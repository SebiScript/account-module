package org.jala.university.presentation.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jala.university.ServiceFactory;
import org.jala.university.application.dto.AccountDto;
import org.jala.university.application.service.UserService;
import org.jala.university.commons.presentation.BaseController;
import org.jala.university.commons.presentation.ViewSwitcher;
import org.jala.university.domain.entity.Role;
import org.jala.university.presentation.AccountView;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public abstract class AbstractUserController extends BaseController implements Initializable {

    protected static final String ERROR_MESSAGE =
            "Error occurred showing the update screen";

    @FXML
    protected TextField username;
    @FXML
    protected PasswordField password;
    @FXML
    protected ChoiceBox<String> roleSelector;
    @FXML
    protected TextField email;
    @FXML
    protected TableColumn<AccountDto, Void> indexColumn;
    @FXML
    protected TableColumn<AccountDto, String> accountNumber;
    @FXML
    protected TableView<AccountDto> accountsTable;

    protected final ObservableList<String> roles =
            FXCollections.observableArrayList();
    protected final ObservableList<AccountDto> accounts =
            FXCollections.observableArrayList();

    protected final UserService userService;

    public AbstractUserController() {
        this.userService = ServiceFactory.getUserService();
    }

    /**
     * This method is used to initialize the controller.
     * It is called by the FXMLLoader when loading the FXML file.
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        indexColumn.setSortable(false);
        indexColumn.setCellValueFactory(cellData -> null);
        indexColumn.setCellFactory(col -> {
            return new TableCell<AccountDto, Void>() {
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(String.valueOf(getIndex() + 1));
                    }
                }
            };
        });
        loadRoles();
    }

    private void loadRoles() {
        roles.clear();
        Arrays.stream(Role.values()).forEach(
                role -> roles.add(role.name())
        );
        roleSelector.getItems().addAll(roles);
        roleSelector.setValue(roles.stream().findFirst().orElse(""));
    }

    /**
     * This method is used to initialize the controller.
     * It is called by the FXMLLoader when loading the FXML file.
     * @return boolean
     */
    protected boolean isFormInputsValid() {
        return !username.getText().isEmpty();
    }

    public final void onCancel() {
        ViewSwitcher.switchTo(AccountView.MAIN.getView());
    }

    public final void onAddAccount() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/account-to-user.fxml"));
        Stage addAccountStage = new Stage();
        Parent root = fxmlLoader.load();
        addAccountStage.setScene(new Scene(root));
        addAccountStage.setTitle("Add Account");

        AccountToUserController accountToUserController = fxmlLoader.getController();
        accountToUserController.setStage(addAccountStage);

        Consumer<AccountDto> onDataReceived = data -> {
            if (data != null) {
                accounts.add(data);
                accountsTable.setItems(accounts);
            }
        };

        accountToUserController.setOnCloseCallback(onDataReceived);
        addAccountStage.initModality(Modality.APPLICATION_MODAL);
        addAccountStage.showAndWait();
    }

    protected final void showValidationMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText("All fields must be filled out!");
        alert.showAndWait();
    }

}
