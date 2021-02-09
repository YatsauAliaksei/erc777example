package by.mrj.besu.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.16.
 */
@SuppressWarnings("rawtypes")
public class STokenExecutor extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506040516108503803806108508339818101604052604081101561003357600080fd5b508051602090910151600080546001600160a01b039093166001600160a01b03199384161790556001805490921633179091556002556107d8806100786000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c8063153e66e614610051578063a4d66daf14610089578063cae270b6146100a3578063f18d03cc146100d9575b600080fd5b6100876004803603606081101561006757600080fd5b506001600160a01b03813581169160208101359091169060400135610115565b005b610091610377565b60408051918252519081900360200190f35b610087600480360360608110156100b957600080fd5b506001600160a01b0381358116916020810135909116906040013561037d565b610087600480360360808110156100ef57600080fd5b506001600160a01b038135811691602081013582169160408201351690606001356105da565b60015483906001600160a01b031633148061013d57506001546001600160a01b038281169116145b6101785760405162461bcd60e51b81526004018080602001828103825260288152602001806107566028913960400191505060405180910390fd5b8160025481106101c1576040805162461bcd60e51b815260206004820152600f60248201526e2634b6b4ba103b34b7b630ba34b7b760891b604482015290519081900360640190fd5b60008054604080516340c10f1960e01b81526001600160a01b038981166004830152602482018890529151309492909316926340c10f199260448084019391929182900301818387803b15801561021757600080fd5b505af115801561022b573d6000803e3d6000fd5b505060008054604080516315cc8ba360e11b81526001600160a01b038c811660048301528781166024830152604482018b90529151919092169450632b99174693506064808301939282900301818387803b15801561028957600080fd5b505af115801561029d573d6000803e3d6000fd5b505060008054604080516323b872dd60e01b81526001600160a01b038c811660048301528b81166024830152604482018b905291519190921694506323b872dd935060648083019360209383900390910190829087803b15801561030057600080fd5b505af1158015610314573d6000803e3d6000fd5b505050506040513d602081101561032a57600080fd5b505161036f576040805162461bcd60e51b815260206004820152600f60248201526e151c985b9cd9995c8819985a5b1959608a1b604482015290519081900360640190fd5b505050505050565b60025481565b60015483906001600160a01b03163314806103a557506001546001600160a01b038281169116145b6103e05760405162461bcd60e51b81526004018080602001828103825260288152602001806107566028913960400191505060405180910390fd5b816002548110610429576040805162461bcd60e51b815260206004820152600f60248201526e2634b6b4ba103b34b7b630ba34b7b760891b604482015290519081900360640190fd5b60008054604080516315cc8ba360e11b81526001600160a01b0388811660048301523060248301526044820188905291519190921692632b991746926064808201939182900301818387803b15801561048157600080fd5b505af1158015610495573d6000803e3d6000fd5b505060008054604080516323b872dd60e01b81526001600160a01b038a81166004830152306024830152604482018a905291519190921694506323b872dd935060648083019360209383900390910190829087803b1580156104f657600080fd5b505af115801561050a573d6000803e3d6000fd5b505050506040513d602081101561052057600080fd5b5051610565576040805162461bcd60e51b815260206004820152600f60248201526e151c985b9cd9995c8819985a5b1959608a1b604482015290519081900360640190fd5b600080546040805163fe9d930360e01b815260048101879052602481018290526044810184905290516001600160a01b039092169263fe9d93039260848084019382900301818387803b1580156105bb57600080fd5b505af11580156105cf573d6000803e3d6000fd5b505050505050505050565b60015484906001600160a01b031633148061060257506001546001600160a01b038281169116145b61063d5760405162461bcd60e51b81526004018080602001828103825260288152602001806107566028913960400191505060405180910390fd5b816002548110610686576040805162461bcd60e51b815260206004820152600f60248201526e2634b6b4ba103b34b7b630ba34b7b760891b604482015290519081900360640190fd5b60008054604080516315cc8ba360e11b81526001600160a01b0389811660048301523060248301526044820188905291519190921692632b991746926064808201939182900301818387803b1580156106de57600080fd5b505af11580156106f2573d6000803e3d6000fd5b505060008054604080516323b872dd60e01b81526001600160a01b038b811660048301528a81166024830152604482018a905291519190921694506323b872dd935060648083019360209383900390910190829087803b15801561030057600080fdfe496e73756666696369656e742070726976696c6567657320746f207472616e736665722066726f6da2646970667358221220c0f80b00a1d96f7182aed37bb7f102d5d8b2850c1de39cea95a458c16025b3e564736f6c637827302e362e392d646576656c6f702e323032302e352e32372b636f6d6d69742e39663430376665300058";

    public static final String FUNC_BUY = "buy";

    public static final String FUNC_LIMIT = "limit";

    public static final String FUNC_SELL = "sell";

    public static final String FUNC_TRANSFER = "transfer";

    public static final Event ERRORMSG_EVENT = new Event("ErrorMsg",
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    @Deprecated
    protected STokenExecutor(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected STokenExecutor(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected STokenExecutor(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected STokenExecutor(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ErrorMsgEventResponse> getErrorMsgEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ERRORMSG_EVENT, transactionReceipt);
        ArrayList<ErrorMsgEventResponse> responses = new ArrayList<ErrorMsgEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ErrorMsgEventResponse typedResponse = new ErrorMsgEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.msg = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.error = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ErrorMsgEventResponse> errorMsgEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ErrorMsgEventResponse>() {
            @Override
            public ErrorMsgEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ERRORMSG_EVENT, log);
                ErrorMsgEventResponse typedResponse = new ErrorMsgEventResponse();
                typedResponse.log = log;
                typedResponse.msg = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.error = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ErrorMsgEventResponse> errorMsgEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ERRORMSG_EVENT));
        return errorMsgEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> buy(String god, String buyer, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BUY,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, god),
                new org.web3j.abi.datatypes.Address(160, buyer),
                new org.web3j.abi.datatypes.generated.Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> limit() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_LIMIT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> sell(String god, String seller, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SELL,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, god),
                new org.web3j.abi.datatypes.Address(160, seller),
                new org.web3j.abi.datatypes.generated.Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String god, String seller, String buyer, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, god),
                new org.web3j.abi.datatypes.Address(160, seller),
                new org.web3j.abi.datatypes.Address(160, buyer),
                new org.web3j.abi.datatypes.generated.Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static STokenExecutor load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new STokenExecutor(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static STokenExecutor load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new STokenExecutor(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static STokenExecutor load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new STokenExecutor(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static STokenExecutor load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new STokenExecutor(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<STokenExecutor> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _tokenAddress, BigInteger _limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _tokenAddress),
                new org.web3j.abi.datatypes.generated.Uint256(_limit)));
        return deployRemoteCall(STokenExecutor.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<STokenExecutor> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _tokenAddress, BigInteger _limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _tokenAddress),
                new org.web3j.abi.datatypes.generated.Uint256(_limit)));
        return deployRemoteCall(STokenExecutor.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<STokenExecutor> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _tokenAddress, BigInteger _limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _tokenAddress),
                new org.web3j.abi.datatypes.generated.Uint256(_limit)));
        return deployRemoteCall(STokenExecutor.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<STokenExecutor> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _tokenAddress, BigInteger _limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _tokenAddress),
                new org.web3j.abi.datatypes.generated.Uint256(_limit)));
        return deployRemoteCall(STokenExecutor.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ErrorMsgEventResponse extends BaseEventResponse {
        public String msg;

        public byte[] error;
    }
}
