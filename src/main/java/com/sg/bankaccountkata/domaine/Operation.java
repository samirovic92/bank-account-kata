package com.sg.bankaccountkata.domaine;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Builder
@Getter
public class Operation {

    private Long id;
    private Double amount;
    private OffsetDateTime date;
    private OperationType operationType;
    private Long accountId;

    public void addAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
