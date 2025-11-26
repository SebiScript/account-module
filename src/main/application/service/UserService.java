package org.jala.university.application.service;

import org.jala.university.application.dto.AccountDto;
import org.jala.university.application.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto createUser(UserDto userDto);

    void updateUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto getUserById(UUID id);

    void setAccounts(UserDto userDto, List<AccountDto> accountsDto);

    void removeAccount(UserDto userDto, AccountDto accountDto);
}
