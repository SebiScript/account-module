package org.jala.university.application.mapper;

import lombok.AllArgsConstructor;
import org.jala.university.application.dto.AccountDto;
import org.jala.university.application.dto.UserDto;
import org.jala.university.commons.application.mapper.Mapper;
import org.jala.university.domain.entity.Account;
import org.jala.university.domain.entity.User;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UserMapper implements Mapper<User, UserDto> {

    public static UserMapper getInstance() {
        return new UserMapper();
    }

    @Override
    public final UserDto mapTo(User user) {
        AccountMapper accountMapper = AccountMapper.getInstance();
        List<AccountDto> accountDtos = new ArrayList<>();
        if (user.getAccounts() != null) {
            accountDtos = user.getAccounts()
                    .stream()
                    .map(accountMapper::mapTo)
                    .toList();
        }
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .phone(user.getPhone())
                .accounts(accountDtos)
                .build();
    }

    @Override
    public final User mapFrom(UserDto userDto) {
        AccountMapper accountMapper = AccountMapper.getInstance();
        List<Account> accounts = new ArrayList<>();
        if (userDto.getAccounts() != null) {
            accounts = userDto.getAccounts()
                    .stream()
                    .map(accountMapper::mapFrom)
                    .toList();
        }
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .accounts(accounts)
                .build();
    }
}
