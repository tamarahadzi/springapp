package com.example.springapp.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {

    /**
     * Constructor
     */
    public AjaxLogoutSuccessHandler() {}

    /**
     * Event, on logout success
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @param authentication {@link Authentication}
     */
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) {
        System.out.println("******************** onLogoutSuccess ******************");
        System.out.println("**** Invalidate session");
        System.out.println("**** Set response to code 200, success");
        System.out.println("*******************************************************");
        request.getSession().invalidate();
        response.setStatus(200);
    }
}

