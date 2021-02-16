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
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
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
 * <p>Generated with web3j version 4.8.1.
 */
@SuppressWarnings("rawtypes")
public class ERC777HookCheck extends Contract {
    public static final String BINARY = "60806040526001805462010000600160b01b031916751820a4b7618bde71dce8cdc73aab6c95905fad24000017905534801561003a57600080fd5b50610da58061004a6000396000f3fe608060405234801561001057600080fd5b50600436106100a85760003560e01c806375ab97821161007157806375ab97821461036f578063a8badaa514610455578063c97e18fc1461047b578063d2de64741461049a578063e0eb2180146104c0578063e1ecbd30146104e6576100a8565b806223de29146100ad578063249cb3fa146101955780633836ef89146101d357806344d17187146102975780634e4ae5a514610350575b600080fd5b610193600480360360c08110156100c357600080fd5b6001600160a01b03823581169260208101358216926040820135909216916060820135919081019060a081016080820135600160201b81111561010557600080fd5b82018360208201111561011757600080fd5b803590602001918460018302840111600160201b8311171561013857600080fd5b919390929091602081019035600160201b81111561015557600080fd5b82018360208201111561016757600080fd5b803590602001918460018302840111600160201b8311171561018857600080fd5b50909250905061050c565b005b6101c1600480360360408110156101ab57600080fd5b50803590602001356001600160a01b031661072a565b60408051918252519081900360200190f35b610193600480360360808110156101e957600080fd5b6001600160a01b03823581169260208101359091169160408201359190810190608081016060820135600160201b81111561022357600080fd5b82018360208201111561023557600080fd5b803590602001918460018302840111600160201b8311171561025657600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955061079f945050505050565b610193600480360360608110156102ad57600080fd5b6001600160a01b0382351691602081013591810190606081016040820135600160201b8111156102dc57600080fd5b8201836020820111156102ee57600080fd5b803590602001918460018302840111600160201b8311171561030f57600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955061087e945050505050565b6101936004803603602081101561036657600080fd5b5035151561094c565b610193600480360360c081101561038557600080fd5b6001600160a01b03823581169260208101358216926040820135909216916060820135919081019060a081016080820135600160201b8111156103c757600080fd5b8201836020820111156103d957600080fd5b803590602001918460018302840111600160201b831117156103fa57600080fd5b919390929091602081019035600160201b81111561041757600080fd5b82018360208201111561042957600080fd5b803590602001918460018302840111600160201b8311171561044a57600080fd5b50909250905061095f565b6101936004803603602081101561046b57600080fd5b50356001600160a01b0316610b78565b6101936004803603602081101561049157600080fd5b50351515610c14565b610193600480360360208110156104b057600080fd5b50356001600160a01b0316610c2e565b610193600480360360208110156104d657600080fd5b50356001600160a01b0316610c77565b610193600480360360208110156104fc57600080fd5b50356001600160a01b0316610cbc565b600154610100900460ff161561052157600080fd5b600061052b610d3d565b90506000816001600160a01b03166370a082318a6040518263ffffffff1660e01b815260040180826001600160a01b0316815260200191505060206040518083038186803b15801561057c57600080fd5b505afa158015610590573d6000803e3d6000fd5b505050506040513d60208110156105a657600080fd5b5051604080516370a0823160e01b81526001600160a01b038b811660048301529151929350600092918516916370a0823191602480820192602092909190829003018186803b1580156105f857600080fd5b505afa15801561060c573d6000803e3d6000fd5b505050506040513d602081101561062257600080fd5b810190808051906020019092919050505090507f47e915878c47f3ec4d7ff646a2becb229f64fd2abe4d2b5e2bb4275b0cf50d4e8b8b8b8b8b8b8b8b8b8b8b604051808c6001600160a01b031681526020018b6001600160a01b031681526020018a6001600160a01b031681526020018981526020018060200180602001866001600160a01b0316815260200185815260200184815260200183810383528a8a82818152602001925080828437600083820152601f01601f191690910184810383528881526020019050888880828437600083820152604051601f909101601f19169092018290039f50909d5050505050505050505050505050a15050505050505050505050565b6000828152602081815260408083206001600160a01b038516845290915281205460ff16610759576000610798565b604051602001808073455243313832305f4143434550545f4d4147494360601b8152506014019050604051602081830303815290604052805190602001205b9392505050565b836001600160a01b0316639bd9bbc68484846040518463ffffffff1660e01b815260040180846001600160a01b0316815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b838110156108125781810151838201526020016107fa565b50505050905090810190601f16801561083f5780820380516001836020036101000a031916815260200191505b50945050505050600060405180830381600087803b15801561086057600080fd5b505af1158015610874573d6000803e3d6000fd5b5050505050505050565b6040805163fe9d930360e01b815260048101848152602482019283528351604483015283516001600160a01b0387169363fe9d9303938793879390929160640190602085019080838360005b838110156108e25781810151838201526020016108ca565b50505050905090810190601f16801561090f5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561092f57600080fd5b505af1158015610943573d6000803e3d6000fd5b50505050505050565b6001805460ff1916911515919091179055565b60015460ff161561096f57600080fd5b6000610979610d3d565b90506000816001600160a01b03166370a082318a6040518263ffffffff1660e01b815260040180826001600160a01b0316815260200191505060206040518083038186803b1580156109ca57600080fd5b505afa1580156109de573d6000803e3d6000fd5b505050506040513d60208110156109f457600080fd5b5051604080516370a0823160e01b81526001600160a01b038b811660048301529151929350600092918516916370a0823191602480820192602092909190829003018186803b158015610a4657600080fd5b505afa158015610a5a573d6000803e3d6000fd5b505050506040513d6020811015610a7057600080fd5b810190808051906020019092919050505090507faa3e88aca472e90221daf7d3d601abafb62b120319089d7a2c2f63588da855298b8b8b8b8b8b8b8b8b8b8b604051808c6001600160a01b031681526020018b6001600160a01b031681526020018a6001600160a01b031681526020018981526020018060200180602001866001600160a01b0316815260200185815260200184815260200183810383528a8a82818152602001925080828437600083820152601f01601f191690910184810383528881526020019050888880828437600083820152604051601f909101601f19169092018290039f50909d5050505050505050505050505050a15050505050505050505050565b600154604080516329965a1d60e01b81523060048201527fb281fc8c12954d22544db45de3159a39272895b169a852b314f9cc762e44c53b60248201526001600160a01b038481166044830152915162010000909304909116916329965a1d9160648082019260009290919082900301818387803b158015610bf957600080fd5b505af1158015610c0d573d6000803e3d6000fd5b5050505050565b600180549115156101000261ff0019909216919091179055565b610c587f29ddb589b1fb5fc7cf394961c1adf5f8c6454761adf795e67fe149f658abe89582610d41565b306001600160a01b038216811415610c7357610c7381610cbc565b5050565b610ca17fb281fc8c12954d22544db45de3159a39272895b169a852b314f9cc762e44c53b82610d41565b306001600160a01b038216811415610c7357610c7381610b78565b600154604080516329965a1d60e01b81523060048201527f29ddb589b1fb5fc7cf394961c1adf5f8c6454761adf795e67fe149f658abe89560248201526001600160a01b038481166044830152915162010000909304909116916329965a1d9160648082019260009290919082900301818387803b158015610bf957600080fd5b3390565b6000918252602082815260408084206001600160a01b0390931684529190529020805460ff1916600117905556fea2646970667358221220adbdc36e49f2fbfe77a3c8719043f4e74819db2272d2653589c622571f9802cd64736f6c63430007050033";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_CANIMPLEMENTINTERFACEFORADDRESS = "canImplementInterfaceForAddress";

