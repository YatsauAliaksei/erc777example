package by.mrj.besu.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
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
public class ERC1820Registry extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5061033a806100206000396000f3fe608060405234801561001057600080fd5b50600436106100885760003560e01c8063a41e7d511161005b578063a41e7d51146101b7578063aabbb8ca146101ed578063b705676514610219578063f712f3e81461021957610088565b806329965a1d1461008d5780633d584063146100c55780635df8122f1461010757806365ba36c114610135575b600080fd5b6100c3600480360360608110156100a357600080fd5b506001600160a01b03813581169160208101359160409091013516610263565b005b6100eb600480360360208110156100db57600080fd5b50356001600160a01b031661029c565b604080516001600160a01b039092168252519081900360200190f35b6100c36004803603604081101561011d57600080fd5b506001600160a01b03813581169160200135166102a1565b6101a56004803603602081101561014b57600080fd5b81019060208101813564010000000081111561016657600080fd5b82018360208201111561017857600080fd5b8035906020019184600183028401116401000000008311171561019a57600080fd5b5090925090506102a5565b60408051918252519081900360200190f35b6100c3600480360360408110156101cd57600080fd5b5080356001600160a01b031690602001356001600160e01b0319166102a1565b6100eb6004803603604081101561020357600080fd5b506001600160a01b0381351690602001356102b1565b61024f6004803603604081101561022f57600080fd5b5080356001600160a01b031690602001356001600160e01b0319166102d7565b604080519115158252519081900360200190f35b6001600160a01b039283166000908152602081815260408083209483529390529190912080546001600160a01b03191691909216179055565b503390565b5050565b61486960f01b92915050565b6001600160a01b0391821660009081526020818152604080832093835292905220541690565b60009291505056fea26469706673582212202f2cc0a2339f60a6b89264c70572a1a9b2bce3dc686fdb7cdb9fdae5ea3cb62364736f6c637827302e362e392d646576656c6f702e323032302e352e32372b636f6d6d69742e39663430376665300058";

    public static final String FUNC_GETINTERFACEIMPLEMENTER = "getInterfaceImplementer";

    public static final String FUNC_GETMANAGER = "getManager";

    public static final String FUNC_IMPLEMENTSERC165INTERFACE = "implementsERC165Interface";

    public static final String FUNC_IMPLEMENTSERC165INTERFACENOCACHE = "implementsERC165InterfaceNoCache";

    public static final String FUNC_INTERFACEHASH = "interfaceHash";

    public static final String FUNC_SETINTERFACEIMPLEMENTER = "setInterfaceImplementer";

    public static final String FUNC_SETMANAGER = "setManager";

    public static final String FUNC_UPDATEERC165CACHE = "updateERC165Cache";

    public static final Event INTERFACEIMPLEMENTERSET_EVENT = new Event("InterfaceImplementerSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event MANAGERCHANGED_EVENT = new Event("ManagerChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected ERC1820Registry(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ERC1820Registry(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ERC1820Registry(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ERC1820Registry(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<InterfaceImplementerSetEventResponse> getInterfaceImplementerSetEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(INTERFACEIMPLEMENTERSET_EVENT, transactionReceipt);
        ArrayList<InterfaceImplementerSetEventResponse> responses = new ArrayList<InterfaceImplementerSetEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            InterfaceImplementerSetEventResponse typedResponse = new InterfaceImplementerSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.interfaceHash = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.implementer = (String) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<InterfaceImplementerSetEventResponse> interfaceImplementerSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, InterfaceImplementerSetEventResponse>() {
            @Override
            public InterfaceImplementerSetEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(INTERFACEIMPLEMENTERSET_EVENT, log);
                InterfaceImplementerSetEventResponse typedResponse = new InterfaceImplementerSetEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.interfaceHash = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.implementer = (String) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<InterfaceImplementerSetEventResponse> interfaceImplementerSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INTERFACEIMPLEMENTERSET_EVENT));
        return interfaceImplementerSetEventFlowable(filter);
    }

    public List<ManagerChangedEventResponse> getManagerChangedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(MANAGERCHANGED_EVENT, transactionReceipt);
        ArrayList<ManagerChangedEventResponse> responses = new ArrayList<ManagerChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ManagerChangedEventResponse typedResponse = new ManagerChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newManager = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ManagerChangedEventResponse> managerChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ManagerChangedEventResponse>() {
            @Override
            public ManagerChangedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(MANAGERCHANGED_EVENT, log);
                ManagerChangedEventResponse typedResponse = new ManagerChangedEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newManager = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ManagerChangedEventResponse> managerChangedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MANAGERCHANGED_EVENT));
        return managerChangedEventFlowable(filter);
    }

    public RemoteFunctionCall<String> getInterfaceImplementer(String account, byte[] interfaceHash) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETINTERFACEIMPLEMENTER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Bytes32(interfaceHash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getManager(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETMANAGER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> implementsERC165Interface(String account, byte[] interfaceId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_IMPLEMENTSERC165INTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> implementsERC165InterfaceNoCache(String account, byte[] interfaceId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_IMPLEMENTSERC165INTERFACENOCACHE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<byte[]> interfaceHash(String interfaceName) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_INTERFACEHASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(interfaceName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<TransactionReceipt> setInterfaceImplementer(String account, byte[] interfaceHash, String implementer) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETINTERFACEIMPLEMENTER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Bytes32(interfaceHash), 
                new org.web3j.abi.datatypes.Address(160, implementer)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setManager(String account, String newManager) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETMANAGER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.Address(160, newManager)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> updateERC165Cache(String account, byte[] interfaceId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATEERC165CACHE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ERC1820Registry load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC1820Registry(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ERC1820Registry load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC1820Registry(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ERC1820Registry load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ERC1820Registry(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ERC1820Registry load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ERC1820Registry(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ERC1820Registry> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ERC1820Registry.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ERC1820Registry> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ERC1820Registry.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<ERC1820Registry> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ERC1820Registry.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ERC1820Registry> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ERC1820Registry.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class InterfaceImplementerSetEventResponse extends BaseEventResponse {
        public String account;

        public byte[] interfaceHash;

        public String implementer;
    }

    public static class ManagerChangedEventResponse extends BaseEventResponse {
        public String account;

        public String newManager;
    }
}
