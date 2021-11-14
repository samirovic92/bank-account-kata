package com.sg.bankaccountkata.application;

import com.sg.bankaccountkata.domaine.Account;
import com.sg.bankaccountkata.domaine.AccountRepository;
import com.sg.bankaccountkata.domaine.Operation;
import com.sg.bankaccountkata.domaine.OperationType;
import com.sg.bankaccountkata.domaine.exception.AccountNotFoundException;
import com.sg.bankaccountkata.domaine.exception.UnauthorizedOperationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OperationServiceTest {

    private AccountRepository accountRepository;
    private OperationService operationService;
    @Captor
    private ArgumentCaptor<Account> accountCaptor;

    @BeforeEach
    public void setup() {
        accountRepository = mock(AccountRepository.class);
        operationService = new OperationServiceImpl(accountRepository);

    }

    @Test
    public void should_deposit_money() throws AccountNotFoundException {
        // Given
        OffsetDateTime now = OffsetDateTime.now();
        Account account = buildDefaultAccount();
        Operation operation = new Operation(
                100d,
                OperationType.DEPOSIT,
                now);

        when(accountRepository.findByAccountNumber(anyLong())).thenReturn(account);

        // When
        operationService.saveOperation(operation, 100l);

        // Then
        verify(accountRepository).save(accountCaptor.capture());

        Account accountCaptured = accountCaptor.getValue();
        assertEquals(accountCaptured.getBalance(), 300d);
        assertEquals(accountCaptured.getOperations().size(), 1);
        assertEquals(accountCaptured.getOperations().get(0).getOperationType(), OperationType.DEPOSIT);
    }

    @Test
    public void should_withdrawal_money() throws AccountNotFoundException {
        // Given
        OffsetDateTime now = OffsetDateTime.now();
        Account account = buildDefaultAccount();
        Operation operation = new Operation(
                100d,
                OperationType.WITHDRAWAL,
                now);
        when(accountRepository.findByAccountNumber(anyLong())).thenReturn(account);

        // When
        operationService.saveOperation(operation, 100l);

        // Then
        verify(accountRepository).save(accountCaptor.capture());

        Account accountCaptured = accountCaptor.getValue();
        assertEquals(accountCaptured.getBalance(), 100d);
        assertEquals(accountCaptured.getOperations().size(), 1);
        assertEquals(accountCaptured.getOperations().get(0).getOperationType(), OperationType.WITHDRAWAL);
    }

    @Test
    public void should_not_withdrawal_money_with_invalid_amount() {
        // Given
        OffsetDateTime now = OffsetDateTime.now();
        Account account = buildDefaultAccount();

        Operation operation = new Operation(
                300d,
                OperationType.WITHDRAWAL,
                now);

        when(accountRepository.findByAccountNumber(anyLong())).thenReturn(account);

        // When
        assertThatThrownBy(() -> operationService.saveOperation(operation, 100l))
                .isInstanceOf(UnauthorizedOperationException.class);

        // Then
        verify(accountRepository, never()).save(any(Account.class));
    }

    private Account buildDefaultAccount() {
        return Account.builder()
                .id(1l)
                .accountNumber(100l)
                .balance(200d)
                .operations(new ArrayList<>())
                .build();
    }
}
