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
public class STokenTransfer extends Contract {
    public static final String BINARY = "60806040526005805460ff1916905534801561001a57600080fd5b5060405161030a38038061030a8339818101604052608081101561003d57600080fd5b50805160208201516040830151606090930151600180546001600160a01b039485166001600160a01b0319918216179091556000805482163317905560028054938516938216939093179092556003805493909416929091169190911790915560045561025b806100af6000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c80635a9b0b891461003b5780638a4068dd14610077575b600080fd5b610043610081565b604080516001600160a01b039586168152939094166020840152828401919091521515606082015290519081900360800190f35b61007f6100a6565b005b6002546003546004546005546001600160a01b03938416949390921692909160ff1690565b6000546001600160a01b03163314610105576040805162461bcd60e51b815260206004820152601760248201527f496e73756666696369656e742070726976696c65676573000000000000000000604482015290519081900360640190fd5b60055460ff16156101475760405162461bcd60e51b81526004018080602001828103825260258152602001806101dc6025913960400191505060405180910390fd5b6001546002546003546004805460408051633c6340f360e21b815233938101939093526001600160a01b039485166024840152928416604483015260648201529051919092169163f18d03cc91608480830192600092919082900301818387803b1580156101b457600080fd5b505af11580156101c8573d6000803e3d6000fd5b50506005805460ff19166001179055505056fe436f6e747261637420636f756c642062652070726f636573736564206f6e6c79206f6e6365a26469706673582212203a7e6f4d3b0437bd4859bdf714f15371f53155ef695aa9956ee43e00caca0ee964736f6c637827302e362e392d646576656c6f702e323032302e352e32372b636f6d6d69742e39663430376665300058";

    public static final String FUNC_GETINFO = "getInfo";

    public static final String FUNC_TRANSFER = "transfer";

    @Deprecated
    protected STokenTransfer(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected STokenTransfer(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected STokenTransfer(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected STokenTransfer(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Tuple4<String, String, BigInteger, Boolean>> getInfo() {
        final Function function = new Function(FUNC_GETINFO,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple4<String, String, BigInteger, Boolean>>(function,
                new Callable<Tuple4<String, String, BigInteger, Boolean>>() {
                    @Override
                    public Tuple4<String, String, BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, String, BigInteger, Boolean>(
                                (String) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (Boolean) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> transfer() {
        final Function function = new Function(
                FUNC_TRANSFER,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static STokenTransfer load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new STokenTransfer(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static STokenTransfer load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new STokenTransfer(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static STokenTransfer load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new STokenTransfer(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static STokenTransfer load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new STokenTransfer(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<STokenTransfer> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String executorAddress, String _from, String _to, BigInteger _amount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, executorAddress),
                new org.web3j.abi.datatypes.Address(160, _from),
                new org.web3j.abi.datatypes.Address(160, _to),
                new org.web3j.abi.datatypes.generated.Uint256(_amount)));
        return deployRemoteCall(STokenTransfer.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<STokenTransfer> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String executorAddress, String _from, String _to, BigInteger _amount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, executorAddress),
                new org.web3j.abi.datatypes.Address(160, _from),
                new org.web3j.abi.datatypes.Address(160, _to),
                new org.web3j.abi.datatypes.generated.Uint256(_amount)));
        return deployRemoteCall(STokenTransfer.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<STokenTransfer> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String executorAddress, String _from, String _to, BigInteger _amount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, executorAddress),
                new org.web3j.abi.datatypes.Address(160, _from),
                new org.web3j.abi.datatypes.Address(160, _to),
                new org.web3j.abi.datatypes.generated.Uint256(_amount)));
        return deployRemoteCall(STokenTransfer.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<STokenTransfer> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String executorAddress, String _from, String _to, BigInteger _amount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, executorAddress),
                new org.web3j.abi.datatypes.Address(160, _from),
                new org.web3j.abi.datatypes.Address(160, _to),
                new org.web3j.abi.datatypes.generated.Uint256(_amount)));
        return deployRemoteCall(STokenTransfer.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
