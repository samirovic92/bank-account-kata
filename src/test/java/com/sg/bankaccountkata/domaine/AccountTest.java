package com.sg.bankaccountkata.domaine;

import com.sg.bankaccountkata.domaine.exception.UnauthorizedOperationException;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTest {

    @Test
    public void should_add_New_operation() {
        // Given
        Operation operation = new Operation(120d, OperationType.DEPOSIT, OffsetDateTime.now());
        Account account = Account.builder().operations(new ArrayList()).balance(0d).build();

        // When
        account.addNewOperation(operation);

        // Then
        assertEquals(account.getOperations().size(), 1);
    }

    @Test
    public void should_not_add_New_operation() {
        // Given
        Operation operation = new Operation(120d, OperationType.WITHDRAWAL, OffsetDateTime.now());
        Account account = Account.builder()
                .accountNumber(1200l)
                .operations(new ArrayList())
                .balance(0d).build();

        // When
        UnauthorizedOperationException thrown = assertThrows(UnauthorizedOperationException.class, () -> {
            account.addNewOperation(operation);
        });

        // Then
        assertEquals(thrown.getMessage(),  "Unauthorized operation WITHDRAWAL on account 1200");
    }

}
