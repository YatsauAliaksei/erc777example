package by.mrj.eth.service;

import by.mrj.eth.web3j.Web3jClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;

@Slf4j
@Component
public class AccountService {

    private final Transfer transfer;

    @SneakyThrows
    public AccountService(Web3jClient web3jClient, TransactionManager transactionManager) {
        this.transfer = new Transfer(web3jClient.getWeb3j(), transactionManager);
    }

    @SneakyThrows
    public Credentials createCredentials() {
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        Credentials newUserCredentials = Credentials.create(ecKeyPair);

        String address = newUserCredentials.getAddress();
        transfer.sendFunds(address, BigDecimal.ONE, Convert.Unit.ETHER).send();

        log.info("New account [{}] created with {} ETH", address, BigDecimal.ONE);

        return newUserCredentials;
    }
}
