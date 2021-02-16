pragma solidity ^0.7.0;

import "./openzeppelin/mocks/ERC777Mock.sol";


contract ERC777Contract is ERC777Mock {

    constructor(
        address initialHolder,
        uint256 initialBalance,
        string memory name,
        string memory symbol,
        address[] memory defaultOperators
    ) ERC777Mock(initialHolder, initialBalance, name, symbol, defaultOperators) {
//        _mint(initialHolder, initialBalance, "", "");
    }
}
