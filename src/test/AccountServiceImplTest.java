// Package Structure: Organize your test classes in a parallel package structure to your main classes.
// For example, if your main class is in org.jala.university.application.service, your test class should be
// in the same package
package org.jala.university.application.service;

import org.jala.university.application.dto.AccountDto;
import org.jala.university.application.mapper.AccountMapper;
import org.jala.university.domain.entity.Account;
import org.jala.university.domain.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

// Name your test classes to reflect the class under test by appending "Test" to the class name.
class AccountServiceImplTest {

    AccountRepository accountRepository;
    AccountServiceImpl accountService;

    @BeforeEach
     void setup(){
        accountRepository = mock(AccountRepository.class);
        AccountMapper accountMapper = new AccountMapper();
        accountService = new AccountServiceImpl(accountRepository, accountMapper);
    }

    // NameConvention:
    //    Method Naming: Name your test methods descriptively, using the should or when style. For example:
    //    calculateTotal_shouldReturnCorrectSum()
    //    withdrawMoney_whenSufficientBalance_shouldUpdateBalance()
    @Test
    void createAccount_shouldCreateAccountSuccessfully() {
        //Arrange, Act, Assert (AAA): Structure your tests using the AAA pattern for clarity:

        //Arrange: Set up the preconditions for the test.
        UUID savedId = UUID.randomUUID();
        // Use Mockito to simulate a expected response from repository so our code is isolated to Repository logic
        when(accountRepository.save(any())).thenReturn(Account.builder().id(savedId).name("TestAccount").build());

        //Act: Perform the action or invoke the method being tested.
        AccountDto actualAccount = accountService.createAccount(AccountDto.builder().name("TestAccount").build());

        //Assert: Verify that the expected behavior occurred.
        //Use Assertions: Utilize the assertion methods provided by JUnit 5, such as assertEquals, assertTrue, assertNotNull,
        assertNotNull(actualAccount);
        assertEquals(savedId, actualAccount.getId());
        assertEquals("TestAccount", actualAccount.getName());
        assertNull(actualAccount.getCurrency()); // Since repo is not returning currency is expected null
    }

    // Annotations:
    // Annotate your test methods with @Test from org.junit.jupiter.api.Test.
    // Optionally, use other annotations like @DisplayName to provide more descriptive names for your tests.
    @Test
    void updateAccount() {
        UUID savedId = UUID.randomUUID();
        Account account = Account.builder().id(savedId).name("TestAccount").build();
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        accountService.updateAccount(AccountDto.builder().id(savedId).name("TestAccount").build());

        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void getAllAccounts() {
        UUID savedId = UUID.randomUUID();
        Account account = Account.builder().id(savedId).name("TestAccount").build();
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        when(accountRepository.findAll()).thenReturn(accounts);

        List<AccountDto> expectedResult = accountService.getAllAccounts();

        assertEquals(1, expectedResult.size());
        assertEquals(savedId, expectedResult.get(0).getId());
        assertEquals("TestAccount", expectedResult.get(0).getName());
    }

    @Test
    void removeAccount() {
        doNothing().when(accountRepository).delete(any(Account.class));

        accountService.removeAccount(AccountDto.builder().name("TestAccount").build());

        verify(accountRepository, times(1)).delete(any(Account.class));
    }
}