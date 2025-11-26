package org.jala.university.infrastructure.persistance;

import org.jala.university.domain.entity.Role;
import org.jala.university.domain.entity.User;

import java.util.*;

/**.
 * User: David Vida
 * Date: 21/4/25 4:30 PM
 */
public final class UserGenerator {

    private static final String STR_PASSWORD = "password";
    private static final String STR_USERNAME1 = "client1";
    private static final String STR_USERNAME2 = "client2";
    private static final String STR_USERNAME3 = "admin1";
    private static final String STR_EMAIL_DOMAIN = "@example.com";

    private UserGenerator() {
    }

    public static Map<UUID, User> generateUsers() {
        Map<UUID, User> usersMap = new HashMap<>();
        List<User> usersList = getUsers();
        for (User user : usersList) {
            usersMap.put(user.getId(), user);
        }
        return usersMap;
    }

    private static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        User user1 = User.builder()
                .id(UUID.randomUUID())
                .username(STR_USERNAME1)
                .password(STR_PASSWORD)
                .email(STR_USERNAME1 + STR_EMAIL_DOMAIN)
                .role(Role.CLIENT)
                .build();
        User user2 = User.builder()
                .id(UUID.randomUUID())
                .username(STR_USERNAME2)
                .password(STR_PASSWORD)
                .email(STR_USERNAME2 + STR_EMAIL_DOMAIN)
                .role(Role.CLIENT)
                .build();
        users.add(user1);
        users.add(user2);

        User admin1 = User.builder()
                .id(UUID.randomUUID())
                .username(STR_USERNAME3)
                .password(STR_PASSWORD)
                .role(Role.ADMIN)
                .build();
        users.add(admin1);
        return users;
    }

}
