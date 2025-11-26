package org.jala.university.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.jala.university.commons.domain.entity.BaseEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "accounts")
public final class Account implements BaseEntity<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String accountNumber;

    @Column
    private AccountType type;

    @Column
    private String name;

    @Column
    private Double balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @CreatedDate
    private Date created;

    @LastModifiedDate
    private Date updated;

    @Override
    public UUID getId() {
        return id;
    }

    public Account() {
        super();
    }
}
