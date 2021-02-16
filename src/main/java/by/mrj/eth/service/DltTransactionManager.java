package by.mrj.eth.service;

import by.mrj.eth.config.ApplicationProperties;
import by.mrj.eth.web3j.GodCredentials;
import by.mrj.eth.web3j.Web3jClient;
import org.springframework.stereotype.Component;
import org.web3j.tx.RawTransactionManager;

@Component
public class DltTransactionManager extends RawTransactionManager {

    public DltTransactionManager(Web3jClient web3jClient, GodCredentials credentials, ApplicationProperties props) {
        super(web3jClient.getWeb3j(), credentials.getCredentials(), props.getTrxAttempts(), props.getTrxSleep());
    }
}
