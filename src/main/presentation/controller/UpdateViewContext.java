package org.jala.university.presentation.controller;

import lombok.Builder;
import lombok.Data;
import org.jala.university.application.dto.AccountDto;
import org.jala.university.application.dto.UserDto;
import org.jala.university.commons.presentation.ViewContext;

/**
 * User: Joaquin Arrazola.
 * Date: 27/4/24 10:31 AM
 */
@Data
@Builder
public class UpdateViewContext implements ViewContext {
    private AccountDto accountToUpdate;
    private UserDto userToUpdate;
}
