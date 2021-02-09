package by.mrj.besu.token;

import by.mrj.besu.contract.SToken;
import by.mrj.besu.service.ContractLoader;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class STokenService {

    public final static String S_TOKEN = "SToken";

    @Getter
    // fixme: no direct access. All needed methods wrapped here with proper loging.
    private final SToken sToken;
    @Getter
    private final String address;

    public STokenService(NetworkBaseContractService networkBaseContractService, ContractLoader contractLoader) throws IOException {

        log.info("Loading SToken contract...");

        address = networkBaseContractService.getSTokenAddress();
        sToken = contractLoader.load(address, SToken.class);

        if (!this.sToken.isValid()) {
            throw new IllegalStateException("Invalid contract loaded. Contract address [" + address + "]");
        }
    }
}
