package com.sg.bankaccountkata.application;

import com.sg.bankaccountkata.domaine.Operation;
import com.sg.bankaccountkata.domaine.exception.AccountNotFoundException;

public interface OperationService {

    void saveOperation(Operation operation, Long accountNumber) throws AccountNotFoundException;

}
