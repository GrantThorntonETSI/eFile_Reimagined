package com.thorton.grant.uspto.prototypewebapp.service.recovery;

import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.IUserService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import com.thorton.grant.uspto.prototypewebapp.service.mail.gmail.GmailJavaMailSenderService;
import com.thorton.grant.uspto.prototypewebapp.service.registratrion.OnRegistrationCompleteEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class recoveryEventListener implements
        ApplicationListener<accountRecoveryEvent> {

    private final IUserService service;

    /////////////////////////////////////////////////////////////////////////////////////////
    // based on the profile  ...we should be able
    // to inject the correct bean mapped to the correct host file here
    ////////////////////////////////////////////////////////////////////////////////////////
    // private final MessageSource messages;
    private  final HostBean hostBean;
    private final ApplicationContext appContext;


    private final GmailJavaMailSenderService mailSender;

    public recoveryEventListener(IUserService service, ApplicationContext appContext, GmailJavaMailSenderService mailSender) {
        this.service = service;
        this.appContext = appContext;
        this.mailSender = mailSender;
        this.hostBean = (HostBean) appContext.getBean(HostBean.class);
    }



    @Override
    public void onApplicationEvent(accountRecoveryEvent accountRecoveryEvent) {

        this.confirmRecoverEvent(accountRecoveryEvent);

    }



    private void confirmRecoverEvent(accountRecoveryEvent event) {


        UserCredentials userCredentials = event.getUserCredentials();

        String token = UUID.randomUUID().toString();
        service.createVerificationToken(userCredentials, token);

        String recipientAddress = userCredentials.getEmail();
        String subject = "Password Reset";
        String confirmationUrl
                = event.getAppUrl() + "/recovery/setPassword/?token=" + token;
        //  String message = messages.getMessage("message.regSucc", null, event.getLocale());

        System.out.println("email verification link: "+confirmationUrl);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);


        //email.setText(message + " rn" + "http://localhost:8080" + confirmationUrl);
        // make send email call none blocking
        new Thread(new Runnable() {
            public void run()
            {

                // perform any operation
                //mailSender.sendEmailverificationLink("http://efile-reimagined.com"+confirmationUrl,recipientAddress);
                mailSender.sendAccountRestLink(hostBean.getHost()+confirmationUrl,recipientAddress);
                System.out.println("ACCOUNT RECOVERY EMAIL SENT!");
            }
        }).start();
        //mailSender.send(email);
    }
}
