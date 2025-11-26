package org.jala.university;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.jala.university.application.mapper.AccountMapper;
import org.jala.university.application.mapper.UserMapper;
import org.jala.university.application.service.AccountService;
import org.jala.university.application.service.AccountServiceImpl;
import org.jala.university.application.service.UserService;
import org.jala.university.application.service.UserServiceImpl;
import org.jala.university.domain.repository.AccountRepository;
import org.jala.university.domain.repository.UserRepository;
import org.jala.university.infrastructure.persistance.AccountRepositoryImpl;
import org.jala.university.infrastructure.persistance.UserRepositoryImpl;

public final class ServiceFactory {

    private static final String PERSISTENCE_UNIT_NAME = "default";

    private static AccountService service;
    private static UserService userService;

    private ServiceFactory() {
    }

    public static AccountService accountService() {
        if (service != null) {
            return service;
        }
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        AccountRepository accountRepository = new AccountRepositoryImpl(entityManager);
        service = new AccountServiceImpl(accountRepository, AccountMapper.getInstance());
        return service;
    }

    public static UserService getUserService() {
        if (userService != null) {
            return userService;
        }
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserRepository userRepository = new UserRepositoryImpl(entityManager);
        userService = new UserServiceImpl(
                userRepository,
                UserMapper.getInstance(),
                accountService(),
                AccountMapper.getInstance());
        return userService;
    }
}
