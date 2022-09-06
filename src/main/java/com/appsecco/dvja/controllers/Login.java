package com.appsecco.dvja.controllers;

import com.appsecco.dvja.models.User;
import com.appsecco.dvja.services.UserAuthenticationService;
import org.apache.commons.lang.StringUtils;

public class Login extends BaseController {

    private UserAuthenticationService userAuthenticationService;

    private String username;
    private String password;

    public UserAuthenticationService getUserAuthenticationService() {
        return userAuthenticationService;
    }

    public void setUserAuthenticationService(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String logout() {
        sessionRemoveUser();
        return SUCCESS;
    }

    public String execute() {
        User user;

        if(StringUtils.isEmpty(getUsername()) || StringUtils.isEmpty(getPassword()))
            return INPUT;

        if((user = userAuthenticationService.authenticate(getUsername(), getPassword())) != null) {
            sessionSetUser(user);
            return SUCCESS;
        }

        addActionError("Authentication failed!");
        return INPUT;
    }
}

