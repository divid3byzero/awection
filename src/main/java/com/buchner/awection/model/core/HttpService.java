package com.buchner.awection.model.core;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.ResourceRequest;

@RequestScoped
public class HttpService {

    public HttpService() {

    }

    public PortletRequest getPortletRequest() {

        return
            (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public PortletResponse getPortletResponse() {

        return
            (PortletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    public String getLoginRedirect() {

        ResourceRequest resourceRequest =
            (ResourceRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        return resourceRequest.getScheme() + "://" + resourceRequest.getServerName() + ":" + resourceRequest
            .getServerPort() + "/web/guest/dashboard";
    }
}
