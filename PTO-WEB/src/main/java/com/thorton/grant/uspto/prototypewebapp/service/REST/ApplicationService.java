package com.thorton.grant.uspto.prototypewebapp.service.REST;


import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Owner;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.Console;
import java.util.ArrayList;
import java.util.Iterator;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Service
public class ApplicationService {

    private final ServiceBeanFactory serviceBeanFactory;

    /////////////////////////////////////////////////////////////////////////////////////////
    // based on the profile  ...we should be able
    // to inject the correct bean mapped to the correct host file here
    ////////////////////////////////////////////////////////////////////////////////////////
    private final HostBean hostBean;
    private final ApplicationContext appContext;


    public ApplicationService(ServiceBeanFactory serviceBeanFactory,ApplicationContext appContext ) {
        this.serviceBeanFactory = serviceBeanFactory;
        this.appContext = appContext;
        this.hostBean = (HostBean) appContext.getBean(HostBean.class);
    }

    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/update/{applicationField}/{param}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationFields(@PathVariable String applicationField , @PathVariable String param, @PathVariable String appInternalID){


    System.out.println("############################################################");
    System.out.println(applicationField);
    System.out.println(param);
    System.out.println(appInternalID);
    System.out.println("############################################################");
            // verify token before preceding
/*
        VerificationToken verificationToken = service.getVerificationToken(token);
        if (verificationToken == null) {
            String message = "INVALID ACCESS TOKEN.";
            HttpHeaders responseHeader = new HttpHeaders ();
            ArrayList<String> headersAllowed = new ArrayList<String>();
            responseHeader.setAccessControlAllowHeaders(headersAllowed);
            return ResponseEntity.badRequest().headers(responseHeader).body(message) ;
        }


        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiredTime().getTime() - cal.getTime().getTime()) <= 0) {
            String message = "EXPIRED ACCESS TOKEN.";
            HttpHeaders responseHeader = new HttpHeaders ();
            ArrayList<String> headersAllowed = new ArrayList<String>();
            responseHeader.setAccessControlAllowHeaders(headersAllowed);
            return ResponseEntity.badRequest().headers(responseHeader).body(message) ;
        }


        UserCredentials userCredentials = verificationToken.getNewCredential();
*/


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        //UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        //UserCredentials userCredentials = userCredentialsService.findByEmail(email);
        PTOUser ptoUser = ptoUserService.findByEmail(email);// ?? we may not need to save this
        // verify authentication is valid before moving on ....
        // have to have a valid session

        if(ptoUser == null){
            ////////////////////////////////////////////////
            // start generating response
            ////////////////////////////////////////////////
            String statusCode = "404";
            String responseMsg = applicationField+" has not been saved. invalid user session.";
            responseMsg = "{status:" + statusCode +" } { msg:"+responseMsg+" }";
            HttpHeaders responseHeader = new HttpHeaders ();
            //responseHeader.setAccessControlAllowOrigin("http://efile-reimagined.com");
            responseHeader.setAccessControlAllowOrigin(hostBean.getHost()+hostBean.getPort());
            ArrayList<String> headersAllowed = new ArrayList<String>();
            headersAllowed.add("Access-Control-Allow-Origin");
            responseHeader.setAccessControlAllowHeaders(headersAllowed);
            ArrayList<String> methAllowed = new ArrayList<String>();

            System.out.println("response header : "+responseHeader.getAccessControlAllowOrigin());


            return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;

        }
        // retrieve application using passed internal id
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);
        String appFieldReadable = "";

        // page 1 fields
        if(applicationField.equals("set-lawyer-options-value")){
            // ptoUser.setState(param); // sets state code
            if(param.equals("no")){

                baseTrademarkApplication.setAttorneySet(true);
                baseTrademarkApplication.setAttorneyFiling(false);
                baseTrademarkApplication.setLastViewModel("application/AttorneyStart");

            }
            else {
                baseTrademarkApplication.setAttorneySet(true);
                baseTrademarkApplication.setAttorneyFiling(true);
                baseTrademarkApplication.setLastViewModel("application/AttorneyStart");

            }

            baseTradeMarkApplicationService.save(baseTrademarkApplication);
            appFieldReadable = "Attorney Option. ";
        }


