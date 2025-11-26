package org.jala.university.application.service;

import org.jala.university.application.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto account);

    void updateAccount(AccountDto accountDto);

    List<AccountDto> getAllAccounts();

    void removeAccount(AccountDto account);

    AccountDto getAccountByNumber(String accountNumber);

}
