package org.jala.university.domain.entity;

import lombok.Getter;

@Getter
public enum AccountType {
    SAVING,
    CREDIT_CARD,
    LOAN,
    EXTERNAL_SERVICE;
}
