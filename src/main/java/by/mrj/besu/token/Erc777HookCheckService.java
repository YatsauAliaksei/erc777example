package by.mrj.besu.token;

import by.mrj.besu.contract.ERC777HookCheck;
import by.mrj.besu.service.ContractLoader;
import by.mrj.besu.service.NetworkBaseContractService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class Erc777HookCheckService {
    @Getter
    private final ERC777HookCheck erc777HookCheck;
    @Getter
    private final String address;

    public Erc777HookCheckService(NetworkBaseContractService networkBaseContractService, ContractLoader contractLoader) throws IOException {

        log.info("Loading erc777hookCheck contract...");

        address = networkBaseContractService.getErc777HookAddress();
        erc777HookCheck = contractLoader.load(address, ERC777HookCheck.class);

        if (!this.erc777HookCheck.isValid()) {
            throw new IllegalStateException("Invalid contract loaded. Contract address [" + address + "]");
        }
    }
}
