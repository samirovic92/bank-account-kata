package com.sg.bankaccountkata.infrasctructure.persistence;

import com.sg.bankaccountkata.domaine.Account;
import com.sg.bankaccountkata.domaine.AccountRepository;
import com.sg.bankaccountkata.infrasctructure.persistence.mapper.AccountMapper;
import org.springframework.stereotype.Component;

@Component
public class JpaAccountRepository implements AccountRepository {

    private AccountRepositoryDao accountRepositoryDao;

    public JpaAccountRepository(AccountRepositoryDao accountRepositoryDao) {
        this.accountRepositoryDao = accountRepositoryDao;
    }

    @Override
    public void save(Account account) {
        accountRepositoryDao.save(AccountMapper.from(account));
    }

    @Override
    public Account findByAccountNumber(Long accountNumber) {
        AccountEntity accountEntity = accountRepositoryDao.findByAccountNumber(accountNumber);
        return AccountMapper.to(accountEntity);
    }
}
