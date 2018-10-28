package com.thorton.grant.uspto.prototypewebapp.controllers;

import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.IUserService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;
import java.util.Locale;


@Controller
public class EmailVerificationController {

    ///////////////////////////////////////////////////////////////
    // email verification handler
    ///////////////////////////////////////////////////////////////
    @Autowired
    private IUserService service;

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration
            (WebRequest request, Model model, @RequestParam("token") String token) {

        Locale locale = request.getLocale();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%email activation link clicked!!!!!%%%%%%%%%%%%%%%%%%%%%");

        VerificationToken verificationToken = service.getVerificationToken(token);
        if (verificationToken == null) {
            String message = "auth.message.invalidToken";
            model.addAttribute("message", message);
            //return "redirect:/badUser.html";
            return "registrationConfirm/badUser";
        }

        UserCredentials userCredentials = verificationToken.getNewCredential();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiredTime().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = "token has expired";
            model.addAttribute("message", messageValue);
            //return " redirect:/badUser.html";
            return "registrationConfirm/badUser";
        }

        userCredentials.setActive(1);
        service.saveRegisteredUserCredential(userCredentials);
        //return "redirect:/activate.html";
        return "registrationConfirm/activate";
    }
}
