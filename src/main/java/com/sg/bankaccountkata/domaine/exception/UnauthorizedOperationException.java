package com.sg.bankaccountkata.domaine.exception;

import com.sg.bankaccountkata.domaine.Operation;

public class UnauthorizedOperationException extends RuntimeException {

    private static final String MESSAGE = "Unauthorized operation %s on account %s";

    public UnauthorizedOperationException(Long accountNumber, Operation operation) {
        super(String.format(MESSAGE, operation.getOperationType().toString(), accountNumber));
    }

}
