package by.mrj.besu.web3j;

import by.mrj.besu.config.ApplicationProperties;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.IOException;

@Slf4j
@Component
public class GodCredentials {

    @Getter
    private final Credentials credentials;

    public GodCredentials(ApplicationProperties applicationProperties) throws IOException, CipherException {
        String privateKey = applicationProperties.getGodKey();

        if (StringUtils.hasText(privateKey)) {
            credentials = Credentials.create(privateKey);
        } else {
            ApplicationProperties.Wallet wallet = applicationProperties.getWallet();
            ClassPathResource classPathResource = new ClassPathResource(wallet.getPath());

            credentials = WalletUtils.loadCredentials(wallet.getPassword(), classPathResource.getFile());
        }
    }
}
