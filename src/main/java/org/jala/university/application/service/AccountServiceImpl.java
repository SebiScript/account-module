package org.jala.university.application.service;

import lombok.AllArgsConstructor;
import org.jala.university.application.dto.AccountDto;
import org.jala.university.application.mapper.AccountMapper;
import org.jala.university.domain.entity.Account;
import org.jala.university.domain.entity.AccountType;
import org.jala.university.domain.repository.AccountRepository;

import java.util.List;

@AllArgsConstructor
public final class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = accountMapper.mapFrom(accountDto);
        if (account.getType() == null) {
            account.setType(AccountType.SAVING);
        }
        Account saved = accountRepository.save(account);
        return accountMapper.mapTo(saved);
    }

    @Override
    public void updateAccount(AccountDto accountDto) {
        Account account = accountMapper.mapFrom(accountDto);
        accountRepository.save(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository
                .findAll()
                .stream()
                .map(accountMapper::mapTo)
                .toList();
    }

    @Override
    public void removeAccount(AccountDto account) {
        accountRepository.delete(accountMapper.mapFrom(account));
    }

    @Override
    public AccountDto getAccountByNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            return accountMapper.mapTo(account);
        }
        return null;
    }
}
