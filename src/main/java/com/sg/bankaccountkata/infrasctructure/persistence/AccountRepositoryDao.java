package com.sg.bankaccountkata.infrasctructure.persistence;

import com.sg.bankaccountkata.domaine.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepositoryDao extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByAccountNumber(Long accountNumber);
}
