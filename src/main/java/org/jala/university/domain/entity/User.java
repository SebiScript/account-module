package org.jala.university.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.jala.university.commons.domain.entity.BaseEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "users")
public final class User implements BaseEntity<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String phone;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Account> accounts = new ArrayList<>();

    @CreatedDate
    private Date created;

    @LastModifiedDate
    private Date updated;

    @Override
    public UUID getId() {
        return id;
    }

    @PrePersist
    @PreUpdate
    private void hashPassword() {
        if (password != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            this.password = encoder.encode(password);
        }
    }

    public User() {
        super();
    }

}
