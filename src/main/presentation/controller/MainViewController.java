package org.jala.university.presentation.controller;

import org.jala.university.commons.presentation.BaseController;
import org.jala.university.commons.presentation.ViewSwitcher;
import org.jala.university.presentation.AccountView;

public final class MainViewController extends BaseController {

    public void onCreateAccount() {
        ViewSwitcher.switchTo(AccountView.CREATE.getView());
    }

    public void onViewAccounts() {
        ViewSwitcher.switchTo(AccountView.LIST.getView());
    }

    public void onCreateUser() {
        ViewSwitcher.switchTo(AccountView.CREATE_USER.getView());
    }

    public void onViewUsers() {
        ViewSwitcher.switchTo(AccountView.USERS_LIST.getView());
    }
}
