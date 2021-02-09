package by.mrj.besu;

import by.mrj.besu.service.Checker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

import javax.net.ssl.HttpsURLConnection;
import java.util.Properties;

@Slf4j
@SpringBootApplication
public class SpringBootApp {

    public static void main(String[] args) {
        HttpsURLConnection.setDefaultHostnameVerifier((s, sslSession) -> true);

        ConfigurableApplicationContext context = new SpringApplicationBuilder()
            .sources(SpringBootApp.class)
            .bannerMode(Banner.Mode.OFF)
            .registerShutdownHook(true)
            .profiles("dev")
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

    private static void setDefaultProps(SpringApplication app) {
        Properties defaultProperties = new Properties();
        defaultProperties.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "dev");

        app.setDefaultProperties(defaultProperties);
    }
}
