package org.jala.university.domain.repository;

import org.jala.university.commons.domain.repository.Repository;
import org.jala.university.domain.entity.Account;

import java.util.UUID;


public interface AccountRepository extends Repository<Account, UUID> {
    Account findByAccountNumber(String accountNumber);
}
