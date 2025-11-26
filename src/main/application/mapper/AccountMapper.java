package org.jala.university.application.mapper;

import org.jala.university.application.dto.AccountDto;
import org.jala.university.commons.application.mapper.Mapper;
import org.jala.university.domain.entity.Account;

public class AccountMapper implements Mapper<Account, AccountDto> {

    public static AccountMapper getInstance() {
        return new AccountMapper();
    }

    @Override
    public final AccountDto mapTo(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .name(account.getName())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .status(account.getStatus())
                .build();
    }

    @Override
    public final Account mapFrom(AccountDto accountDto) {
        return Account.builder()
                .id(accountDto.getId())
                .name(accountDto.getName())
                .accountNumber(accountDto.getAccountNumber())
                .balance(accountDto.getBalance())
                .currency(accountDto.getCurrency())
                .status(accountDto.getStatus())
                .build();
    }
}
