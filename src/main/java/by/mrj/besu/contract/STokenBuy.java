package by.mrj.besu.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
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
public class STokenBuy extends Contract {
    public static final String BINARY = "60806040526002805460ff60a01b1916905534801561001d57600080fd5b506040516102fe3803806102fe8339818101604052608081101561004057600080fd5b50805160208201516040830151606090930151600180546001600160a01b039485166001600160a01b031991821617909155600080548216331790556002805494909316931692909217905560039190915560045561025a806100a46000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c80635a9b0b891461003b578063a6f2ae3a14610075575b600080fd5b61004361007f565b604080516001600160a01b03909516855260208501939093528383019190915215156060830152519081900360800190f35b61007d6100a4565b005b6002546003546004546001600160a01b0383169260ff600160a01b9091041690919293565b6000546001600160a01b03163314610103576040805162461bcd60e51b815260206004820152601760248201527f496e73756666696369656e742070726976696c65676573000000000000000000604482015290519081900360640190fd5b600254600160a01b900460ff161561014c5760405162461bcd60e51b81526004018080602001828103825260258152602001806101db6025913960400191505060405180910390fd5b60015460025460035460408051630a9f337360e11b81523360048201526001600160a01b039384166024820152604481019290925251919092169163153e66e691606480830192600092919082900301818387803b1580156101ad57600080fd5b505af11580156101c1573d6000803e3d6000fd5b50506002805460ff60a01b1916600160a01b179055505056fe436f6e747261637420636f756c642062652070726f636573736564206f6e6c79206f6e6365a26469706673582212208056ebae674a99080a84ee84ccd45081f612f3a2443a47043dd2aa5c4e06ac3864736f6c637827302e362e392d646576656c6f702e323032302e352e32372b636f6d6d69742e39663430376665300058";

    public static final String FUNC_BUY = "buy";

    public static final String FUNC_GETINFO = "getInfo";

    @Deprecated
    protected STokenBuy(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected STokenBuy(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected STokenBuy(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected STokenBuy(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> buy() {
        final Function function = new Function(
                FUNC_BUY,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple4<String, BigInteger, BigInteger, Boolean>> getInfo() {
        final Function function = new Function(FUNC_GETINFO,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple4<String, BigInteger, BigInteger, Boolean>>(function,
                new Callable<Tuple4<String, BigInteger, BigInteger, Boolean>>() {
                    @Override
                    public Tuple4<String, BigInteger, BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, BigInteger, BigInteger, Boolean>(
                                (String) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (Boolean) results.get(3).getValue());
                    }
                });
    }

    @Deprecated
    public static STokenBuy load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new STokenBuy(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static STokenBuy load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new STokenBuy(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static STokenBuy load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new STokenBuy(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static STokenBuy load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new STokenBuy(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<STokenBuy> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String executorAddress, String _buyer, BigInteger _amount, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, executorAddress),
                new org.web3j.abi.datatypes.Address(160, _buyer),
                new org.web3j.abi.datatypes.generated.Uint256(_amount),
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(STokenBuy.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<STokenBuy> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String executorAddress, String _buyer, BigInteger _amount, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, executorAddress),
                new org.web3j.abi.datatypes.Address(160, _buyer),
                new org.web3j.abi.datatypes.generated.Uint256(_amount),
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(STokenBuy.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<STokenBuy> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String executorAddress, String _buyer, BigInteger _amount, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, executorAddress),
                new org.web3j.abi.datatypes.Address(160, _buyer),
                new org.web3j.abi.datatypes.generated.Uint256(_amount),
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(STokenBuy.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<STokenBuy> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String executorAddress, String _buyer, BigInteger _amount, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, executorAddress),
                new org.web3j.abi.datatypes.Address(160, _buyer),
                new org.web3j.abi.datatypes.generated.Uint256(_amount),
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(STokenBuy.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