    public static final String FUNC_RECIPIENTFOR = "recipientFor";

    public static final String FUNC_REGISTERRECIPIENT = "registerRecipient";

    public static final String FUNC_REGISTERSENDER = "registerSender";

    public static final String FUNC_SEND = "send";

    public static final String FUNC_SENDERFOR = "senderFor";

    public static final String FUNC_SETSHOULDREVERTRECEIVE = "setShouldRevertReceive";

    public static final String FUNC_SETSHOULDREVERTSEND = "setShouldRevertSend";

    public static final String FUNC_TOKENSRECEIVED = "tokensReceived";

    public static final String FUNC_TOKENSTOSEND = "tokensToSend";

    public static final Event BEFORETOKENTRANSFER_EVENT = new Event("BeforeTokenTransfer", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event TOKENSRECEIVEDCALLED_EVENT = new Event("TokensReceivedCalled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TOKENSTOSENDCALLED_EVENT = new Event("TokensToSendCalled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected ERC777HookCheck(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ERC777HookCheck(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ERC777HookCheck(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ERC777HookCheck(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<BeforeTokenTransferEventResponse> getBeforeTokenTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(BEFORETOKENTRANSFER_EVENT, transactionReceipt);
        ArrayList<BeforeTokenTransferEventResponse> responses = new ArrayList<BeforeTokenTransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BeforeTokenTransferEventResponse typedResponse = new BeforeTokenTransferEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BeforeTokenTransferEventResponse> beforeTokenTransferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, BeforeTokenTransferEventResponse>() {
            @Override
            public BeforeTokenTransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(BEFORETOKENTRANSFER_EVENT, log);
                BeforeTokenTransferEventResponse typedResponse = new BeforeTokenTransferEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Flowable<BeforeTokenTransferEventResponse> beforeTokenTransferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BEFORETOKENTRANSFER_EVENT));
        return beforeTokenTransferEventFlowable(filter);
    }

    public List<TokensReceivedCalledEventResponse> getTokensReceivedCalledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TOKENSRECEIVEDCALLED_EVENT, transactionReceipt);
        ArrayList<TokensReceivedCalledEventResponse> responses = new ArrayList<TokensReceivedCalledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TokensReceivedCalledEventResponse typedResponse = new TokensReceivedCalledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.from = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.to = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.token = (String) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse.fromBalance = (BigInteger) eventValues.getNonIndexedValues().get(7).getValue();
            typedResponse.toBalance = (BigInteger) eventValues.getNonIndexedValues().get(8).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TokensReceivedCalledEventResponse> tokensReceivedCalledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TokensReceivedCalledEventResponse>() {
            @Override
            public TokensReceivedCalledEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TOKENSRECEIVEDCALLED_EVENT, log);
                TokensReceivedCalledEventResponse typedResponse = new TokensReceivedCalledEventResponse();
                typedResponse.log = log;
                typedResponse.operator = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.from = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.to = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse.token = (String) eventValues.getNonIndexedValues().get(6).getValue();
                typedResponse.fromBalance = (BigInteger) eventValues.getNonIndexedValues().get(7).getValue();
                typedResponse.toBalance = (BigInteger) eventValues.getNonIndexedValues().get(8).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TokensReceivedCalledEventResponse> tokensReceivedCalledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TOKENSRECEIVEDCALLED_EVENT));
        return tokensReceivedCalledEventFlowable(filter);
    }

