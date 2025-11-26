package org.jala.university.presentation.controller;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import lombok.EqualsAndHashCode;
import org.jala.university.application.dto.UserDto;
import org.jala.university.commons.presentation.ViewSwitcher;
import org.jala.university.domain.entity.Role;
import org.jala.university.presentation.AccountView;

import java.net.URL;
import java.util.ResourceBundle;

@EqualsAndHashCode(callSuper = true)
public final class UpdateUserController extends AbstractUserController implements Initializable {

    private UserDto userDto;

    public UpdateUserController() {
        super();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        Platform.runLater(() -> {
            UpdateViewContext context = (UpdateViewContext) getContext();
            if (context == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(ERROR_MESSAGE);
                alert.show();
                return;
            }
            userDto = context.getUserToUpdate();
            username.setText(userDto.getUsername());
            email.setText(userDto.getEmail());
            roleSelector.setValue(userDto.getRole().name());
            accounts.setAll(userDto.getAccounts());
            accountsTable.setItems(accounts);
        });
    }

    public void onUpdate() {
        if (isFormInputsValid()) {
            userDto.setUsername(username.getText());
            userDto.setEmail(email.getText());
            userDto.setRole(Role.fromName(roleSelector.getValue()));
            userDto.setAccounts(accounts);
            if (!password.getText().isEmpty()) {
                userDto.setPassword(password.getText());
            }
            userService.updateUser(userDto);
            ViewSwitcher.switchTo(AccountView.USERS_LIST.getView());
        } else {
            showValidationMessage();
        }
    }
}
