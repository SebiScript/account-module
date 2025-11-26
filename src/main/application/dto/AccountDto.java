package org.jala.university.application.dto;

import lombok.Builder;
import lombok.Data;
import org.jala.university.domain.entity.AccountStatus;
import org.jala.university.domain.entity.AccountType;
import org.jala.university.domain.entity.Currency;

import java.util.UUID;

@Builder
@Data
public class AccountDto {

    UUID id;
    String accountNumber;
    String name;
    Double balance;
    AccountStatus status;
    AccountType type;
    Currency currency;
}
