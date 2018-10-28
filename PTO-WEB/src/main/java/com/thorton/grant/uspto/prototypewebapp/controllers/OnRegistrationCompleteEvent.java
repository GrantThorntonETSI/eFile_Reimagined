package com.thorton.grant.uspto.prototypewebapp.controllers;

import com.thorton.grant.uspto.prototypewebapp.model.entities.UserCredentials;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private UserCredentials userCredentials;

    public OnRegistrationCompleteEvent(
            UserCredentials userCredentials, Locale locale, String appUrl) {
        super(userCredentials);

        this.userCredentials = userCredentials;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }
}
