package com.buchner.awection.controller.user;

import com.buchner.awection.model.core.AuctionUser;
import com.buchner.awection.model.core.LiferayComponentService;
import com.buchner.awection.model.user.LoginView;
import com.buchner.awection.model.user.UserRegisterView;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class UserController {

    @Inject
    private UserRegisterView userRegisterView;

    @Inject
    private LoginView loginView;

    @Inject
    private LiferayComponentService lrayComponentService;


    public UserRegisterView getUserRegisterView() {

        return userRegisterView;
    }

    public LoginView getLoginView() {

        return loginView;
    }

    public void registerUser() {

        AuctionUser auctionUser = userRegisterView.createAuctionUser();
        lrayComponentService.createLrayUser(auctionUser);
    }
}
