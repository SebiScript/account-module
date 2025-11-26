package org.jala.university.infrastructure.persistance;

import org.jala.university.domain.entity.Account;
import org.jala.university.domain.repository.AccountRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class AccountRepositoryMock implements AccountRepository {

    private static final int RANDOM_ACCOUNTS_SIZE = 10;
    private final Map<UUID, Account> accounts = new HashMap<>();

    public AccountRepositoryMock() {
        //Populate sample data
        accounts.putAll(AccountGenerator.generateRandomAccounts(RANDOM_ACCOUNTS_SIZE));
    }

    @Override
    public Account findById(UUID id) {
        return accounts.get(id);
    }

    @Override
    public List<Account> findAll() {
        return accounts.values().stream().toList();
    }

    @Override
    public Account save(Account account) {
        if (account.getId() == null) {
            account.setId(UUID.randomUUID());
        }
        accounts.put(account.getId(), account);
        return account;
    }

    @Override
    public void delete(Account account) {
        accounts.remove(account.getId());
    }

    @Override
    public void deleteById(UUID uuid) {
        accounts.remove(uuid);
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        return accounts.values().stream().filter(a -> a.getAccountNumber().equals(accountNumber))
                .findFirst().orElse(null);
    }
}
