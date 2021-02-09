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

  private Wallet wallet;
  private String netUrl;
  private String godKey;
  private Contract contract;

  private int trxSleep;
  private int trxAttempts;

  private List<NetContract> netContracts = new ArrayList<>();
  private List<User> users = new ArrayList<>();

  @Getter
  @Setter
  public static class Wallet {
    private String path;
    private String password;
  }

  @Getter
  @Setter
  public static class NetContract {
    private String name;
    private String address;
  }

  @Getter
  @Setter
  public static class Contract {
    private int limit;
  }

  @Getter
  @Setter
  public static class User {
    private String privateKey;
  }
}
