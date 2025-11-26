package org.jala.university.application.dto;

import lombok.Builder;
import lombok.Data;
import org.jala.university.domain.entity.Role;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class UserDto {

    UUID id;
    String username;
    String password;
    Role role;
    String email;
    String phone;
    List<AccountDto> accounts;
}
