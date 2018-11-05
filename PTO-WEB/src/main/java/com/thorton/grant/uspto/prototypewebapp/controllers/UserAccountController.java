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

import java.util.HashSet;

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
        BaseTrademarkApplication baseTrademarkApplication = ptoUser.getMyApplications().iterator().next();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@User Account Controller@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("user : "+authentication.getName());

        Lawyer lawyer = baseTrademarkApplication.getPrimaryLawyer();

        System.out.println("primary lawer law firm: "+lawyer.getLawFirmName());
        // add it to model as attribute
        // or do we prebuild the data tables array here and add it as an object ???
        ////////////////////////////////////////////////////////////////////////////
        //do we just build the array object that datatables needs ???
        /// do  static datatables push on user dash board
        ////////////////////////////////////////////////////////////////////////////

        // build string array
        String[]  applicationDataTableRow = new String[5];
        applicationDataTableRow[0] = "";
        applicationDataTableRow[1] = "<a href='/application/continue/" +baseTrademarkApplication.getApplicationInternalID()+ "'>Goto Application</a>";
        applicationDataTableRow[2] = baseTrademarkApplication.getOwner().getFirstName() + " "+baseTrademarkApplication.getOwner().getLastName();
        applicationDataTableRow[3] = baseTrademarkApplication.getCurrentStage();
        applicationDataTableRow[4] = "";



        System.out.println("1111111111111111111111"+applicationDataTableRow);
        model.addAttribute("newFilingTableRow", applicationDataTableRow );

        model.addAttribute("trademarkApplication", baseTrademarkApplication);

        if(ptoUser.isProfileComplete() == false){
            model.addAttribute("message", "Please Complete your Contact Information First.");
            return "account/userHome";
        }
        return "account/dashboard";
        //return baseTrademarkApplication.getLastViewModel();

    }




}
