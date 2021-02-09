package by.mrj.besu.service;

import by.mrj.besu.token.ContractService;
import by.mrj.besu.token.STokenService;
import by.mrj.besu.token.ScraperService;
import by.mrj.besu.web3j.GodCredentials;
import by.mrj.besu.web3j.Web3jClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
@RequiredArgsConstructor
public class Checker {

    private final GodCredentials credentials;
    private final STokenService sTokenTokenService;
    private final ScraperService scraperService;
    private final AccountService accountService;
    private final Web3jClient web3jClient;
    private final ContractService contractService;

    private final Map<Integer, Tuple2<Double, Double>> cityCoordinates = new HashMap<>();

    {
        cityCoordinates.put(0, new Tuple2<>(50.064651, 19.944981));//  Krakow
        cityCoordinates.put(1, new Tuple2<>(50.064651, 19.944981));// Krakow
        cityCoordinates.put(2, new Tuple2<>(47.376888, 8.541694)); // Zurich
        cityCoordinates.put(3, new Tuple2<>(1.289440, 103.849983)); // Singapore
        cityCoordinates.put(4, new Tuple2<>(41.878113, -87.629799)); // Chicago
        cityCoordinates.put(5, new Tuple2<>(48.135124, 11.581981)); // Munich
    }


    @Data
    static class UserData {
        int id;
        String name;
        int price;
        int amount;
        String geo;
        String operation;
    }

    @Data
    static class ClientData {
        int id;
        int tax;
        String number;
        int profit;
    }


    @SneakyThrows
    public void checkOperations() {
        var random = ThreadLocalRandom.current();

        while (true) {
            var start = System.nanoTime();
            scraperService.getScraperTesting().newAmount(BigInteger.valueOf(15)).sendAsync();
            Thread.sleep(random.nextInt(100, 2000));

            scraperService.getScraperTesting().newPrice(BigInteger.valueOf(38)).sendAsync();
            Thread.sleep(random.nextInt(100, 3000));

            ObjectMapper om = new ObjectMapper();

            var city = cityCoordinates.get(random.nextInt(6));

            var ud = new UserData();
            int id = random.nextInt(20);
            ud.setId(id);
            ud.setName(UUID.randomUUID().toString().substring(0, 3));
            ud.setPrice(random.nextInt(40, 60));
            ud.setAmount(random.nextInt(100));
            ud.setOperation(random.nextBoolean() ? "BUY" : "SELL");
            ud.setGeo(city.component1() + "," + city.component2());

            var cd = new ClientData();
            cd.setId(id);
            cd.setTax(random.nextInt(50, 300));
            cd.setNumber(UUID.randomUUID().toString().substring(0, 7));
            cd.setProfit((int) (ud.getAmount() * ud.getPrice() * random.nextDouble()));

            scraperService.getScraperTesting().submit(om.writeValueAsString(ud), om.writeValueAsBytes(cd)).sendAsync();

            Thread.sleep(random.nextInt(100, 3000));

            log.info("Time: {}", Duration.ofNanos(System.nanoTime() - start));
        }

//        long amount = 10L;

//        log.info("Starting check with amount - {}", amount);

        // INITIAL
//        String newClientAddress = createNewClient();
//        logBalance(newClientAddress);

        // fixme: should be done in other way. Contract should be created initially and address saved.
        // todo: Create mechanism which destroys contracts if they were not executed after specified time limit.
//        log.info("BUY OP");
//        contractService.createBuy(newClientAddress, amount, BigInteger.ONE).join().buy().send();
//        logBalance(newClientAddress);

/*        log.info("SELL OP");
        contractService.createSell(newClientAddress, amount, BigInteger.ONE).join().sell().send();
        logBalance(newClientAddress);

        // 2nd client
        String newClientAddress1 = createNewClient();
        logBalance(newClientAddress1);

        // BUY
        contractService.createBuy(newClientAddress, amount, BigInteger.ONE).join().buy().send();
        logBalance(newClientAddress);

        log.info("TRANSFER OP");
        // TRANSFER
        contractService.createTransfer(newClientAddress, newClientAddress1, amount).join().transfer().send();
        logBalance(newClientAddress);
        logBalance(newClientAddress1);*/
    }

    private void logBalance(String address) throws Exception {
        BigInteger ethBalance = getEthBalance(address);
        BigInteger tokenBalance = getTokenBalance(address);
        log.info("\nAddress: {}\nBalance ETH: [{}], TokenToken: [{}]", address, ethBalance, tokenBalance);
    }

    private String createNewClient() {
        Credentials newUserCredentials = accountService.createCredentials();
        String newClientAddress = newUserCredentials.getAddress();

        BigInteger privateKey = credentials.getCredentials().getEcKeyPair().getPrivateKey();
        log.info("New client address [{}] private [{}]", newClientAddress, Numeric.toHexStringNoPrefix(privateKey));
        return newClientAddress;
    }

    private BigInteger getTokenBalance(String address) throws Exception {
        return sTokenTokenService.getSToken().balanceOf(address).send();
    }

    private BigInteger getEthBalance(String a) throws java.io.IOException {
        Request<?, EthGetBalance> request = web3jClient.getWeb3j().ethGetBalance(a, DefaultBlockParameter.valueOf("latest"));
        return request.send().getBalance();
    }
}
