package com.thorton.grant.uspto.prototypewebapp.controllers;

import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


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
              return "account/dashboard";
        }
        else {
            return "account/userHome";
        }







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
