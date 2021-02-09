package by.mrj.besu.service;

import by.mrj.besu.config.ApplicationProperties;
import by.mrj.besu.web3j.GodCredentials;
import by.mrj.besu.web3j.Web3jClient;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AccountService {

    private final Transfer transfer;

    @Getter
    private final List<Credentials> users;

    @SneakyThrows
    public AccountService(ApplicationProperties applicationProperties,
                          Web3jClient web3jClient,
                          TransactionManager transactionManager) {

        this.transfer = new Transfer(web3jClient.getWeb3j(), transactionManager);

        users = applicationProperties.getUsers().stream()
            .map(ApplicationProperties.User::getPrivateKey)
            .map(Credentials::create)
            .collect(Collectors.toList());
    }

    @SneakyThrows
    public Credentials createCredentials() {
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
//        WalletFile walletFile = Wallet.createStandard("", ecKeyPair);
        Credentials newUserCredentials = Credentials.create(ecKeyPair);

        String address = newUserCredentials.getAddress();
        transfer.sendFunds(address, BigDecimal.ONE, Convert.Unit.ETHER);

        log.info("New account [{}] created with {} ETH", address, BigDecimal.ONE);

        return newUserCredentials;
    }
}
