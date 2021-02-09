pragma solidity ^0.6.0;

import "./STokenExecutor.sol";

contract STokenTransfer {

    address private issuer;
    STokenExecutor private sGoldExecutor;

    address private from;
    address private to;
    uint private amount;
    bool private burned = false;

    constructor (address executorAddress, address _from, address _to, uint _amount) public {
        sGoldExecutor = STokenExecutor(executorAddress);

        issuer = msg.sender;
        from = _from;
        to = _to;
        amount = _amount;
    }

    modifier onlyOwner() {
        require(msg.sender == issuer, "Insufficient privileges");
        _;
    }

    function transfer() public onlyOwner {
        require(!burned, "Contract could be processed only once");

        sGoldExecutor.transfer(msg.sender, from, to, amount);

        burned = true;
    }

    // Retrieves contract info
    function getInfo() public view returns (address, address, uint, bool) {
        return (from, to, amount, burned);
    }

}
