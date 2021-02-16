package by.mrj.besu;

import by.mrj.besu.service.Checker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.net.ssl.HttpsURLConnection;

@Slf4j
@SpringBootApplication
public class SpringBootApp {

    public static void main(String[] args) {
        HttpsURLConnection.setDefaultHostnameVerifier((s, sslSession) -> true);

        ConfigurableApplicationContext context = new SpringApplicationBuilder()
            .sources(SpringBootApp.class)
            .bannerMode(Banner.Mode.OFF)
            .registerShutdownHook(true)
            .build()
            .run(args);

        HttpsURLConnection.setDefaultHostnameVerifier((s, sslSession) -> {
            log.info("Verifying host");

            return true;
        });

        Checker checker = context.getBean(Checker.class);
        checker.checkOperations();

        log.info("Closing");
    }
}
