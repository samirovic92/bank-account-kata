package com.sg.bankaccountkata.application;

import com.sg.bankaccountkata.application.data.AccountStatement;
import com.sg.bankaccountkata.domaine.Account;
import com.sg.bankaccountkata.domaine.AccountRepository;
import com.sg.bankaccountkata.domaine.exception.AccountNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AccountStatementServiceImpl implements AccountStatementService {

    private AccountRepository accountRepository;

    @Override
    public AccountStatement getOperationsHistory(Long accountNumber) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (Objects.isNull(account)) {
            throw  new AccountNotFoundException(accountNumber);
        }
        return AccountStatement.from(account);
    }
}
