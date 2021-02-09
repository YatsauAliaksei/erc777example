package by.mrj.besu.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
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
public class ScraperTesting extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506040516103c73803806103c78339818101604052604081101561003357600080fd5b508051602090910151600280546001600160a01b03191633179055600091909155600155610361806100666000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c806304b9b16b146100515780635a9b0b89146100705780639e813f1f14610091578063fe72ac7f146101be575b600080fd5b61006e6004803603602081101561006757600080fd5b50356101db565b005b610078610239565b6040805192835260208301919091528051918290030190f35b61006e600480360360408110156100a757600080fd5b8101906020810181356401000000008111156100c257600080fd5b8201836020820111156100d457600080fd5b803590602001918460018302840111640100000000831117156100f657600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929594936020810193503591505064010000000081111561014957600080fd5b82018360208201111561015b57600080fd5b8035906020019184600183028401116401000000008311171561017d57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610243945050505050565b61006e600480360360208110156101d457600080fd5b50356102a8565b6002546001600160a01b03163314610234576040805162461bcd60e51b8152602060048201526017602482015276496e73756666696369656e742070726976696c6567657360481b604482015290519081900360640190fd5b600055565b6000546001549091565b6002546001600160a01b0316331461029c576040805162461bcd60e51b8152602060048201526017602482015276496e73756666696369656e742070726976696c6567657360481b604482015290519081900360640190fd5b50506000808055600155565b6002546001600160a01b03163314610301576040805162461bcd60e51b8152602060048201526017602482015276496e73756666696369656e742070726976696c6567657360481b604482015290519081900360640190fd5b60015556fea264697066735822122060d3123a5444e02880c487a3361795d0a593e6b744033fa1648c5e163dc8950764736f6c637827302e362e392d646576656c6f702e323032302e352e32372b636f6d6d69742e39663430376665300058";

    public static final String FUNC_GETINFO = "getInfo";

    public static final String FUNC_NEWAMOUNT = "newAmount";

    public static final String FUNC_NEWPRICE = "newPrice";

    public static final String FUNC_SUBMIT = "submit";

    @Deprecated
    protected ScraperTesting(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ScraperTesting(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ScraperTesting(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ScraperTesting(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> getInfo() {
        final Function function = new Function(FUNC_GETINFO,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple2<BigInteger, BigInteger>>(function,
                new Callable<Tuple2<BigInteger, BigInteger>>() {
                    @Override
                    public Tuple2<BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> newAmount(BigInteger _amount) {
        final Function function = new Function(
                FUNC_NEWAMOUNT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> newPrice(BigInteger _price) {
        final Function function = new Function(
                FUNC_NEWPRICE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_price)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> submit(String userData, byte[] clientData) {
        final Function function = new Function(
                FUNC_SUBMIT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(userData),
                new org.web3j.abi.datatypes.DynamicBytes(clientData)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ScraperTesting load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ScraperTesting(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ScraperTesting load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ScraperTesting(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ScraperTesting load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ScraperTesting(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ScraperTesting load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ScraperTesting(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ScraperTesting> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger _amount, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_amount),
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(ScraperTesting.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<ScraperTesting> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger _amount, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_amount),
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(ScraperTesting.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ScraperTesting> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _amount, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_amount),
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(ScraperTesting.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ScraperTesting> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _amount, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_amount),
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(ScraperTesting.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
