package org.jala.university.domain.entity;

import lombok.Getter;

@Getter
public enum Currency {
    USD("US Dollar", "$"),
    MXP("Peso Mexicano", "MX$"),
    COL("Peso Colombiano", "COL$"),
    BOB("Boliviano", "Bs"),
    EUR("Euro", "€");

    private final String name;

    private final String symbol;

    Currency(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public static Currency fromName(String name) {
        for (Currency b : Currency.values()) {
            if (b.name.equalsIgnoreCase(name)) {
                return b;
            }
        }
        return null;
    }

}
