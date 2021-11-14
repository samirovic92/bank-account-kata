package com.sg.bankaccountkata.application;

import com.sg.bankaccountkata.application.data.AccountStatement;
import com.sg.bankaccountkata.domaine.Account;
import com.sg.bankaccountkata.domaine.AccountRepository;
import com.sg.bankaccountkata.domaine.Operation;
import com.sg.bankaccountkata.domaine.OperationType;
import com.sg.bankaccountkata.domaine.exception.AccountNotFoundException;
import com.sg.bankaccountkata.domaine.exception.UnauthorizedOperationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class AccountStatementServiceTest {

    private AccountRepository accountRepository;
    private AccountStatementService accountStatementService;

    @BeforeEach
    public void setup() {
        accountRepository = mock(AccountRepository.class);
        accountStatementService = new AccountStatementServiceImpl(accountRepository);
    }

    @Test
    public void should_retrieve_history() throws AccountNotFoundException {
        // Given
        final Long accountNumber = 2500l;
        final Long clientId = 1000l;
        Operation operation = new Operation(
                100d,
                OperationType.DEPOSIT,
                OffsetDateTime.now());

        Account account = Account.builder()
                .id(1l)
                .clientId(clientId)
                .accountNumber(accountNumber)
                .balance(200d)
                .operations(List.of(operation))
                .build();

        when(accountRepository.findByAccountNumber(anyLong())).thenReturn(account);

        // when
        AccountStatement accountStatement = accountStatementService.getOperationsHistory(accountNumber);

        // Then
        verify(accountRepository).findByAccountNumber(accountNumber);
        assertEquals(accountStatement.getAccountNumber(), accountNumber);
        assertEquals(accountStatement.getClientId(), clientId);
        assertEquals(accountStatement.getOperations(), List.of(operation));
    }

    @Test
    public void should_throw_exception_if_accountNumber_not_exist() {
        // Given
        final Long accountNumber = 2500l;
        when(accountRepository.findByAccountNumber(anyLong())).thenReturn(null);

        // When
        assertThatThrownBy(() -> accountStatementService.getOperationsHistory(accountNumber))
                .isInstanceOf(AccountNotFoundException.class);
    }
}