    public List<TokensToSendCalledEventResponse> getTokensToSendCalledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TOKENSTOSENDCALLED_EVENT, transactionReceipt);
        ArrayList<TokensToSendCalledEventResponse> responses = new ArrayList<TokensToSendCalledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TokensToSendCalledEventResponse typedResponse = new TokensToSendCalledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.from = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.to = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.token = (String) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse.fromBalance = (BigInteger) eventValues.getNonIndexedValues().get(7).getValue();
            typedResponse.toBalance = (BigInteger) eventValues.getNonIndexedValues().get(8).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TokensToSendCalledEventResponse> tokensToSendCalledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TokensToSendCalledEventResponse>() {
            @Override
            public TokensToSendCalledEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TOKENSTOSENDCALLED_EVENT, log);
                TokensToSendCalledEventResponse typedResponse = new TokensToSendCalledEventResponse();
                typedResponse.log = log;
                typedResponse.operator = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.from = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.to = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse.token = (String) eventValues.getNonIndexedValues().get(6).getValue();
                typedResponse.fromBalance = (BigInteger) eventValues.getNonIndexedValues().get(7).getValue();
                typedResponse.toBalance = (BigInteger) eventValues.getNonIndexedValues().get(8).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TokensToSendCalledEventResponse> tokensToSendCalledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TOKENSTOSENDCALLED_EVENT));
        return tokensToSendCalledEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> burn(String token, BigInteger amount, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BURN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token), 
                new org.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<byte[]> canImplementInterfaceForAddress(byte[] interfaceHash, String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CANIMPLEMENTINTERFACEFORADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(interfaceHash), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<TransactionReceipt> recipientFor(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RECIPIENTFOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> registerRecipient(String recipient) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REGISTERRECIPIENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, recipient)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> registerSender(String sender) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REGISTERSENDER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> send(String token, String to, BigInteger amount, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SEND, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> senderFor(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SENDERFOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setShouldRevertReceive(Boolean shouldRevert) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETSHOULDREVERTRECEIVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(shouldRevert)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setShouldRevertSend(Boolean shouldRevert) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETSHOULDREVERTSEND, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(shouldRevert)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> tokensReceived(String operator, String from, String to, BigInteger amount, byte[] userData, byte[] operatorData) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TOKENSRECEIVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator), 
                new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.web3j.abi.datatypes.DynamicBytes(userData), 
                new org.web3j.abi.datatypes.DynamicBytes(operatorData)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> tokensToSend(String operator, String from, String to, BigInteger amount, byte[] userData, byte[] operatorData) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TOKENSTOSEND, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator), 
                new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.web3j.abi.datatypes.DynamicBytes(userData), 
                new org.web3j.abi.datatypes.DynamicBytes(operatorData)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ERC777HookCheck load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC777HookCheck(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ERC777HookCheck load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC777HookCheck(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ERC777HookCheck load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ERC777HookCheck(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ERC777HookCheck load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ERC777HookCheck(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ERC777HookCheck> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ERC777HookCheck.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ERC777HookCheck> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ERC777HookCheck.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<ERC777HookCheck> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ERC777HookCheck.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ERC777HookCheck> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ERC777HookCheck.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class BeforeTokenTransferEventResponse extends BaseEventResponse {
    }

    public static class TokensReceivedCalledEventResponse extends BaseEventResponse {
        public String operator;

        public String from;

        public String to;

        public BigInteger amount;

        public byte[] data;

        public byte[] operatorData;

        public String token;

        public BigInteger fromBalance;

        public BigInteger toBalance;
    }

    public static class TokensToSendCalledEventResponse extends BaseEventResponse {
        public String operator;

        public String from;

        public String to;

        public BigInteger amount;

        public byte[] data;

        public byte[] operatorData;

        public String token;

        public BigInteger fromBalance;

        public BigInteger toBalance;
    }
}
