package by.mrj.besu.token;

import by.mrj.besu.contract.ScraperTesting;
import by.mrj.besu.service.ContractLoader;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class ScraperService {
    @Getter
    // fixme: no direct access. All needed methods wrapped here with proper loging.
    private final ScraperTesting scraperTesting;
    @Getter
    private final String address;

    public ScraperService(NetworkBaseContractService networkBaseContractService, ContractLoader contractLoader) throws IOException {

        log.info("Loading ScraperTesting contract...");

        address = networkBaseContractService.getScraperTestingAddress();
        scraperTesting = contractLoader.load(address, ScraperTesting.class);

        if (!this.scraperTesting.isValid()) {
            throw new IllegalStateException("Invalid contract loaded: ScraperTesting. Contract address [" + address + "]");
        }
    }
}
