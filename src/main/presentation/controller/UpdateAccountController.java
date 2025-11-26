package org.jala.university.presentation.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import lombok.EqualsAndHashCode;
import org.jala.university.ServiceFactory;
import org.jala.university.application.dto.AccountDto;
import org.jala.university.application.service.AccountService;
import org.jala.university.commons.presentation.BaseController;
import org.jala.university.commons.presentation.ViewSwitcher;
import org.jala.university.presentation.AccountView;

import java.net.URL;
import java.util.ResourceBundle;

@EqualsAndHashCode(callSuper = true)
public final class UpdateAccountController
        extends BaseController
        implements Initializable {
    private static final String ERROR_MESSAGE =
            "Error occurred showing the update screen";

    @FXML
    private ChoiceBox<String> currencySelector;
    @FXML
    private TextField accountNumber;
    @FXML
    private TextField name;
    @FXML
    private TextField balance;

    private final AccountService accountService;
    private AccountDto accountDto;

    public UpdateAccountController() {
        this.accountService = ServiceFactory.accountService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            UpdateViewContext context = (UpdateViewContext) getContext();
            if (context == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(ERROR_MESSAGE);
                alert.show();
                return;
            }
            accountDto  =  context.getAccountToUpdate();
            accountNumber.setText(accountDto.getAccountNumber());
            name.setText(accountDto.getName());
            balance.setText(accountDto.getBalance().toString());
            currencySelector.setValue(accountDto.getCurrency().getName());
        });
    }

    public void onUpdate() {
        accountDto.setAccountNumber(accountNumber.getText());
        accountDto.setName(name.getText());
        accountService.updateAccount(accountDto);
        ViewSwitcher.switchTo(AccountView.LIST.getView());
    }

    public void onCancel() {
        ViewSwitcher.switchTo(AccountView.LIST.getView());
    }
}
