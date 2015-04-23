package com.buchner.auction.controller;

import com.buchner.auction.model.user.LoginView;
import com.buchner.auction.model.core.app.AwectionCredentials;
import com.buchner.auction.model.core.app.HttpService;
import com.buchner.auction.model.core.app.LiferayComponentService;
import org.primefaces.context.RequestContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

/**
 * Handles user login attempts
 */
@Named
@ApplicationScoped
public class LoginController extends AbstractBaseController {

    @Inject
    private LiferayComponentService liferayComponentService;

    @Inject
    private LoginView loginView;

    @Inject
    private HttpService httpService;

    public LoginController() {

    }

    public void login() {

        AwectionCredentials awectionCredentials = loginView.getAwectionCredentials();
        boolean loggedIn = false;
        String redirectUrl = null;
        try {

            liferayComponentService.invokeLogin(awectionCredentials);
            loggedIn = true;
            redirectUrl = httpService.getLoginRedirect();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            // Pass variables to Facelets view to redirect user to dashboard page.
            // Variable "loggedIn" is used to communicate an invalid login attempt. See JavaScript part in /auction/src/main/webapp/views/login.xhtml
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.addCallbackParam("loggedIn", loggedIn);
            requestContext.addCallbackParam("redirectUrl", Optional.ofNullable(redirectUrl).orElse(""));
        }

    }
}
