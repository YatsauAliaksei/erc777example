package by.mrj.besu.token;

import by.mrj.besu.contract.SToken;
import by.mrj.besu.contract.STokenExecutor;
import by.mrj.besu.service.ContractLoader;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class STokenExecutorService {

    public final static String S_TOKEN_EXECUTOR = "STokenExecutor";

    @Getter
    private final STokenExecutor sTokenExecutor;
    @Getter
    private final String address;

    @SneakyThrows
    public STokenExecutorService(NetworkBaseContractService networkBaseContractService, ContractLoader contractLoader) {

        log.info("Loading STokenExecutor contract...");

        address = networkBaseContractService.getSTokenExecutorAddress();
        sTokenExecutor = contractLoader.load(address, STokenExecutor.class);

        if (!this.sTokenExecutor.isValid()) {
            throw new IllegalStateException("Invalid contract loaded. Contract address [" + address + "]");
        }

        var sTokenToken = contractLoader.load(networkBaseContractService.getSTokenAddress(), SToken.class);
        sTokenToken.addAllowed(address).send();
    }
}
