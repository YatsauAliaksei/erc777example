package by.mrj.besu.service;

import by.mrj.besu.web3j.GodCredentials;
import by.mrj.besu.web3j.Web3jClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.lang.reflect.Constructor;

import static org.web3j.tx.Contract.deployRemoteCall;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContractLoader {

    private final Web3jClient web3jClient;
    private final TransactionManager transactionManager;

    /**
     * Loads existing contract
     */
    @SneakyThrows
    public <T extends Contract> T load(String address, Class<T> contract) {
        log.info("Loading contract type [{}] address [{}]", contract.getName(), address);

        Constructor<T> constructor = contract.getDeclaredConstructor(String.class, Web3j.class, TransactionManager.class, ContractGasProvider.class);
        constructor.setAccessible(true);
        return constructor.newInstance(address, web3jClient.getWeb3j(), transactionManager, new DefaultGasProvider());
    }

    /**
     * So far only no args constructor supported
     * Deploys new Contract to chain
     */
    @SneakyThrows
    public <T extends Contract> T deploy(String binary, Class<T> contract) {
        log.info("Deploying contract type [{}]", contract.getName());

        RemoteCall<T> remoteCall = deployRemoteCall(contract, web3jClient.getWeb3j(), transactionManager, new DefaultGasProvider(), binary, "");
        return remoteCall.sendAsync().join();
    }
}
