package com.thorton.grant.uspto.prototypewebapp.controllers;

import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.PasswordSetDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.TwoFactorDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.VerificationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;


@Controller
public class PathController {

    private final ServiceBeanFactory serviceBeanFactory;

    public PathController(ServiceBeanFactory serviceBeanFactory) {
        this.serviceBeanFactory = serviceBeanFactory;
    }

    @RequestMapping({"", "/","/index","/index.html", "/home"})
    public String index(){
        // get owner info
        System.out.println("#############################################################");

        return "public/index2";

        //return "registrationConfirm/VerificationEmail";
    }


    // login intercept
    @RequestMapping({"/verifyAddress"})
    public String verifyAddress(Model model){

        // get access credentials
        // get email and get PTOUser object from repository
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());

        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);

        if(ptoUser.isProfileComplete()){
              return "forward:accounts/dashboard";
        }
        else {
            return "forward:accounts/userHome";
        }







    }


    // login intercept
    @RequestMapping({"/2FactorAuth"})
    public String twofactorAuth(Model model){

        // get access credentials
        // get email and get PTOUser object from repository
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());

        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());
        /////////////////////////////////////////////////////////////////////
        // generate token + send email with token
        // link to current user and store in token store
        // add token DTO to model
        /////////////////////////////////////////////////////////////////////



        TwoFactorDTO twoFactorDTO = new TwoFactorDTO();
        model.addAttribute("twoFactorDTO", twoFactorDTO);




        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);

        return "2factorAuth";

    }





    @RequestMapping(value = "/2factorSubmit", method = RequestMethod.POST)
    public String verifyTwoFactorToken(
            Model model,
            @ModelAttribute("twoFactorDTO") @Valid TwoFactorDTO twoFactorDTO,
            BindingResult result,
            WebRequest request,
            Errors errors){



        // get token from twoFactorDTO
        // if token exists ...(check token store)
        //
        //
        // forward user to  /verifyAddress






        return "forward:/verifyAddress";


    }




    @RequestMapping({"/aboutUs"})
    public String info(){

        return "aboutUs";
    }


    @RequestMapping({"/contact"})
    public String contact(){

        return "contacts/index";
    }



    @RequestMapping({"/owner"})
    public String testDashboard(){


        return "owner/index";
    }
}
