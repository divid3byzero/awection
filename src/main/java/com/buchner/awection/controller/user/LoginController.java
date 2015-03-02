package com.buchner.awection.controller.user;

import com.buchner.awection.model.user.LoginView;
import com.buchner.awection.model.core.AwectionCredentials;
import com.buchner.awection.model.core.HttpService;
import com.buchner.awection.model.core.LiferayComponentService;
import org.primefaces.context.RequestContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
@ApplicationScoped
public class LoginController {

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

            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.addCallbackParam("loggedIn", loggedIn);
            requestContext.addCallbackParam("redirectUrl", Optional.ofNullable(redirectUrl).orElse(""));
        }

    }
}
