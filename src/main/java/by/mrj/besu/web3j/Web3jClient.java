package by.mrj.besu.web3j;

import by.mrj.besu.config.ApplicationProperties;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGetStorageAt;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.websocket.WebSocketClient;
import org.web3j.protocol.websocket.WebSocketService;

import java.net.URI;

@Slf4j
@Component
public class Web3jClient implements DisposableBean {

    @Getter
    private final Web3j web3j;

    @SneakyThrows
    public Web3jClient(ApplicationProperties applicationProperties) {
        // todo: make configurable web3jService
        String netUrl = applicationProperties.getNetUrl();

        log.info("Creating net client for url [{}]", netUrl);
//        var wsc = new WebSocketClient(URI.create("wss://localhost:8546"));
//        var wss = new WebSocketService(wsc, false);
//        wss.connect();
//        web3j = Web3j.build(wss);
        web3j = Web3j.build(new HttpService(netUrl, new OkHttpClient.Builder()
//            .hostnameVerifier((s, sslSession) -> true)
            .build()));  // defaults to http://localhost:8545/
    }

    @Override
    public void destroy() throws Exception {
        log.info("Destroying web3j client");
        web3j.shutdown();
    }
}
