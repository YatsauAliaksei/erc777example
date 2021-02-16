package by.mrj.besu.web3j;

import by.mrj.besu.config.ApplicationProperties;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;

@Slf4j
@Component
public class GodCredentials {

    @Getter
    private final Credentials credentials;

    public GodCredentials(ApplicationProperties applicationProperties) {
        String privateKey = applicationProperties.getGodKey();

        credentials = Credentials.create(privateKey);
    }
}
