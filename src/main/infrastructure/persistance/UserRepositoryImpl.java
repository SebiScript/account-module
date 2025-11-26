package org.jala.university.infrastructure.persistance;

import jakarta.persistence.EntityManager;
import org.jala.university.commons.infrastructure.persistance.CrudRepository;
import org.jala.university.domain.entity.User;
import org.jala.university.domain.repository.UserRepository;

import java.util.UUID;

public final class UserRepositoryImpl extends CrudRepository<User, UUID> implements UserRepository {
    public UserRepositoryImpl(EntityManager entityManager) {
        super(User.class, entityManager);
    }

    @Override
    public User findByUsername(String username) {
        return getEntityManager()
                .createQuery("SELECT a FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
