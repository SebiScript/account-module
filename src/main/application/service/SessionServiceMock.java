package org.jala.university.application.service;

import lombok.Getter;
import lombok.Setter;
import org.jala.university.domain.repository.UserRepository;
import org.jala.university.infrastructure.persistance.UserRepositoryMock;

import java.util.UUID;

public final class SessionServiceMock implements SessionService {

    private static SessionService instance;

    public static class InstanceHolder {
        private static final SessionService INSTANCE = new SessionServiceMock(new UserRepositoryMock());
    }

    public static SessionService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    UserRepository userRepository;

    @Getter
    @Setter
    UUID currentUserId;

    private SessionServiceMock(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void clearSession() {
        this.currentUserId = null;
    }

}
