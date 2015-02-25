package com.buchner.auction.frontend.user.controller;

import com.buchner.auction.frontend.user.model.internal.AuctionUser;
import com.buchner.auction.frontend.user.model.LoginView;
import com.buchner.auction.frontend.user.model.UserRegisterView;
import com.buchner.auction.frontend.user.model.internal.LiferayComponentService;

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
        lrayComponentService.createUser(auctionUser);
    }
}
