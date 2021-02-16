#!/usr/bin/env bash

#rm contracts/compiled/*

solc --bin --abi --optimize --overwrite -o contracts/compiled/ contracts/*.sol

if [ -z "$(ls -A contracts/compiled)" ]; then
   echo "Compilation errors found"
   exit
fi

CONTRACTS=(
          ERC777HookCheck ERC777Contract
)
#          SToken STokenSell
#          STokenBuy STokenTransfer STokenExecutor

#COMMON_CONTRACTS=( ERC1820Registry )

for item in ${CONTRACTS[*]}
    do
        web3j generate solidity contracts/compiled/$item.bin contracts/compiled/$item.abi -p by.mrj.besu.token.contract -o '../../main/java/'
    done

#for item in ${COMMON_CONTRACTS[*]}
#    do
#        web3j solidity generate -a=contracts/compiled/$item.abi -b=contracts/compiled/$item.bin -o=../../main/java/ -p=by.mrj.besu.contract
#    done

