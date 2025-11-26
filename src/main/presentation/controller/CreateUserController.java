package org.jala.university.presentation.controller;

import javafx.fxml.Initializable;
import lombok.EqualsAndHashCode;
import org.jala.university.application.dto.UserDto;
import org.jala.university.commons.presentation.ViewSwitcher;
import org.jala.university.domain.entity.Role;
import org.jala.university.presentation.AccountView;

@EqualsAndHashCode(callSuper = true)
public final class CreateUserController extends AbstractUserController implements Initializable {

    public CreateUserController() {
        super();
    }

    public void onCreate() {
        if (isFormInputsValid()) {
            UserDto newUserDto = UserDto.builder()
                    .username(username.getText())
                    .email(email.getText())
                    .role(Role.fromName(roleSelector.getValue()))
                    .password(password.getText())
                    .accounts(accounts)
                    .build();
            userService.createUser(newUserDto);
            ViewSwitcher.switchTo(AccountView.MAIN.getView());
        } else {
            showValidationMessage();
        }
    }

    @Override
    protected boolean isFormInputsValid() {
        return super.isFormInputsValid() && !password.getText().isEmpty()
                && !roleSelector.getValue().isEmpty();
    }
}
