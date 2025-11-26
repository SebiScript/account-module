package org.jala.university.application.service;

import lombok.AllArgsConstructor;
import org.jala.university.application.dto.AccountDto;
import org.jala.university.application.dto.UserDto;
import org.jala.university.application.mapper.AccountMapper;
import org.jala.university.application.mapper.UserMapper;
import org.jala.university.domain.entity.Account;
import org.jala.university.domain.entity.Role;
import org.jala.university.domain.entity.User;
import org.jala.university.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public final class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.mapFrom(userDto);
        if (user.getRole() == null) {
            user.setRole(Role.CLIENT);
        }
        if (userDto.getAccounts() != null) {
            user.setAccounts(userDto.getAccounts().stream().map(accountMapper::mapFrom).toList());
        }
        user = userRepository.save(user);
        return userMapper.mapTo(user);
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = userMapper.mapFrom(userDto);
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return this.userRepository
                .findAll()
                .stream().map(userMapper::mapTo)
                .toList();
    }

    @Override
    public UserDto getUserById(UUID id) {
        User findUser = userRepository.findById(id);
        return userMapper.mapTo(findUser);

    }

    @Override
    public void setAccounts(UserDto userDto, List<AccountDto> accountsDto) {
        User user = userMapper.mapFrom(userDto);
        List<Account> userAccounts = user.getAccounts();
        if (userAccounts == null) {
            userAccounts = new ArrayList<>();
        }
        if (!accountsDto.isEmpty()) {
            List<Account> newAccounts = accountsDto.stream().map(accountMapper::mapFrom).toList();
            userAccounts.addAll(newAccounts);
            user.setAccounts(userAccounts);
            userRepository.save(user);
        }
    }

    @Override
    public void removeAccount(UserDto userDto, AccountDto accountDto) {
        User user = userMapper.mapFrom(userDto);
        List<Account> userAccounts = user.getAccounts();
        AccountDto accDto = accountService.getAccountByNumber(accountDto.getAccountNumber());
        if (accDto != null) {
            userAccounts.remove(accountMapper.mapFrom(accountDto));
            user.setAccounts(userAccounts);
        }
        userRepository.save(user);
    }

}
