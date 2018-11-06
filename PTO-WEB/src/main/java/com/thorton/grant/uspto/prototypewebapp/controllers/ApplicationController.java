package com.thorton.grant.uspto.prototypewebapp.controllers;


import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
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

@Controller
public class ApplicationController {

    private final  ServiceBeanFactory serviceBeanFactory;

    public ApplicationController(ServiceBeanFactory serviceBeanFactory) {
        this.serviceBeanFactory = serviceBeanFactory;
    }

    @RequestMapping({"/application/start"})
    public String applicationStart(Model model){


        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("logged in user email :"+authentication.getPrincipal());
        System.out.println("logged in user name: "+authentication.getName());
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());

        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);


        /////////////////////////////////////////////////////////////////////////////////////////////
        // create new check
        /////////////////////////////////////////////////////////////////////////////////////////////
        // check if user has LoadContinueUrl set ...
        // in the web url that triggers, you get that from an iteration of
        // myTradeMarks on the dashboard
        /////////////////////////////////////////////////////////////////////////////////////////////

        // this is only set during LoadContinueUrl ..and load the fling  object with continue url
        ////////////////////////////////////////////////////////////////////////////////////////////
        // when do we unset this ..we unset it as soon as we process the boolean in this method
        // make sure this call is none blocking
        ////////////////////////////////////////////////////////////////////////////////////////////


        //////////////////////////////////////////////////////////////////////////////////////////////
        // if not set, we create a new Filing object and add it to the model and pass it to the view
        //////////////////////////////////////////////////////////////////////////////////////////////


        // check if user have address information set.
        // also add possible attorney DTO for hidden part of the form


        // we are actually just gonna hit next and got to add owner from econtacts as new proseee


        return "application/AttorneyStart";

    }


    // hopefully just a redirect here, we won't need to add the applicaiton and credentials to the model
    @RequestMapping({"/application/OwnerStart"})
    public String ownerStart(Model model){

        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("logged in user email :"+authentication.getPrincipal());
        System.out.println("logged in user name: "+authentication.getName());
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());

        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);



        return "application/OwnerStart";
    }


    // hopefully just a redirect here, we won't need to add the applicaiton and credentials to the model
    @RequestMapping({"/application/owner/Ind/info"})
    public String ownerInfo(Model model){

        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());

        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);



        return "application/owner/individual/ownerInfo";
    }


    @RequestMapping(value = "/application/continueApplication", method = RequestMethod.GET)
    public String continueApplication
            (WebRequest request, Model model, @RequestParam("trademarkID") String trademarkInternalID) {

        String applcationLookupID = trademarkInternalID;
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!continue Application!!!!!!!!!!!!!!!!!");
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(trademarkInternalID);
        //HashSet<BaseTrademarkApplication> baseTrademarkApplications = new HashSet<BaseTrademarkApplication>(ptoUser.getMyApplications());

        //////////////////////////////////////////////////////////////////////////
        // retrieve trade mark using internal id
        // add trademark to the model
        // and return the the view that trademark object has saved.
        //////////////////////////////////////////////////////////////////////////

        model.addAttribute("trademarkApplication", baseTrademarkApplication);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        PTOUserService  ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        ptoUser.setContinuationURL(trademarkInternalID);

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);




        return baseTrademarkApplication.getLastViewModel();

    }




}