        // page two fields
        if(applicationField.equals("set-Owner-entity-types")){
            // parse params
            int start = param.indexOf("+");
            String entity_type = param.substring(0,start);
            String sub_type = param.substring(start+1);

            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            System.out.println(entity_type);
            System.out.println(sub_type);


            if(entity_type.equals("US")){
                Owner owner = baseTrademarkApplication.getOwner();
                owner.setOwnerType(entity_type);
                owner.setOwnersubType(sub_type);
                baseTrademarkApplication.setOwner(owner);

                baseTrademarkApplication.setLastViewModel("application/owner/individual/ownerInfo");
                baseTradeMarkApplicationService.save(baseTrademarkApplication);

                appFieldReadable = "Entity Type. ";
            }
            // ptoUser.setState(param); // sets state code
        }



        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////
        String statusCode = "200";
        String responseMsg = appFieldReadable+" has been saved.";
        responseMsg = "{status:" + statusCode +" } { msg:"+responseMsg+" }";
        HttpHeaders responseHeader = new HttpHeaders ();
        //responseHeader.setAccessControlAllowOrigin("http://efile-reimagined.com");
        responseHeader.setAccessControlAllowOrigin(hostBean.getHost()+hostBean.getPort());
        ArrayList<String> headersAllowed = new ArrayList<String>();
        headersAllowed.add("Access-Control-Allow-Origin");
        responseHeader.setAccessControlAllowHeaders(headersAllowed);
        ArrayList<String> methAllowed = new ArrayList<String>();

        System.out.println("response header : "+responseHeader.getAccessControlAllowOrigin());


