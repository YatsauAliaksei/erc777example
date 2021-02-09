package by.mrj.besu.token;


import by.mrj.besu.contract.STokenBuy;
import by.mrj.besu.contract.STokenSell;
import by.mrj.besu.contract.STokenTransfer;
import by.mrj.besu.web3j.Web3jClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class ContractService {

    private final Web3jClient web3jClient;
    private final TransactionManager transactionManager;
    private final String tokenExecutorServiceAddress;

    public ContractService(Web3jClient web3jClient, TransactionManager transactionManager, STokenExecutorService sTokenExecutorService) {
        this.web3jClient = web3jClient;
        this.transactionManager = transactionManager;
        this.tokenExecutorServiceAddress = sTokenExecutorService.getAddress();
    }

    /**
     * Sells to {@param buyer} {@param amount} of token by {@param price}
     * @param amount - 0.1g
     * @param price  - 1 cent
     */
    public CompletableFuture<STokenBuy> createBuy(String buyer, long amount, BigInteger price) {
        log.info("Creating BUY contract for [{}], size: [{}] price: [{}]", buyer, amount, price);
        log.info("Executor: {}", tokenExecutorServiceAddress);


        return STokenBuy.deploy(web3jClient.getWeb3j(), transactionManager, new DefaultGasProvider(),
            tokenExecutorServiceAddress, buyer, BigInteger.valueOf(amount), price).sendAsync();
    }

    public CompletableFuture<STokenSell> createSell(String seller, long amount, BigInteger price) {
        log.info("Creating SELL contract for [{}], size: [{}] price: [{}]", seller, amount, price);

        return STokenSell.deploy(web3jClient.getWeb3j(), transactionManager, new DefaultGasProvider(),
            tokenExecutorServiceAddress, seller, BigInteger.valueOf(amount), price).sendAsync();
    }

    public CompletableFuture<STokenTransfer> createTransfer(String from, String to, long amount) {
        log.info("Creating TRANSFER contract from [{}] to [{}], size: [{}]", from, to, amount);

        return STokenTransfer.deploy(web3jClient.getWeb3j(), transactionManager, new DefaultGasProvider(),
            tokenExecutorServiceAddress, from, to, BigInteger.valueOf(amount)).sendAsync();

    }
}
