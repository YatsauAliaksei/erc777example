package by.mrj.besu.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ConfigurationProperties(prefix = "token", ignoreUnknownFields = false)
public class ApplicationProperties {

    private String netUrl;
    private String godKey;

    private int trxSleep;
    private int trxAttempts;

    private List<NetContract> netContracts = new ArrayList<>();

    @Getter
    @Setter
    public static class NetContract {
        private String name;
        private String address;
    }
}
