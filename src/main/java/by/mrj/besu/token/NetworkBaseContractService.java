package by.mrj.besu.token;

import by.mrj.besu.config.ApplicationProperties;
import by.mrj.besu.contract.ERC1820Registry;
import by.mrj.besu.contract.SToken;
import by.mrj.besu.contract.STokenExecutor;
import by.mrj.besu.contract.ScraperTesting;
import by.mrj.besu.service.ContractLoader;
import by.mrj.besu.service.ERC1820RegistryDeploy;
import by.mrj.besu.web3j.GodCredentials;
import by.mrj.besu.web3j.Web3jClient;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import static by.mrj.besu.token.STokenExecutorService.S_TOKEN_EXECUTOR;
import static by.mrj.besu.token.STokenService.S_TOKEN;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Component
public class NetworkBaseContractService {

    public final static String ERC1820_REGISTRY = "ERC1820Registry";

    private final TransactionManager transactionManager;

//    @Getter
//    private final String sTokenAddress;
//    @Getter
//    private final String sTokenExecutorAddress;
    @Getter
    private final String scraperTestingAddress;

    public NetworkBaseContractService(ApplicationProperties applicationProperties, Web3jClient web3jClient,
                                      GodCredentials credentials, TransactionManager transactionManager,
                                      ERC1820RegistryDeploy erc1820RegistryDeploy,
                                      ContractLoader contractLoader) throws IOException {

        this.transactionManager = transactionManager;

        Map<String, String> nameToAddress = applicationProperties.getNetContracts().stream()
            .collect(toMap(ApplicationProperties.NetContract::getName, ApplicationProperties.NetContract::getAddress));

//        var erc1820Address = deployErc1820(contractLoader, nameToAddress.get(ERC1820_REGISTRY));

        this.scraperTestingAddress = deployScraperTesting(web3jClient, nameToAddress.get("ScraperTesting"));



//        this.sTokenAddress = deploySTokenTokenContract(web3jClient, credentials,
//            nameToAddress.get(S_TOKEN), erc1820Address);

//        this.sTokenExecutorAddress = deploySTokenExecutorContract(web3jClient,
//            nameToAddress.get(S_TOKEN_EXECUTOR), this.sTokenAddress, applicationProperties.getContract().getLimit());
    }

    private String deployScraperTesting(Web3jClient web3jClient, String contractAddress) throws IOException {
        if (StringUtils.hasText(contractAddress)) {
            return contractAddress;
        }

        log.info("ScraperTesting contract address was not found. Creating...");

        var scraperTesting = ScraperTesting.deploy(web3jClient.getWeb3j(), transactionManager,
            new DefaultGasProvider(), BigInteger.valueOf(11), BigInteger.valueOf(22)).sendAsync().join();

        contractAddress = scraperTesting.getContractAddress();
        log.info("ScraperTesting contract created with address [{}]", contractAddress);

        if (!scraperTesting.isValid()) {
            throw new IllegalStateException("Invalid contract loaded. Contract address [" + contractAddress + "]");
        }

        return contractAddress;
    }

    /**
     * Creates {@link SToken} contract if doesn't exist
     */
    private String deploySTokenExecutorContract(Web3jClient web3jClient,
                                                String sTokenExecutorAddress, String tokenAddress, long limit) throws IOException {
        if (StringUtils.hasText(sTokenExecutorAddress)) {
            return sTokenExecutorAddress;
        }

        log.info("STokenExecutor contract address was not found. Creating...");

        var sTokenExecutor = STokenExecutor.deploy(web3jClient.getWeb3j(), transactionManager,
            new DefaultGasProvider(), tokenAddress, BigInteger.valueOf(limit)).sendAsync().join();

        sTokenExecutorAddress = sTokenExecutor.getContractAddress();
        log.info("STokenExecutor contract created with address [{}]", sTokenExecutorAddress);

        if (!sTokenExecutor.isValid()) {
            throw new IllegalStateException("Invalid contract loaded. Contract address [" + sTokenExecutorAddress + "]");
        }

        return sTokenExecutorAddress;
    }

    /**
     * Creates {@link SToken} contract if doesn't exist
     */
    private String deploySTokenTokenContract(Web3jClient web3jClient, GodCredentials credentials, String tokenTokenAddress, String erc1820Address) throws IOException {
        if (StringUtils.hasText(tokenTokenAddress)) {
            return tokenTokenAddress;
        }

        log.info("STokenToken contract address was not found. Creating...");

        var sTokenToken = SToken.deploy(web3jClient.getWeb3j(), transactionManager, new DefaultGasProvider(),
            List.of(credentials.getCredentials().getAddress()), erc1820Address).sendAsync().join();

        tokenTokenAddress = sTokenToken.getContractAddress();
        log.info("STokenToken contract created with address [{}]", tokenTokenAddress);

        if (!sTokenToken.isValid()) {
            throw new IllegalStateException("Invalid contract loaded. Contract address [" + tokenTokenAddress + "]");
        }

        return tokenTokenAddress;
    }

    /**
     * Creates ERC1820Registry in case doesn't exist yet.
     * Not needed so far for our needs
     */
    private String deployErc1820(ContractLoader contractLoader, String erc1820Address) {

        if (!StringUtils.hasText(erc1820Address)) {
            log.info("ERC1820 address not found. Creating new...");

            erc1820Address = contractLoader.deploy(ERC1820Registry.BINARY, ERC1820Registry.class).getContractAddress();

            log.info("ERC1820 contract created. Address [{}]", erc1820Address);
        }

        return erc1820Address;
    }
}
