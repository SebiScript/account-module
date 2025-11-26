package org.jala.university.domain.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Role {
    CLIENT("Client"),
    ADMIN("Admin"),
    EXTERNAL_PROVIDER("ExternalProvider"),;

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public static Role fromName(String name) {
        return Arrays
                .stream(Role.values())
                .filter(role -> role.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
