package com.sg.bankaccountkata.application.data;

import com.sg.bankaccountkata.domaine.Account;
import com.sg.bankaccountkata.domaine.Operation;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AccountStatement {

    private Long clientId;
    private Long accountNumber;
    private Double balance;
    private List<Operation> operations;

    public static AccountStatement from(Account account) {
        return AccountStatement.builder()
                .clientId(account.getClientId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .operations(account.getOperations())
                .build();
    }
}
