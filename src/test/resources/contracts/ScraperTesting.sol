pragma solidity ^0.6.0;


contract ScraperTesting {

    uint private amount;
    uint private price;
    address private issuer;

    constructor (uint _amount, uint _price) public {
        issuer = msg.sender;
        amount = _amount;
        price = _price;
    }

    modifier onlyOwner() {
        require(msg.sender == issuer, "Insufficient privileges");
        _;
    }

    function submit(string memory userData, bytes memory clientData) public onlyOwner {
        amount = 0;
        price = 0;
    }

    function newAmount(uint _amount) public onlyOwner {
        amount = _amount;
    }

    function newPrice(uint _price) public onlyOwner {
        price = _price;
    }

    // Retrieves contract info
    function getInfo() public view returns (uint, uint) {
        return (amount, price);
    }

}
