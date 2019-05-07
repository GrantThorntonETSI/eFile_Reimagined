package com.thorton.grant.uspto.prototypewebapp.controllers;

import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.AccountRecoveryDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.RegistrationDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.VerificationToken;
import com.thorton.grant.uspto.prototypewebapp.service.recovery.accountRecoveryEvent;
import com.thorton.grant.uspto.prototypewebapp.service.registratrion.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.Locale;


@Controller
public class AccountRecoveryController {

    private final UserRegistrationService service;

    private final ServiceBeanFactory serviceBeanFactory;


    private String account_email;
    private String account_token;

    public String getAccount_email() {
        return account_email;
    }

    public void setAccount_email(String account_email) {
        this.account_email = account_email;
    }

    public String getAccount_token() {
        return account_token;
    }

    public void setAccount_token(String account_token) {
        this.account_token = account_token;
    }

    public AccountRecoveryController(UserRegistrationService service, ServiceBeanFactory serviceBeanFactory) {
        this.service = service;
        this.serviceBeanFactory = serviceBeanFactory;
    }


    @RequestMapping(value = "/recovery", method = RequestMethod.GET)
    public String showRecoveryForm(WebRequest request, Model model){

        System.out.print("registration show form pre-binding.......");
        // get values from newUser form
        // assign a use_role to new user
        // save new user data into User table

        // create owner object
        // copy over name info and user id/email
        // add owner object to model as attribute


         AccountRecoveryDTO accountRecoveryDTO = new AccountRecoveryDTO();
         model.addAttribute("userCredentialsDTO", accountRecoveryDTO);


        return "AccountRecovery";

    }




    @Autowired
    ApplicationEventPublisher eventPublisher2;
    @RequestMapping(value = "/recovery/recoverAccount", method = RequestMethod.POST)
    public ModelAndView recoverUserAccount(
            @ModelAttribute("userCredentialsDTO")  AccountRecoveryDTO accountDto,
            BindingResult result,
            WebRequest request,
            Errors errors) {

        System.out.print("post process for account recovery .......form result binded to DTO object");
        System.out.println("DTO capture output: ");
        System.out.println();
        System.out.println("####################################################################");

        System.out.println(accountDto.getEmail());
        System.out.println("####################################################################");



        ////////////////////////////////
        // main recovery  logic
        ///////////////////////////////

        // get email

        // find userCredentials via email

        UserCredentials registered;

        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        registered = userCredentialsService.findByEmail(accountDto.getEmail());

        if (registered == null) {
            result.rejectValue("email", "account is not valid");
        }
        if (result.hasErrors()) {
            System.out.println("result has errors !!!!!!!!!!!!!!!!!!!!"+result.getAllErrors().toString());

            return new ModelAndView("AccountRecovery", "user", accountDto);
        }
        else {
            //return new ModelAndView("/account/userHome", "users", accountDto);
            // new users needs to log in. will need to verify email before activation.
            // no errors ...send user to email verification notifcation page
            try {
                String appUrl = request.getContextPath();
                eventPublisher2.publishEvent(new accountRecoveryEvent
                        (registered, request.getLocale(), appUrl));
            } catch (Exception me) {
                return new ModelAndView("emailError", "user", accountDto);
            }

            //////////////////////////////////////////////////////////////////////////////////////
            // after registration event, return user to check email activation link page
            //////////////////////////////////////////////////////////////////////////////////////
            return new ModelAndView("AccountRecoveryConfirm", "user",accountDto);
        }



    }


    @RequestMapping(value = "/recovery/setPassword", method = RequestMethod.GET)
    public String confirmReset
            (WebRequest request, Model model, @RequestParam("token") String token) {

        Locale locale = request.getLocale();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%password reset link clicked!!!!!%%%%%%%%%%%%%%%%%%%%%");

        VerificationToken verificationToken = service.getVerificationToken(token);
        if (verificationToken == null) {
            String message = "auth.message.invalidToken";


            return "/error";
        }

        UserCredentials userCredentials = verificationToken.getNewCredential();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiredTime().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = "token has expired";
            AccountRecoveryDTO accountRecoveryDTO = new AccountRecoveryDTO();
            model.addAttribute("userCredentialsDTO", accountRecoveryDTO);
            model.addAttribute("message", messageValue);

            return "AccountRecovery";
        }

        //////////////////////////////////////////////////////////////////////////
        // activates account
        //////////////////////////////////////////////////////////////////////////
        //userCredentials.setActive(1);

        //service.saveRegisteredUserCredential(userCredentials);
        System.out.println ("account : " +userCredentials.getEmail() + "  is now active. user still need to set account password.");
        ///////////////////////////////////////////////////////////////////////////
        // persists user data locally to this servlet
        ///////////////////////////////////////////////////////////////////////////
        setAccount_email(userCredentials.getEmail());
        setAccount_token(token);

        // we will be forwarding this to self ...


        //////////////////////////////////////////////////////////////////////////
        // these two values will be reset every time this work flow is accessed


        String server_message = "Email verification succesful. You can now reset your password.";
        //redirectAttributes.addFlashAttribute("message",server_message );
        model.addAttribute("message", server_message);


        return "resetPassword";

    }
}
