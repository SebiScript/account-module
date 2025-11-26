package org.jala.university.infrastructure.persistance;

import org.jala.university.domain.entity.Account;
import org.jala.university.domain.entity.AccountStatus;
import org.jala.university.domain.entity.Currency;

import java.util.*;

public final class AccountGenerator {

    private static final String ACCOUNT_STR = "Account";
    private static final int RANDOM_NAME_LEFT_LIMIT = 97;
    private static final int RANDOM_NAME_RIGHT_LIMIT = 122;
    private static final int RANDOM_NAME_BOUND = 5;
    private static final int RANDOM_ACCOUNT_LEFT_LIMIT = 48;
    private static final int RANDOM_ACCOUNT_RIGHT_LIMIT = 57;
    private static final int RANDOM_ACCOUNT_BOUND = 12;
    private static final int BALANCE_OFFSET = 1000;

    private AccountGenerator() {
    }

    public static Map<UUID, Account> generateRandomAccounts(int size) {
        Map<UUID, Account> accounts = new HashMap<>();
        for (int i = 0; i < size; i++) {
            Account account = buildRandomAccount();
            accounts.put(account.getId(), account);
        }
        return accounts;
    }

    private static Account buildRandomAccount() {
        List<Currency> currencies =
                List.of(Currency.values());
        List<AccountStatus> statusList =
                List.of(AccountStatus.values());
        return Account.builder()
                .id(UUID.randomUUID())
                .name(ACCOUNT_STR + " " + getRandomName())
                .balance(Math.random() + BALANCE_OFFSET)
                .accountNumber(radonAccountNumber())
                .currency(
                        currencies.get(getRandom().nextInt(currencies.size())))
                .status(
                        statusList.get(getRandom().nextInt(statusList.size())))
                .build();
    }

    private static String getRandomName() {
        int targetStringLength = getRandom().nextInt(RANDOM_NAME_BOUND);
        return getRandom()
                .ints(RANDOM_NAME_LEFT_LIMIT, RANDOM_NAME_RIGHT_LIMIT + 1)
                .limit(targetStringLength)
                .collect(
                        StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

    private static String radonAccountNumber() {
        return getRandom()
                .ints(RANDOM_ACCOUNT_LEFT_LIMIT, RANDOM_ACCOUNT_RIGHT_LIMIT + 1)
                .limit(RANDOM_ACCOUNT_BOUND)
                .collect(
                        StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

    private static Random getRandom() {
        return new Random();
    }
}
