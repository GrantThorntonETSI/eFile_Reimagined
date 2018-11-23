package com.thorton.grant.uspto.prototypewebapp.service.REST;


import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Owner;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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

    public ApplicationService(ServiceBeanFactory serviceBeanFactory, HostBean hostBean) {
        this.serviceBeanFactory = serviceBeanFactory;
        this.hostBean = hostBean;
    }

    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/update/{applicationField}/{param}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationFields(@PathVariable String applicationField , @PathVariable String param, @PathVariable String appInternalID){

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

    /*
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

        }*/

        if(verifyValidUserSession("xxx") == false){

            String responseMsg = applicationField+" has not been saved. invalid user session.";
            return buildResponseEnity("404", responseMsg );

        }

        // retrieve application using passed internal id
        //BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);
        String appFieldReadable = "";
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);

        // page 1 fields
        if(applicationField.equals("set-lawyer-options-value")){
            // ptoUser.setState(param); // sets state code
            if(param.equals("no")){
                baseTrademarkApplication.setAttorneySet(true);
                baseTrademarkApplication.setAttorneyFiling(false);
                baseTrademarkApplication.setLastViewModel("application/AttorneyStart");
                baseTradeMarkApplicationService.save(baseTrademarkApplication);

            }
            else {
                baseTrademarkApplication.setAttorneySet(true);
                baseTrademarkApplication.setAttorneyFiling(true);
                baseTrademarkApplication.setLastViewModel("application/AttorneyStart");
                baseTradeMarkApplicationService.save(baseTrademarkApplication);
            }


            appFieldReadable = "Attorney Option. ";
        }


        // page two fields
        if(applicationField.equals("set-Owner-entity-types")){
            // parse params
            int start = param.indexOf("+");
            String entity_type = param.substring(0,start);
            String sub_type = param.substring(start+1);

            if(entity_type.equals("US")){

                baseTrademarkApplication.setOwnerType(entity_type);
                baseTrademarkApplication.setOwnerSubType(sub_type);

                baseTrademarkApplication.setLastViewModel("application/OwnerStart");
                baseTradeMarkApplicationService.save(baseTrademarkApplication);

                appFieldReadable = "Entity Type. ";
            }
            // ptoUser.setState(param); // sets state code
        }

        String responseMsg = appFieldReadable+" has been saved.";

        //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
        return buildResponseEnity("200", responseMsg);
    }




    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/owner/update/{applicationField}/{param}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationOwner(@PathVariable String applicationField , @PathVariable String param, @PathVariable String appInternalID){

         // verify token before preceding

        if(verifyValidUserSession("xxx") == false){
            String responseMsg = applicationField+" has not been saved. invalid user session.";
            return buildResponseEnity("404", responseMsg);

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

        String responseMsg = appFieldReadable+" has been saved.";
        return buildResponseEnity("200", responseMsg);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Owner create + set for eFile Application
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // owner update - PTOUser --> Owner Contacts // this should get moved to ContactServices
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/contacts/add/{contact_email}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationAddContactsPool(@PathVariable String contact_email , @PathVariable String appInternalID){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // verify authentication is valid before moving on ....
        // have to have a valid session

        if(verifyValidUserSession(contact_email) == false){
            String responseMsg = "Contact with email address :"+contact_email+ " has not been saved. invalid user session.";
            return buildResponseEnity("404", responseMsg);

        }
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();

        PTOUser ptoUser = ptoUserService.findByEmail(email);//
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



        Lawyer lawyer = ptoUser.findLawyerContactByEmail(contact_email);
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

        String responseMsg = "Contact with email address :"+contact_email+" has been added to the Application.";

        return buildResponseEnity("200", responseMsg);

    }




    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/contacts/delete/{contact_email}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationRemoveContactsPool(@PathVariable String contact_email , @PathVariable String appInternalID){


        if(verifyValidUserSession(contact_email) == false){
            String responseMsg = "Contact with email address :"+contact_email+ " has not been saved. invalid user session.";
            return buildResponseEnity("404", responseMsg);

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

        Lawyer delLawyer;

        for(Iterator<Lawyer> iter = baseTrademarkApplication.getAvailableLawyers().iterator(); iter.hasNext(); ) {
            //delLawyer = null;
            delLawyer = iter.next();
            if(delLawyer.getEmail().equals(contact_email)){

                if(baseTrademarkApplication.getAvailableLawyers().size() == 1){
                    baseTrademarkApplication.setLastViewModel("application/AttorneyStart");
                    //baseTrademarkApplication.setAttorneyFiling(false);

                }
                if(delLawyer.isPrimary()){
                        baseTrademarkApplication.setPrimaryLawyer(null);
                        delLawyer.setPrimary(false);
                        baseTrademarkApplication.removeAvailableLawyer(delLawyer);

                }
                else {
                    baseTrademarkApplication.removeAvailableLawyer(delLawyer);
                }

            }
        }


        baseTradeMarkApplicationService.save(baseTrademarkApplication);

        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = "Contact with email address :"+contact_email+" has been removed from the Application";
        return buildResponseEnity("200", responseMsg);

    }






    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/Attorney/Primary/set/{contact_email}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> setApplicationPrimaryAttorney(@PathVariable String contact_email , @PathVariable String appInternalID){

        if(verifyValidUserSession(contact_email) == false){

            String responseMsg = "Contact with email address :"+contact_email+ "has not been set as Primary Attorney. invalid user session.";
            return buildResponseEnity("404", responseMsg);

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


       Lawyer primaryAttorney = baseTrademarkApplication.findContactByEmail(contact_email);

       if(primaryAttorney != null){
           // unset existing if there is one ...
           Lawyer currentPrimary = baseTrademarkApplication.getPrimaryLawyer();
           if(currentPrimary != null){
               currentPrimary.setPrimary(false);
           }
           baseTrademarkApplication.setPrimaryLawyer(primaryAttorney);
           primaryAttorney.setPrimary(true);
           baseTrademarkApplication.setLastViewModel("application/AttorneySet");
           baseTradeMarkApplicationService.save(baseTrademarkApplication);

       }
       else{
           // error

           String responseMsg = "Contact with email address :"+contact_email+ " has not been set as Primary Attorney. invalid contact email.";
           //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
           return buildResponseEnity("404", responseMsg);
       }

        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = "Contact with email address :"+contact_email+"  have been set as Primary Attorney";
        return buildResponseEnity("200", responseMsg);

    }

    ////////////////////////////////////////////////
    // helper functions
    ////////////////////////////////////////////////

    ////////////////////////////////////////////////
    // verify user session is valid
    ////////////////////////////////////////////////
    boolean verifyValidUserSession(String contact_email){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(email);// ?? we may not need to save this
        if(ptoUser == null || contact_email == ""){

            return false;

        }
        else {
            return true;
        }

    }
    ////////////////////////////////////////////////

    ////////////////////////////////////////////////
    // build response enity for REST API
    ////////////////////////////////////////////////
    ResponseEntity<String> buildResponseEnity(String status_code, String response_main){

        //String statusCode = "404";
        String statusCode = status_code;
        //String responseMsg = "Contact with email address :"+contact_email+ "has not been set as Primary Attorney. invalid user session.";
        String responseMsg = response_main;
        responseMsg = "{status:" + statusCode +" } { msg:"+responseMsg+" }";
        HttpHeaders responseHeader = new HttpHeaders ();
        responseHeader.setAccessControlAllowOrigin(hostBean.getHost()+hostBean.getPort());
        ArrayList<String> headersAllowed = new ArrayList<String>();
        headersAllowed.add("Access-Control-Allow-Origin");
        responseHeader.setAccessControlAllowHeaders(headersAllowed);
        ArrayList<String> methAllowed = new ArrayList<String>();

        return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;



    }
    ////////////////////////////////////////////////


    ////////////////////////////////////////////////
    // create copy of lawyer object
    ////////////////////////////////////////////////
    Lawyer createLawyerCopy(Lawyer lawyer){


        Lawyer lawyerCopy = null;
        return lawyerCopy;
    }

    ////////////////////////////////////////////////

    ////////////////////////////////////////////////
    // create copy of owner object
    ////////////////////////////////////////////////
    Owner creaateOwnerCopy( Owner owner){

        Owner ownerCopy = null;
        return  ownerCopy;
    }
    ////////////////////////////////////////////////





}
