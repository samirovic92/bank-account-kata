package com.sg.bankaccountkata.application;

import com.sg.bankaccountkata.domaine.Account;
import com.sg.bankaccountkata.domaine.AccountRepository;
import com.sg.bankaccountkata.domaine.Operation;
import com.sg.bankaccountkata.domaine.exception.AccountNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor
public class OperationServiceImpl implements OperationService {

    private AccountRepository accountRepository;

    @Transactional
    @Override
    public void saveOperation(Operation operation, Long accountNumber) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (Objects.isNull(account)) {
            throw  new AccountNotFoundException(accountNumber);
        }
        account.addNewOperation(operation);
        accountRepository.save(account);
    }
}
