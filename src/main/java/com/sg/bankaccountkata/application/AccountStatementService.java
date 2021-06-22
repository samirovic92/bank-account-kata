package com.sg.bankaccountkata.application;

import com.sg.bankaccountkata.application.data.AccountStatement;
import com.sg.bankaccountkata.domaine.exception.AccountNotFoundException;

public interface AccountStatementService {

    AccountStatement getOperationsHistory(Long accountNumber) throws AccountNotFoundException;
}
