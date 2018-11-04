package com.thorton.grant.uspto.prototypewebapp.controllers;


import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserAccountController {


    private final ServiceBeanFactory serviceBeanFactory;

    public UserAccountController(ServiceBeanFactory serviceBeanFactory) {
        this.serviceBeanFactory = serviceBeanFactory;
    }



    // profile/ user account page controller
    @RequestMapping({"/accounts/userHome"})
    public String usreProfile(Model model){

        // retireve owner using email from the credentials ..
        // add find by email methods to both personal data and credentails cass

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("logged in user email :"+authentication.getPrincipal());
        System.out.println("logged in user name: "+authentication.getName());
        PTOUserService  ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());

        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);


        // check if user have address information set.


        return "account/userHome";
    }


    @RequestMapping({"/accounts/Home"})
    public String landingPageLoggedIn(Model model){

        // retireve owner using email from the credentials ..
        // add find by email methods to both personal data and credentails cass

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        PTOUserService  ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());

        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);


        // check if user have address information set.


        return "account/home";
    }



    @RequestMapping({"/accounts/dashboard"})
    public String dashboard(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("logged in user email :"+authentication.getPrincipal());
        System.out.println("logged in user name: "+authentication.getName());
        PTOUserService  ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());

        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);



        // find user's trademark applications

        // add it to model as attribute
        // or do we prebuild the data tables array here and add it as an object ???





        if(ptoUser.isProfileComplete() == false){
            model.addAttribute("message", "Please Complete your Contact Information First.");
            return "account/userHome";
        }
        return "account/dashboard";

    }




}
