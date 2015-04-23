package com.buchner.auction.controller;

import com.buchner.auction.model.core.app.AuctionUser;
import com.buchner.auction.model.core.app.LiferayComponentService;
import com.buchner.auction.model.user.LoginView;
import com.buchner.auction.model.user.UserRegisterForm;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Controller that adds a user to Liferay database after user registers.
 */
@Named
@ApplicationScoped
public class UserController extends AbstractBaseController {

    @Inject
    private UserRegisterForm userRegisterForm;

    @Inject
    private LoginView loginView;

    @Inject
    private LiferayComponentService lrayComponentService;


    public UserRegisterForm getUserRegisterForm() {

        return userRegisterForm;
    }

    public void registerUser() {

        AuctionUser auctionUser = userRegisterForm.createAuctionUser();
        lrayComponentService.createLrayUser(auctionUser);
        clearView(userRegisterForm);
    }
}
