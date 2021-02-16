package by.mrj.besu.service;

import by.mrj.besu.config.ApplicationProperties;
import by.mrj.besu.contract.ERC777Contract;
import by.mrj.besu.contract.ERC777HookCheck;
import by.mrj.besu.web3j.GodCredentials;
import by.mrj.besu.web3j.Web3jClient;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toMap;

@Slf4j
@Component
public class NetworkBaseContractService {

    @Getter
    private final String erc777Address;
    @Getter
    private final String erc777HookAddress;

    @SneakyThrows
    // using simple sync way to avoid problems with 'nonce'
    public NetworkBaseContractService(ApplicationProperties applicationProperties, Web3jClient web3jClient,
                                      GodCredentials credentials, ERC1820RegistryDeploy erc1820RegistryDeploy) {

        Map<String, String> nameToAddress = applicationProperties.getNetContracts().stream()
            .collect(toMap(ApplicationProperties.NetContract::getName, ApplicationProperties.NetContract::getAddress));

        erc1820RegistryDeploy.deploy();

        String godAddress = credentials.getCredentials().getAddress();

        erc777Address = createIfNotExist(nameToAddress.get("ERC777Contract"), () -> {
            log.info("Deploying new erc777 contract");

            try {
                return ERC777Contract.deploy(web3jClient.getWeb3j(), credentials.getCredentials(), new DefaultGasProvider(),
                    godAddress, BigInteger.valueOf(100000L), "ERC777 ", "erc777", Lists.newArrayList()).send();
            } catch (Exception e) {
                log.error("Error creating erc777", e);
                throw new RuntimeException(e);
            }
        });

        log.info("Deployed ERC777 contract: [{}]", erc777Address);

        erc777HookAddress = createIfNotExist(nameToAddress.get("ERC777HookCheck"), () -> {
            log.info("Deploying new erc777hookCheck");
            try {
                return ERC777HookCheck.deploy(web3jClient.getWeb3j(), credentials.getCredentials(), new DefaultGasProvider()).send();
            } catch (Exception e) {
                log.error("Error creating erc777 hook check", e);
                throw new RuntimeException(e);
            }
        });

        log.info("Deployed ERC777hook contract: [{}]", erc777HookAddress);
    }

    @SneakyThrows
    private String createIfNotExist(String address, Supplier<Contract> supplier) {
        if (StringUtils.hasText(address)) {
            return address;
        }

        var contract = supplier.get();
        if (!contract.isValid()) {
            throw new IllegalStateException("Invalid contract loaded. Contract address [" + address + "]");
        }

        return contract.getContractAddress();
    }
}
