package com.sg.bankaccountkata.domaine;

import com.sg.bankaccountkata.domaine.exception.UnauthorizedOperationException;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Account {

    private Long id;
    private Long accountNumber;
    private Double balance;
    private Long clientId;
    private List<Operation> operations;

    public void addNewOperation(Operation operation) {

        double newBalance = calculateNewBalance(operation);

        if(!operationIsAllowed(newBalance)) {
            throw new UnauthorizedOperationException(accountNumber, operation);
        }
        operation.addAccountId(id);
        balance = newBalance;
        operations.add(operation);
    }

    private double calculateNewBalance(Operation operation) {
        switch (operation.getOperationType()) {
            case DEPOSIT:
                return balance + operation.getAmount();
            case WITHDRAWAL:
                return balance - operation.getAmount();
            default:
                return balance;
        }
    }

    private boolean operationIsAllowed(Double newBalance) {
        return newBalance >= 0;
    }
}
