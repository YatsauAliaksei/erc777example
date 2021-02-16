## ERC777 contract usage example

----
#### Description:

This is simplest example code which uses [ERC777](https://docs.openzeppelin.com/contracts/3.x/api/token/erc777) contract and ERC777Hooks (Sender, Recipient) to transfer tokens and emits Hooks events.

Code does:

1. Deploys ERC1820 contract if not yet exist to private ethereum based blockchain.
2. Deploys*** ERC777 contract
3. Deploys*** ERC777Recipient, ERC777Sender contracts
4. Creates Client (ETH account) on demand
5. Makes a check that tokens were transferred to Client
6. Logs received events: 'TokensReceivedCalled', 'TokensToSendCalled'


Tested using locally running [Hyperledger Besu](https://besu.hyperledger.org/en/stable/) - [version](https://hub.docker.com/layers/hyperledger/besu/21.1/images/sha256-bd75bfbb2d0763f2576043620319fb840d224be8485867049c4252ec5e0b983d?context=explore)


Solidity contracts were taken from [Openzeppelin](https://github.com/OpenZeppelin/openzeppelin-contracts)

Web3j - as ethereum JSON-RPC java library

*** - creates New contract if address is not explicitly set in 'application.yml' file under 'netContracts/name/address' section
