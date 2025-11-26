package org.jala.university.presentation.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.jala.university.ServiceFactory;
import org.jala.university.application.dto.AccountDto;
import org.jala.university.application.service.AccountService;
import org.jala.university.commons.presentation.BaseController;

import java.util.function.Consumer;

@EqualsAndHashCode(callSuper = true)
public final class AccountToUserController extends BaseController {

    @Setter
    private Consumer<AccountDto> onCloseCallback;

    @FXML
    private TextField accountNumber;

    @Setter
    private Stage stage;

    private final AccountService accountService;

    public AccountToUserController() {
        this.accountService = ServiceFactory.accountService();
    }


    public void onSave() {
        String accountNumberValue = accountNumber.getText();
        if (accountNumberValue.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Account number cannot be empty");
            alert.show();
            return;
        }
        AccountDto accountDto = accountService.getAccountByNumber(accountNumberValue);
        if (accountDto != null) {
            onCloseCallback.accept(accountDto);
            close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Account not found");
            alert.show();
        }
    }

    public void onCancel() {
        close();
    }

    private void close() {
        if (stage != null) {
            stage.close();
        }
    }
}
