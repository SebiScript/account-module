package org.jala.university.presentation.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import lombok.EqualsAndHashCode;
import org.jala.university.ServiceFactory;
import org.jala.university.application.dto.AccountDto;
import org.jala.university.application.service.AccountService;
import org.jala.university.commons.presentation.BaseController;
import org.jala.university.commons.presentation.ViewSwitcher;
import org.jala.university.domain.entity.AccountStatus;
import org.jala.university.domain.entity.Currency;
import org.jala.university.presentation.AccountView;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

@EqualsAndHashCode(callSuper = true)
public class CreateAccountController
        extends BaseController
        implements Initializable {
    @FXML
    private ChoiceBox<String> currencySelector;
    @FXML
    private TextField accountNumber;
    @FXML
    private TextField name;

    private final AccountService accountService;
    private final ObservableList<String> currencies =
            FXCollections.observableArrayList();

    public CreateAccountController() {
        this.accountService = ServiceFactory.accountService();
    }

    @Override
    public final void initialize(URL url, ResourceBundle resourceBundle) {
        loadCurrency();
    }

    private void loadCurrency() {
        currencies.clear();
        Arrays.stream(Currency.values()).forEach(
                currency -> currencies.add(currency.getName())
        );
        currencySelector.getItems().addAll(currencies);
        currencySelector.setValue(currencies.stream().findFirst().orElse(""));
    }

    public final void onCreate() {
        AccountDto newAccount = AccountDto.builder()
                .accountNumber(accountNumber.getText())
                .name(name.getText())
                .balance(0.0)
                .status(AccountStatus.ACTIVE)
                .currency(Currency.fromName(currencySelector.getValue()))
                .build();
        accountService.createAccount(newAccount);
        ViewSwitcher.switchTo(AccountView.MAIN.getView());
    }

    public final void onCancel() {
        ViewSwitcher.switchTo(AccountView.MAIN.getView());
    }
}
