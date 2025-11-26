package org.jala.university.infrastructure.persistance;

import org.jala.university.domain.entity.User;
import org.jala.university.domain.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class UserRepositoryMock implements UserRepository {

    private final Map<UUID, User> users = new HashMap<>();

    public UserRepositoryMock() {
        // Pre-populate the repository with some random users
        users.putAll(UserGenerator.generateUsers());
    }

    @Override
    public User findByUsername(String username) {
        return users.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findById(UUID id) {
        return users.get(id);
    }

    @Override
    public List<User> findAll() {
        return users.values().stream().toList();
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID());
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public void delete(User user) {
        users.remove(user.getId());
    }

    @Override
    public void deleteById(UUID id) {
        users.remove(id);
    }
}
