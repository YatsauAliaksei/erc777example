package by.mrj.eth.token;

import by.mrj.eth.contract.ERC777Contract;
import by.mrj.eth.service.ContractLoader;
import by.mrj.eth.service.NetworkBaseContractService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class Erc777Service {
    @Getter
    private final ERC777Contract erc777Contract;
    @Getter
    private final String address;

    public Erc777Service(NetworkBaseContractService networkBaseContractService, ContractLoader contractLoader) throws IOException {

        log.info("Loading erc777 contract...");

        address = networkBaseContractService.getErc777Address();
        erc777Contract = contractLoader.load(address, ERC777Contract.class);

        if (!this.erc777Contract.isValid()) {
            throw new IllegalStateException("Invalid contract loaded. Contract address [" + address + "]");
        }
    }
}
