package com.thorton.grant.uspto.prototypewebapp.controllers;

import com.thorton.grant.uspto.prototypewebapp.interfaces.IUserService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements
        ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private IUserService service;

    //@Autowired
    //private MessageSource messages;

    //@Autowired
    //private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        UserCredentials userCredentials = event.getUserCredentials();
        String token = UUID.randomUUID().toString();
        //service.createVerificationToken(user, token);

        String recipientAddress = userCredentials.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
                = event.getAppUrl() + "/regitrationConfirm.html?token=" + token;
      //  String message = messages.getMessage("message.regSucc", null, event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        //email.setText(message + " rn" + "http://localhost:8080" + confirmationUrl);
        //mailSender.send(email);
    }
}