package com.sg.bankaccountkata.infrasctructure.persistence.mapper;

import com.sg.bankaccountkata.domaine.Operation;
import com.sg.bankaccountkata.infrasctructure.persistence.AccountEntity;
import com.sg.bankaccountkata.infrasctructure.persistence.OperationEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OperationMapper {

    public static List<OperationEntity> from(List<Operation> operations) {
        return operations.stream()
                .map(OperationMapper::from)
                .collect(Collectors.toList());
    }

    public static OperationEntity from(Operation operation) {
        return  OperationEntity.builder()
                .id(operation.getId())
                .operationType(operation.getOperationType())
                .amount(operation.getAmount())
                .date(operation.getDate())
                .account(AccountEntity.builder().id(operation.getAccountId()).build())
                .build();
    }

    public static List<Operation> to(List<OperationEntity> operations) {
        return operations.stream()
                .map(OperationMapper::to)
                .collect(Collectors.toList());
    }

    public static Operation to(OperationEntity operation) {
        return  Operation.builder()
                .id(operation.getId())
                .operationType(operation.getOperationType())
                .amount(operation.getAmount())
                .accountId(operation.getAccount().getId())
                .date(operation.getDate())
                .build();
    }
}
