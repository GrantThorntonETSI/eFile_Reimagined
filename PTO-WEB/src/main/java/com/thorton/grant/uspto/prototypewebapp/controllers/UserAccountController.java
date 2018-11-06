package com.thorton.grant.uspto.prototypewebapp.controllers;


import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

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



    @RequestMapping({"/accounts/dashboard","accounts/dashboard"})
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
        //BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        //BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByEmail(authentication.getName());
        //HashSet<BaseTrademarkApplication> baseTrademarkApplications = new HashSet<BaseTrademarkApplication>(ptoUser.getMyApplications());

        BaseTrademarkApplication baseTrademarkApplication = null;
        ArrayList<String> userfilingTableRowsID = new ArrayList<>();

        for(Iterator<BaseTrademarkApplication> iter = ptoUser.getMyApplications().iterator(); iter.hasNext(); ) {


            baseTrademarkApplication = iter.next();
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@User Account Controller@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("user : "+authentication.getName());

            Lawyer lawyer = baseTrademarkApplication.getPrimaryLawyer();

            System.out.println("primary lawer law firm: "+lawyer.getLawFirmName());
            userfilingTableRowsID.add( "<a href='/application/continue/" +baseTrademarkApplication.getApplicationInternalID()+ "'>Goto Application</a>");

        }

        model.addAttribute("newFilingTableRow", userfilingTableRowsID);

        model.addAttribute("trademarkApplication", baseTrademarkApplication);

        if(ptoUser.isProfileComplete() == false){
            model.addAttribute("message", "Please Complete your Contact Information First.");
            return "account/userHome";
        }

        return "account/dashboard";
        //return baseTrademarkApplication.getLastViewModel();

    }





}