        return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;

    }




    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/owner/update/{applicationField}/{param}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationOwner(@PathVariable String applicationField , @PathVariable String param, @PathVariable String appInternalID){


         System.out.println("############################################################");
         System.out.println(applicationField);
         System.out.println(param);
         System.out.println(appInternalID);
         System.out.println("############################################################");
         // verify token before preceding


         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String email = authentication.getName();
         //UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
         PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
         //UserCredentials userCredentials = userCredentialsService.findByEmail(email);
         PTOUser ptoUser = ptoUserService.findByEmail(email);// ?? we may not need to save this
         // verify authentication is valid before moving on ....
         // have to have a valid session

        if(ptoUser == null || param == ""){
            ////////////////////////////////////////////////
            // start generating response
            ////////////////////////////////////////////////
            String statusCode = "404";
            String responseMsg = applicationField+" has not been saved. invalid user session.";
            responseMsg = "{status:" + statusCode +" } { msg:"+responseMsg+" }";
            HttpHeaders responseHeader = new HttpHeaders ();
            //responseHeader.setAccessControlAllowOrigin("http://efile-reimagined.com");
            responseHeader.setAccessControlAllowOrigin(hostBean.getHost()+hostBean.getPort());
            ArrayList<String> headersAllowed = new ArrayList<String>();
            headersAllowed.add("Access-Control-Allow-Origin");
            responseHeader.setAccessControlAllowHeaders(headersAllowed);
            ArrayList<String> methAllowed = new ArrayList<String>();

            return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;

        }
        // retrieve application using passed internal id
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);
        String appFieldReadable = "";

        // page 3 fields
        if(applicationField.equals("first-name")){
            baseTrademarkApplication.getOwner().setFirstName(param);

            baseTrademarkApplication.setLastViewModel("application/owner/individual/ownerInfo");
            baseTradeMarkApplicationService.save(baseTrademarkApplication);
            appFieldReadable = "Owner First Name. ";
        }

        if(applicationField.equals("last-name")){
            baseTrademarkApplication.getOwner().setLastName(param);

            baseTrademarkApplication.setLastViewModel("application/owner/individual/ownerInfo");
            baseTradeMarkApplicationService.save(baseTrademarkApplication);
            appFieldReadable = "Owner Last Name. ";
        }

        if(applicationField.equals("owner-citizenship")){
            baseTrademarkApplication.getOwner().setCountry(param);

            baseTrademarkApplication.setLastViewModel("application/owner/individual/ownerInfo");
            baseTradeMarkApplicationService.save(baseTrademarkApplication);
            appFieldReadable = "Owner Citizenship. ";
        }

        if(applicationField.equals("owner-state")){
            // ptoUser.setState(param); // sets state code
        }

        if(applicationField.equals("owner-city")){
            //ptoUser.setCity(param);
        }

        if(applicationField.equals("owner-zipcode")){
            //ptoUser.setZipcode(param);
        }

        if(applicationField.equals("owner-address")){
            //ptoUser.setAddress(param);
        }

        if(applicationField.equals("owner-country")){
            //ptoUser.setCountry(param); // sets country code
        }

        if(applicationField.equals("own-Phone")){
            //ptoUser.setPrimaryPhonenumber(param);
        }



        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////
        String statusCode = "200";
        String responseMsg = appFieldReadable+" has been saved.";
        responseMsg = "{status:" + statusCode +" } { msg:"+responseMsg+" }";
        HttpHeaders responseHeader = new HttpHeaders ();
        //responseHeader.setAccessControlAllowOrigin("http://efile-reimagined.com");

        responseHeader.setAccessControlAllowOrigin(hostBean.getHost()+hostBean.getPort());
        ArrayList<String> headersAllowed = new ArrayList<String>();
        headersAllowed.add("Access-Control-Allow-Origin");
        responseHeader.setAccessControlAllowHeaders(headersAllowed);
        ArrayList<String> methAllowed = new ArrayList<String>();

        System.out.println("response header : "+responseHeader.getAccessControlAllowOrigin());


        return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;

    }

    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/contacts/add/{contact_email}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationAddContactsPool(@PathVariable String contact_email , @PathVariable String appInternalID){



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        //UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        //UserCredentials userCredentials = userCredentialsService.findByEmail(email);
        PTOUser ptoUser = ptoUserService.findByEmail(email);// ?? we may not need to save this
        // verify authentication is valid before moving on ....
        // have to have a valid session

        if(ptoUser == null || contact_email == ""){
            ////////////////////////////////////////////////
            // start generating response
            ////////////////////////////////////////////////
            String statusCode = "404";
            String responseMsg = "Contact with email address :"+contact_email+ " has not been saved. invalid user session.";
            responseMsg = "{status:" + statusCode +" } { msg:"+responseMsg+" }";
            HttpHeaders responseHeader = new HttpHeaders ();
            //responseHeader.setAccessControlAllowOrigin("http://efile-reimagined.com");
            responseHeader.setAccessControlAllowOrigin(hostBean.getHost()+hostBean.getPort());
            ArrayList<String> headersAllowed = new ArrayList<String>();
            headersAllowed.add("Access-Control-Allow-Origin");
            responseHeader.setAccessControlAllowHeaders(headersAllowed);
            ArrayList<String> methAllowed = new ArrayList<String>();

            return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;

        }
        //////////////////////////////////////////////////////////
        // retrieve application using passed internal id
        //////////////////////////////////////////////////////////
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);
        //////////////////////////////////////////////////////////
        // find contact via email from PTOUser
        // create a copy of the contact object
        // add the copy of the contact to the application object
        // save application object
        ///////////////////////////////////////////////////////////



        Lawyer lawyer = ptoUser.findContactByEmail(contact_email);
        /////////////////////////////////////////////////////////////////
        // check if contact is already in the pool ...
        // if already in pool, simply do nothing
        /////////////////////////////////////////////////////////////////
        if(lawyer.getPoolMember() != null){
            if(lawyer.getPoolMember().getApplicationInternalID().equals(baseTrademarkApplication.getApplicationInternalID())){
                /////////////////////////////////////////////////////////////////////////
                // check if lawyer already in pool
                // if so, nothing to do here
                // though we should not every reach this block
                // as persistance should prevent user from adding a contact
                // that's already been added. persistance  rendering should have
                // greyed out that contact and also list it in the selected contact list
                /////////////////////////////////////////////////////////////////////////
            }
            else {

                Lawyer application_lawyer = new Lawyer();
                /////////////////////////////////////////////////////////////////
                // copy over contact's lawyer's personal info
                /////////////////////////////////////////////////////////////////
                application_lawyer.setFirstName(lawyer.getFirstName());
                application_lawyer.setLastName(lawyer.getLastName());
                application_lawyer.setCountry(lawyer.getCountry());
                application_lawyer.setAddress(lawyer.getAddress());
                application_lawyer.setCity(lawyer.getCity());
                application_lawyer.setZipcode(lawyer.getZipcode());
                application_lawyer.setPrimaryPhonenumber(lawyer.getPrimaryPhonenumber());
                application_lawyer.setEmail(lawyer.getEmail());
                //////////////////////////////////////////////////////////////////
                // copy over contact's professional info
                //////////////////////////////////////////////////////////////////
                application_lawyer.setBarJurisdiction(lawyer.getBarJurisdiction());
                application_lawyer.setBarLicense(lawyer.getBarLicense());
                application_lawyer.setValidBarAssociation(lawyer.isValidBarAssociation());
                application_lawyer.setLawFirmName(lawyer.getLawFirmName());
                ///////////////////////////////////////////////////////////////////
                // copy over contact's owner info  --->sets clientID to ptoUser
                ///////////////////////////////////////////////////////////////////
                application_lawyer.setClient(ptoUser);
                ///////////////////////////////////////////////////////////////////
                // set lawyer's pool ID ---> sets application's internal id as pool id
                ///////////////////////////////////////////////////////////////////
                application_lawyer.setPoolMember(baseTrademarkApplication);
                baseTrademarkApplication.addAvailableLawyer(application_lawyer);


                baseTradeMarkApplicationService.save(baseTrademarkApplication);
                // save application object  and check database and confirm values

            }
           // contact is already added to the application
            /////////////////////////////////////////////////////////////////
            // this should not really happen. as our persistance scheme should
            // show application pool lawyers in the selected contacts widget, and the
            // corresponding contact should have it check mark greyed out
            // the only action available to users should be to remove
            // a contact from the application
            /////////////////////////////////////////////////////////////////
        }
        else {
            // application lawyer pool is currently empty

            Lawyer application_lawyer = new Lawyer();
            /////////////////////////////////////////////////////////////////
            // copy over contact's lawyer's personal info
            /////////////////////////////////////////////////////////////////
            application_lawyer.setFirstName(lawyer.getFirstName());
            application_lawyer.setLastName(lawyer.getLastName());
            application_lawyer.setCountry(lawyer.getCountry());
            application_lawyer.setAddress(lawyer.getAddress());
            application_lawyer.setCity(lawyer.getCity());
            application_lawyer.setZipcode(lawyer.getZipcode());
            application_lawyer.setPrimaryPhonenumber(lawyer.getPrimaryPhonenumber());
            application_lawyer.setEmail(lawyer.getEmail());
            application_lawyer.setState(lawyer.getState());
            application_lawyer.setDocketNumber(lawyer.getDocketNumber());
            //////////////////////////////////////////////////////////////////
            // copy over contact's professional info
            //////////////////////////////////////////////////////////////////
            application_lawyer.setBarJurisdiction(lawyer.getBarJurisdiction());
            application_lawyer.setBarLicense(lawyer.getBarLicense());
            application_lawyer.setValidBarAssociation(lawyer.isValidBarAssociation());
            application_lawyer.setLawFirmName(lawyer.getLawFirmName());
            ///////////////////////////////////////////////////////////////////
            // copy over contact's owner info  --->sets clientID to ptoUser
            ///////////////////////////////////////////////////////////////////
            application_lawyer.setClient(ptoUser);
            ///////////////////////////////////////////////////////////////////
            // set lawyer's pool ID ---> sets application's internal id as pool id
            ///////////////////////////////////////////////////////////////////
            application_lawyer.setPoolMember(baseTrademarkApplication);
            baseTrademarkApplication.addAvailableLawyer(application_lawyer);


            baseTradeMarkApplicationService.save(baseTrademarkApplication);
            // save application object  and check database and confirm values
        }







        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////
        String statusCode = "200";
        String responseMsg = "Contact with email address :"+contact_email+" has been added to the Application.";

        responseMsg = "{status:" + statusCode +" } { msg:"+responseMsg+" }";
        HttpHeaders responseHeader = new HttpHeaders ();
        //responseHeader.setAccessControlAllowOrigin("http://efile-reimagined.com");

        responseHeader.setAccessControlAllowOrigin(hostBean.getHost()+hostBean.getPort());
        ArrayList<String> headersAllowed = new ArrayList<String>();
        headersAllowed.add("Access-Control-Allow-Origin");
        responseHeader.setAccessControlAllowHeaders(headersAllowed);
        ArrayList<String> methAllowed = new ArrayList<String>();

        System.out.println("response header : "+responseHeader.getAccessControlAllowOrigin());


        return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;

    }




    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/contacts/delete/{contact_email}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationRemoveContactsPool(@PathVariable String contact_email , @PathVariable String appInternalID){



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        //UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        //UserCredentials userCredentials = userCredentialsService.findByEmail(email);
        PTOUser ptoUser = ptoUserService.findByEmail(email);// ?? we may not need to save this
        // verify authentication is valid before moving on ....
        // have to have a valid session

        if(ptoUser == null || contact_email == ""){
            ////////////////////////////////////////////////
            // start generating response
            ////////////////////////////////////////////////
            String statusCode = "404";
            String responseMsg = "Contact with email address :"+contact_email+ " has not been saved. invalid user session.";
            responseMsg = "{status:" + statusCode +" } { msg:"+responseMsg+" }";
            HttpHeaders responseHeader = new HttpHeaders ();
            //responseHeader.setAccessControlAllowOrigin("http://efile-reimagined.com");
            responseHeader.setAccessControlAllowOrigin(hostBean.getHost()+hostBean.getPort());
            ArrayList<String> headersAllowed = new ArrayList<String>();
            headersAllowed.add("Access-Control-Allow-Origin");
            responseHeader.setAccessControlAllowHeaders(headersAllowed);
            ArrayList<String> methAllowed = new ArrayList<String>();

            return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;

        }
        //////////////////////////////////////////////////////////
        // retrieve application using passed internal id
        //////////////////////////////////////////////////////////
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);
        //////////////////////////////////////////////////////////
        // find contact via email from PTOUser
        // create a copy of the contact object
        // add the copy of the contact to the application object
        // save application object
        ///////////////////////////////////////////////////////////

        // check if lawyer is set as primary ...if so, remove that first
         Lawyer delLawyer = baseTrademarkApplication.getPrimaryLawyer();
         if(delLawyer != null){
             if(delLawyer.getEmail().equals(contact_email)){
                 baseTrademarkApplication.setPrimaryLawyer(null);
             }

         } // else primary is not set and nothing to do there
        // check if contact email is in application lawyer pool ..if so, remove
        for(Iterator<Lawyer> iter = baseTrademarkApplication.getAvailableLawyers().iterator(); iter.hasNext(); ) {
            delLawyer = null;
            delLawyer = iter.next();
            if(delLawyer.getEmail().equals(contact_email)){

                baseTrademarkApplication.removeAvailableLawyer(delLawyer);

            }
        }












        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////
        String statusCode = "200";
        String responseMsg = "Contact with email address :"+contact_email+" has been removed from the Application";
        responseMsg = "{status:" + statusCode +" } { msg:"+responseMsg+" }";
        HttpHeaders responseHeader = new HttpHeaders ();
        //responseHeader.setAccessControlAllowOrigin("http://efile-reimagined.com");

        responseHeader.setAccessControlAllowOrigin(hostBean.getHost()+hostBean.getPort());
        ArrayList<String> headersAllowed = new ArrayList<String>();
        headersAllowed.add("Access-Control-Allow-Origin");
        responseHeader.setAccessControlAllowHeaders(headersAllowed);
        ArrayList<String> methAllowed = new ArrayList<String>();

        System.out.println("response header : "+responseHeader.getAccessControlAllowOrigin());


        return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;

    }



}
