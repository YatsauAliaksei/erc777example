package by.mrj.besu.service;

import by.mrj.besu.contract.ERC777HookCheck;
import by.mrj.besu.contract.IERC1820Registry;
import by.mrj.besu.token.Erc777HookCheckService;
import by.mrj.besu.token.Erc777Service;
import by.mrj.besu.web3j.GodCredentials;
import by.mrj.besu.web3j.Web3jClient;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.NewThreadScheduler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.tx.ChainId;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class Checker {

    private final GodCredentials credentials;
    private final Erc777Service erc777Service;
    private final Erc777HookCheckService erc777HookCheckService;
    private final AccountService accountService;
    private final Web3jClient web3jClient;
    private final ERC1820RegistryDeploy erc1820RegistryDeploy;
    private final TrxReceiptProcessor transactionReceiptProcessor;

    @SneakyThrows
    public void checkOperations() {

        String registryAddress = erc1820RegistryDeploy.getRegistry().getContractAddress();
        IERC1820Registry registry = erc1820RegistryDeploy.getRegistry();

//        subscribeToInterfaceCreationEvents(registry);
//        subscribeToPendingTransactionsEvents();

        var client = createNewClient();
        logBalance(client.getAddress());

        ERC777HookCheck erc777HookCheck = erc777HookCheckService.getErc777HookCheck();

        listenReceivedEvent(erc777HookCheck);
        listenToSendEvent(erc777HookCheck);

        var erc777Hook = erc777HookCheck.getContractAddress();

        var trxMan = new RawTransactionManager(web3jClient.getWeb3j(), client, ChainId.NONE);
        var setManagerFunction = registry.setManager(client.getAddress(), credentials.getCredentials().getAddress()).encodeFunctionCall();

        EthSendTransaction ethSendTransaction = trxMan.sendTransaction(DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT, registryAddress, setManagerFunction, BigInteger.ZERO);
        if (ethSendTransaction.hasError()) {
            log.error("Error: {}", ethSendTransaction.getError().getMessage());
            throw new RuntimeException("Got error while setting manager");
        }

        log.info("Register hook as sender for: {}", credentials.getCredentials().getAddress());
        var senderForReceipt = erc777HookCheck.senderFor(credentials.getCredentials().getAddress()).send();

        log.info("Register hook as recipient for: {}", client.getAddress());
        var recipientForReceipt = erc777HookCheck.recipientFor(client.getAddress()).send();

        transactionReceiptProcessor.waitForTransactionReceipt(ethSendTransaction.getTransactionHash());
        checkManager(registry, client, erc777Hook);

        transactionReceiptProcessor.waitForTransactionReceipt(senderForReceipt.getTransactionHash());
        transactionReceiptProcessor.waitForTransactionReceipt(recipientForReceipt.getTransactionHash());

        var recipientInterfaceHash = registry.interfaceHash("ERC777TokensRecipient").send();
        setInterfaceImplementer(registry, client.getAddress(), erc777Hook, recipientInterfaceHash, "recipient");

        var senderInterfaceHash = registry.interfaceHash("ERC777TokensSender").send();
        setInterfaceImplementer(registry, credentials.getCredentials().getAddress(), erc777Hook, senderInterfaceHash, "sender");

        checkImplementor(registry, client, erc777Hook, recipientInterfaceHash);
        checkImplementor(registry, credentials.getCredentials(), erc777Hook, senderInterfaceHash);

        log.info("Before: {}", getTokenBalance(client.getAddress()));

        var random = ThreadLocalRandom.current();

        var transfersNum = 5;
        while (transfersNum-- > 0) {
            var rec = erc777Service.getErc777Contract().send(client.getAddress(), BigInteger.valueOf(random.nextLong(1, 10)),
                "".getBytes()).send();
            transactionReceiptProcessor.waitForTransactionReceipt(rec.getTransactionHash());

//            logTrxEvents(erc777HookCheck, rec);

            log.info("After: {}", getTokenBalance(client.getAddress()));

            TimeUnit.MILLISECONDS.sleep(random.nextInt(100, 3000));
        }

        log.info("Finished.");
    }

    private void logTrxEvents(ERC777HookCheck erc777HookCheck, org.web3j.protocol.core.methods.response.TransactionReceipt rec) {
        for (ERC777HookCheck.TokensReceivedCalledEventResponse tokensReceivedCalledEventResponse : erc777HookCheck.getTokensReceivedCalledEvents(rec)) {
            log.info("TRX Receive event: {}", tokensReceivedCalledEventResponse);
        }

        for (ERC777HookCheck.TokensToSendCalledEventResponse tokensToSendCalledEventResponse : erc777HookCheck.getTokensToSendCalledEvents(rec)) {
            log.info("TRX ToSend event: {}", tokensToSendCalledEventResponse);
        }
    }

    private Disposable subscribeToPendingTransactionsEvents() {
        return web3jClient.getWeb3j().ethPendingTransactionHashFlowable().subscribe(s -> log.info("Pending: {}", s));
    }

    private Disposable subscribeToInterfaceCreationEvents(IERC1820Registry registry) {
        EthFilter filterReg = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, registry.getContractAddress());
        filterReg.addSingleTopic(EventEncoder.encode(IERC1820Registry.INTERFACEIMPLEMENTERSET_EVENT));
        return registry.interfaceImplementerSetEventFlowable(filterReg).subscribe(i -> log.info("Registering Interface: {}", i));
    }

    @SneakyThrows
    private void setInterfaceImplementer(IERC1820Registry registry, String forAddress, String erc777Hook, byte[] functionHash, String interfaceName) {
        log.info("Setting {}", interfaceName);
        var receipt = registry.setInterfaceImplementer(forAddress, functionHash, erc777Hook).send();

        if (!receipt.isStatusOK()) {
            log.error("Receipt NOT OK", receipt.getLogs());
            throw new RuntimeException(interfaceName + " failed");
        }

        log.info("Waiting for {} interface creation for: {}", interfaceName, receipt.getTransactionHash());
        transactionReceiptProcessor.waitForTransactionReceipt(receipt.getTransactionHash());
    }

    private void checkManager(IERC1820Registry registry, Credentials client, String erc777Hook) throws Exception {
        var manager = registry.getManager(client.getAddress()).send();
        log.info("Manager: {}", manager);

        if (!Numeric.cleanHexPrefix(manager).equals(Numeric.cleanHexPrefix(credentials.getCredentials().getAddress()))) {
            log.error("Still not a manager.\n{}\n{}", client.getAddress(), erc777Hook);
            throw new RuntimeException();
        }
    }

    private Disposable listenToSendEvent(ERC777HookCheck erc777HookCheck) {
        log.info("Setting listener for Send event");

        return erc777HookCheck.tokensToSendCalledEventFlowable(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST)
            .subscribeOn(new NewThreadScheduler())
            .subscribe(
                (event) -> log.info("ToSend event:\nfrom: {}\nto: {}\namount: {}", event.from, event.to, event.amount),
                throwable -> {
                    log.error("Can't create Send listener", throwable);
                    throw new RuntimeException(throwable);
                },
                () -> log.info("Completed!"));
    }

    private Disposable listenReceivedEvent(ERC777HookCheck erc777HookCheck) {
        log.info("Setting listener for Received event: {}", erc777HookCheckService.getAddress());

        return erc777HookCheck.tokensReceivedCalledEventFlowable(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST)
            .subscribe((event) -> log.info("Received event:\nfrom: {}\nto: {}\namount: {}", event.from, event.to, event.amount),
                throwable -> {
                    log.error("Can't create Receive listener", throwable);
                    throw new RuntimeException(throwable);
                },
                () -> log.info("Completed!"));
    }

    private void checkImplementor(IERC1820Registry registry, Credentials credentials, String erc777Hook, byte[] h2) throws Exception {
        String implementorAddress = registry.getInterfaceImplementer(credentials.getAddress(), h2).send();

        if (!Numeric.cleanHexPrefix(erc777Hook).equals(Numeric.cleanHexPrefix(implementorAddress))) {
            log.error("Still not a Implementor.\n{}\n{}", implementorAddress, erc777Hook);
            throw new RuntimeException();
        }
    }

    private void logBalance(String address) throws Exception {
        BigInteger ethBalance = getEthBalance(address);
        BigInteger tokenBalance = getTokenBalance(address);
        log.info("\nAddress: {}\nBalance ETH: [{}], TokenToken: [{}]", address, ethBalance, tokenBalance);
    }

    private Credentials createNewClient() {
        Credentials newUserCredentials = accountService.createCredentials();
        String newClientAddress = newUserCredentials.getAddress();

        BigInteger privateKey = credentials.getCredentials().getEcKeyPair().getPrivateKey();
        log.info("New client address [{}] private [{}]", newClientAddress, Numeric.toHexStringNoPrefix(privateKey));
        return newUserCredentials;
    }

    private BigInteger getTokenBalance(String address) throws Exception {
        return erc777Service.getErc777Contract().balanceOf(address).send();
    }

    private BigInteger getEthBalance(String a) throws java.io.IOException {
        Request<?, EthGetBalance> request = web3jClient.getWeb3j().ethGetBalance(a, DefaultBlockParameter.valueOf("latest"));
        return request.send().getBalance();
    }
}
