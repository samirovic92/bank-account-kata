package com.sg.bankaccountkata.domaine.exception;

public class AccountNotFoundException extends Exception {

    private static final String MESSAGE = "the account with number %s not found";

    public AccountNotFoundException(Long accountNumber) {
        super(String.format(MESSAGE, accountNumber));
    }
}
