package com.buchner.auction.model.core.app;

import com.liferay.faces.util.portal.WebKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ClassResolverUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@ApplicationScoped
public class LiferayComponentService {

    public static final String FQCN_LOGIN_UTIL = "com.liferay.portlet.login.util.LoginUtil";
    public static final String LOGIN_METHOD = "login";

    @Inject
    private HttpService httpService;

    public LiferayComponentService() {

    }

    @Produces
    @RequestScoped
    public User produceCurrentUser() {

        ThemeDisplay currentThemeDisplay = getCurrentThemeDisplay();
        return currentThemeDisplay.getUser();
    }

    public User createLrayUser(AuctionUser auctionUser) {

        long companyId = getCurrentThemeDisplay().getCompanyId();
        User lrayUser = null;
        try {

            Role userRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.USER);
            long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);

            lrayUser = UserLocalServiceUtil.addUser(
                defaultUserId,
                companyId, false, auctionUser.getPassword(), auctionUser.getPassword(),
                false, auctionUser.getFirstName() + auctionUser.getSurname(),
                auctionUser.getMailAddress(), 0, null,
                new Locale("de_DE"), auctionUser.getFirstName(), null, auctionUser.getSurname(), 0,
                0, false,
                1, 1, 1970, null, null, null, new long[] {userRole.getRoleId()},
                null,
                false, new ServiceContext()
            );

        } catch (PortalException | SystemException e) {
            System.out.print(e);
        }

        return lrayUser;
    }

    public void invokeLogin(AwectionCredentials awectionCredentials) throws Exception {


        Class<?> loginUtilClass = ClassResolverUtil
            .resolveByPortalClassLoader(FQCN_LOGIN_UTIL);

        HttpServletRequest httpRequest = PortalUtil
            .getHttpServletRequest(httpService.getPortletRequest());
        HttpServletResponse httpResponse =
            PortalUtil.getHttpServletResponse(httpService.getPortletResponse());

        MethodKey key =
            new MethodKey(loginUtilClass, LOGIN_METHOD,
                HttpServletRequest.class, HttpServletResponse.class, String.class, String.class,
                boolean.class, String.class);

        PortalClassInvoker.invoke(false, key,
            httpRequest,
            httpResponse,
            awectionCredentials.getUserName(), awectionCredentials.getPassword(), true,
            CompanyConstants.AUTH_TYPE_EA);
    }

    private ThemeDisplay getCurrentThemeDisplay() {

        return (ThemeDisplay) httpService.getPortletRequest().getAttribute(WebKeys.THEME_DISPLAY);
    }
}
