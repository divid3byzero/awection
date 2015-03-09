package com.buchner.auction.controller.user;

import com.buchner.auction.model.core.app.AuctionUser;
import com.buchner.auction.model.core.app.LiferayComponentService;
import com.buchner.auction.model.user.LoginView;
import com.buchner.auction.model.user.UserRegisterView;

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
