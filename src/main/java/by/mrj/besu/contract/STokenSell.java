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
public class STokenSell extends Contract {
    public static final String BINARY = "60806040526002805460ff60a01b1916905534801561001d57600080fd5b506040516102fe3803806102fe8339818101604052608081101561004057600080fd5b50805160208201516040830151606090930151600180546001600160a01b039485166001600160a01b031991821617909155600080548216331790556002805494909316931692909217905560039190915560045561025a806100a46000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c8063457100741461003b5780635a9b0b8914610045575b600080fd5b61004361007f565b005b61004d6101b5565b604080516001600160a01b03909516855260208501939093528383019190915215156060830152519081900360800190f35b6000546001600160a01b031633146100de576040805162461bcd60e51b815260206004820152601760248201527f496e73756666696369656e742070726976696c65676573000000000000000000604482015290519081900360640190fd5b600254600160a01b900460ff16156101275760405162461bcd60e51b81526004018080602001828103825260258152602001806101db6025913960400191505060405180910390fd5b60015460025460035460408051636571385b60e11b81523360048201526001600160a01b039384166024820152604481019290925251919092169163cae270b691606480830192600092919082900301818387803b15801561018857600080fd5b505af115801561019c573d6000803e3d6000fd5b50506002805460ff60a01b1916600160a01b1790555050565b6002546003546004546001600160a01b0383169260ff600160a01b909104169091929356fe436f6e747261637420636f756c642062652070726f636573736564206f6e6c79206f6e6365a2646970667358221220df38c5a2d56930fa92ca55a74099c3902d34a3a386fbc2cd245aee717491834e64736f6c637827302e362e392d646576656c6f702e323032302e352e32372b636f6d6d69742e39663430376665300058";

    public static final String FUNC_GETINFO = "getInfo";

    public static final String FUNC_SELL = "sell";

    @Deprecated
    protected STokenSell(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected STokenSell(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected STokenSell(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected STokenSell(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
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

    public RemoteFunctionCall<TransactionReceipt> sell() {
        final Function function = new Function(
                FUNC_SELL,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static STokenSell load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new STokenSell(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static STokenSell load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new STokenSell(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static STokenSell load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new STokenSell(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static STokenSell load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new STokenSell(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<STokenSell> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String executorAddress, String _seller, BigInteger _amount, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, executorAddress),
                new org.web3j.abi.datatypes.Address(160, _seller),
                new org.web3j.abi.datatypes.generated.Uint256(_amount),
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(STokenSell.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<STokenSell> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String executorAddress, String _seller, BigInteger _amount, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, executorAddress),
                new org.web3j.abi.datatypes.Address(160, _seller),
                new org.web3j.abi.datatypes.generated.Uint256(_amount),
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(STokenSell.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<STokenSell> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String executorAddress, String _seller, BigInteger _amount, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, executorAddress),
                new org.web3j.abi.datatypes.Address(160, _seller),
                new org.web3j.abi.datatypes.generated.Uint256(_amount),
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(STokenSell.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<STokenSell> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String executorAddress, String _seller, BigInteger _amount, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, executorAddress),
                new org.web3j.abi.datatypes.Address(160, _seller),
                new org.web3j.abi.datatypes.generated.Uint256(_amount),
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(STokenSell.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
