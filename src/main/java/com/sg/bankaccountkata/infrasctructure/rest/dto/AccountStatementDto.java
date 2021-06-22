package com.sg.bankaccountkata.infrasctructure.rest.dto;


import com.sg.bankaccountkata.application.data.AccountStatement;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AccountStatementDto {

    private Long clientId;
    private Long accountNumber;
    private Double balance;
    private List<OperationDto> operations;

    public static AccountStatementDto from(AccountStatement accountStatement) {
        return AccountStatementDto.builder()
                .clientId(accountStatement.getClientId())
                .accountNumber(accountStatement.getAccountNumber())
                .balance(accountStatement.getBalance())
                .operations(OperationDto.from(accountStatement.getOperations()))
                .build();
    }
}
