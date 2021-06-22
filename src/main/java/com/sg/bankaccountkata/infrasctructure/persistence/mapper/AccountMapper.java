package com.sg.bankaccountkata.infrasctructure.persistence.mapper;

import com.sg.bankaccountkata.domaine.Account;
import com.sg.bankaccountkata.infrasctructure.persistence.AccountEntity;
import com.sg.bankaccountkata.infrasctructure.persistence.ClientEntity;

import java.util.Objects;

public class AccountMapper {

    public static AccountEntity from(Account account) {
        return AccountEntity.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .client(ClientEntity.builder().id(account.getClientId()).build())
                .operations(OperationMapper.from(account.getOperations()))
                .balance(account.getBalance())
                .build();
    }

    public static Account to(AccountEntity account) {
        return Objects.nonNull(account) ? Account.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .clientId(account.getClient().getId())
                .operations(OperationMapper.to(account.getOperations()))
                .balance(account.getBalance())
                .build() : null;
    }
}
