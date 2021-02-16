package by.mrj.besu.service;

import by.mrj.besu.web3j.Web3jClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;

import java.io.IOException;

@Slf4j
@Component
public class TrxReceiptProcessor extends PollingTransactionReceiptProcessor {

    public TrxReceiptProcessor(Web3jClient web3j) {
        super(web3j.getWeb3j(), 1000/*ms*/, 5);
    }

    @Override
    public TransactionReceipt waitForTransactionReceipt(String transactionHash) throws IOException, TransactionException {
        var result = super.waitForTransactionReceipt(transactionHash);

        if (!result.isStatusOK()) {
            log.error("Failed to check trx: {}", transactionHash);
            throw new RuntimeException();
        }

        return result;
    }
}
