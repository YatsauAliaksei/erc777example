pragma solidity ^0.6.0;

import "./STokenExecutor.sol";

contract STokenSell {

    address private issuer;
    STokenExecutor private sGoldExecutor;

    address private seller;
    bool private burned = false;
    uint private amount;
    uint private price;

    constructor (address executorAddress, address _seller, uint _amount, uint _price) public {
        sGoldExecutor = STokenExecutor(executorAddress);

        issuer = msg.sender;
        seller = _seller;
        amount = _amount;
        price = _price;
    }

    modifier onlyOwner() {
        require(msg.sender == issuer, "Insufficient privileges");
        _;
    }

    function sell() public onlyOwner {
        require(!burned, "Contract could be processed only once");

        sGoldExecutor.sell(msg.sender, seller, amount);

        burned = true;
    }

    // Retrieves contract info
    function getInfo() public view returns (address, uint, uint, bool) {
        return (seller, amount, price, burned);
    }

}
