pragma solidity ^0.6.0;

import "./openzeppelin/token/ERC777/ERC777.sol";

contract SToken is ERC777 {

    address public owner;
    address[] private allowed;

    constructor(address[] memory defaultOperators, address erc1820Address)
    ERC777("SGold", "SwGld", defaultOperators, erc1820Address) public {

        owner = _msgSender();
        allowed.push(owner);
    }

    function mint(address mintTo, uint amount) public allowedAddresses {
        _mint(owner, mintTo, amount, "", "");
    }

    function approveFor(address holder, address spender, uint256 value) public allowedAddresses {
        _approve(holder, spender, value);
    }

    function addAllowed(address newAllowed) public onlyOwner {
        allowed.push(newAllowed);
    }

    modifier allowedAddresses() {
        address currentAddress = msg.sender;
        for (uint i = 0; i < allowed.length; i++) {
            if (allowed[i] == currentAddress) {
                _;
                return;
            }
        }

        revert("Insufficient privileges");
        _;
    }

    modifier onlyOwner() {
        require(msg.sender == owner, "Insufficient privileges");
        _;
    }
}
