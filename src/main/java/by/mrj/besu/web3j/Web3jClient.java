package by.mrj.besu.web3j;

import by.mrj.besu.config.ApplicationProperties;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Slf4j
@Component
public class Web3jClient implements DisposableBean {

    @Getter
    private final Web3j web3j;

    @SneakyThrows
    public Web3jClient(ApplicationProperties applicationProperties) {
        String netUrl = applicationProperties.getNetUrl();

        log.info("Creating net client for url [{}]", netUrl);
        web3j = Web3j.build(new HttpService(netUrl, new OkHttpClient.Builder().build()));
    }

    @Override
    public void destroy() throws Exception {
        log.info("Destroying web3j client");
        web3j.shutdown();
    }
}
