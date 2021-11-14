package com.sg.bankaccountkata.domaine;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public final class Operation {

    private Long id;
    private final Double amount;
    private final OperationType operationType;
    private final OffsetDateTime date;
    private Long accountId;

    public Operation(Double amount, OperationType operationType, OffsetDateTime date) {
        this.amount = amount;
        this.date = date;
        this.operationType = operationType;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

}
