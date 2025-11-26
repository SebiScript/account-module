package org.jala.university.infrastructure.persistance;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.jala.university.commons.infrastructure.persistance.CrudRepository;
import org.jala.university.domain.entity.Account;
import org.jala.university.domain.repository.AccountRepository;

import java.util.UUID;

public final class AccountRepositoryImpl extends CrudRepository<Account, UUID> implements AccountRepository {
    public AccountRepositoryImpl(EntityManager entityManager) {
        super(Account.class, entityManager);
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        try {
            return getEntityManager()
                    .createQuery("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber", Account.class)
                    .setParameter("accountNumber", accountNumber)
                    .getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }
}
