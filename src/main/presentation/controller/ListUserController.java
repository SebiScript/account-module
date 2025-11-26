package org.jala.university.presentation.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.EqualsAndHashCode;
import org.jala.university.ServiceFactory;
import org.jala.university.application.dto.UserDto;
import org.jala.university.application.service.UserService;
import org.jala.university.commons.presentation.BaseController;
import org.jala.university.commons.presentation.ViewSwitcher;
import org.jala.university.presentation.AccountView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@EqualsAndHashCode(callSuper = true)
public final class ListUserController
        extends BaseController
        implements Initializable {
    @FXML
    private TableColumn<UserDto, String> username;
    @FXML
    private TableColumn<UserDto, String> role;
    @FXML
    private TableColumn<UserDto, String> email;
    @FXML
    private TableView<UserDto> usersTable;

    private final UserService userService;
    private final ObservableList<UserDto> users =
            FXCollections.observableArrayList();

    public ListUserController() {
        this.userService = ServiceFactory.getUserService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setCellValueFactory(
                new PropertyValueFactory<>("username"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadUsers();
    }

    @FXML
    void onReturn() {
        ViewSwitcher.switchTo(AccountView.MAIN.getView());
    }

    private void loadUsers() {
        users.clear();
        List<UserDto> usersList = userService.getAllUsers();
        users.addAll(usersList);
        usersTable.setItems(users);
    }

    @FXML
    public void onUpdate() {
        UserDto userSelected = usersTable
                .getSelectionModel()
                .getSelectedItem();
        if (userSelected == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update User");
            alert.setHeaderText("Select an user to update");
            alert.show();
            return;
        }
        UpdateViewContext updateViewContext = UpdateViewContext
                .builder()
                .userToUpdate(userSelected)
                .build();
        ViewSwitcher.switchTo(AccountView.UPDATE_USER.getView(), updateViewContext);
    }
}
