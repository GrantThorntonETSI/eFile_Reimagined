package com.thorton.grant.uspto.prototypewebapp.service.recovery;

import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class accountRecoveryEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private UserCredentials userCredentials;


    public accountRecoveryEvent(UserCredentials userCredentials, Locale locale, String appUrl ) {
        super(userCredentials);
        this.appUrl = appUrl;
        this.locale = locale;
        this.userCredentials = userCredentials;

        System.out.println("$$$$$$$$$$$$$$$$$$$$$AccountRecoveryCompleteEvent$$$$$$$$$$$$$$$$$$$$$$$$");
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
