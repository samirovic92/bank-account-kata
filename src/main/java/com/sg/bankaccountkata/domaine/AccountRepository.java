package com.sg.bankaccountkata.domaine;

public interface AccountRepository{

    void save(Account account);

    Account findByAccountNumber(Long accountNumber);
}
