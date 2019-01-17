package com.thorton.grant.uspto.prototypewebapp.service.REST;


import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.participants.LawyerService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.participants.OwnerService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Owner;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.GoodAndService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.TradeMark;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Service
public class ApplicationService  extends  BaseRESTapiService{


    public ApplicationService(ServiceBeanFactory serviceBeanFactory, HostBean hostBean) {
        super(serviceBeanFactory, hostBean);
    }

    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/update/{applicationField}/{param}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationFields(@PathVariable String applicationField , @PathVariable String param, @PathVariable String appInternalID){


        if(verifyValidUserSession("xxx") == false){

            String responseMsg = applicationField+" has not been saved. invalid user session.";
            return buildResponseEnity("404", responseMsg );

        }

        // retrieve application using passed internal id
        //BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);
        String appFieldReadable = "";
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);

        // page 1 fields
        if(applicationField.equals("set-lawyer-options-value")){
            // ptoUser.setState(param); // sets state code
            if(param.equals("no")){
                baseTrademarkApplication.setAttorneySet(true);
                baseTrademarkApplication.setAttorneyFiling(false);
                baseTrademarkApplication.setLastViewModel("application/attorney/AttorneyStart");
                // drop all attorneys added to attorney pool if any exists
                baseTrademarkApplication.setPrimaryLawyer(null);
                for(Iterator<Lawyer> iter = baseTrademarkApplication.getAvailableLawyers().iterator(); iter.hasNext(); ) {
                    Lawyer current = iter.next();
                    baseTrademarkApplication.removeAvailableLawyer(current);
                    // remove lawyer from the database completely
                    LawyerService lawyerService = getServiceBeanFactory().getLawyerService();
                    lawyerService.delete(current);

                }

                baseTradeMarkApplicationService.save(baseTrademarkApplication);

            }
            else {
                baseTrademarkApplication.setAttorneySet(true);
                baseTrademarkApplication.setAttorneyFiling(true);
                baseTrademarkApplication.setLastViewModel("application/attorney/AttorneyStart");
                baseTradeMarkApplicationService.save(baseTrademarkApplication);
            }


            appFieldReadable = "Attorney Information";
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
                baseTrademarkApplication.setForeignEnityFiling(false);
                baseTrademarkApplication.setEntityTypeSet(true);

                baseTrademarkApplication.setLastViewModel("application/owner/OwnerStart");
                baseTradeMarkApplicationService.save(baseTrademarkApplication);

                appFieldReadable = "Entity Sub Type";
            }
            else {
                baseTrademarkApplication.setOwnerType("Foreign");
                baseTrademarkApplication.setOwnerSubType("Foreign Corporation");
                baseTrademarkApplication.setForeignEnityFiling(true);
                baseTrademarkApplication.setEntityTypeSet(true);

                baseTrademarkApplication.setLastViewModel("application/owner/OwnerStart");
                baseTradeMarkApplicationService.save(baseTrademarkApplication);

                appFieldReadable = "Entity Sub Type";

            }
            // ptoUser.setState(param); // sets state code
        }

        // page two fields
        if(applicationField.equals("set-Owner-entity")){
            // parse params

            if(param.equals("US")){

                if(baseTrademarkApplication.isForeignEnityFiling() == true){

                    OwnerService ownerService = getServiceBeanFactory().getOwnerService();
                    for(Iterator<Owner> iter = baseTrademarkApplication.getOwners().iterator(); iter.hasNext(); ) {
                        Owner current = iter.next();
                        baseTrademarkApplication.removeOwner(current);
                        ownerService.delete(current);

                    }
                }
                baseTrademarkApplication.setForeignEnityFiling(false);
                baseTrademarkApplication.setEntityTypeSet(true);



                baseTrademarkApplication.setLastViewModel("application/owner/OwnerStart");
                baseTradeMarkApplicationService.save(baseTrademarkApplication);

                appFieldReadable = "Entity Type";
            }
            else {
                if(baseTrademarkApplication.isForeignEnityFiling() == false){

                    OwnerService ownerService = getServiceBeanFactory().getOwnerService();
                    for(Iterator<Owner> iter = baseTrademarkApplication.getOwners().iterator(); iter.hasNext(); ) {
                        Owner current = iter.next();
                        baseTrademarkApplication.removeOwner(current);
                        ownerService.delete(current);

                    }
                }
                baseTrademarkApplication.setForeignEnityFiling(true);
                baseTrademarkApplication.setEntityTypeSet(true);

                baseTrademarkApplication.setLastViewModel("application/owner/OwnerStart");
                baseTradeMarkApplicationService.save(baseTrademarkApplication);

                appFieldReadable = "Entity Type";

            }
            // ptoUser.setState(param); // sets state code
        }

        String responseMsg = appFieldReadable+" has been saved";

        //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
        return buildResponseEnity("200", responseMsg);
    }




    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Owner create + set for eFile Application
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // owner update - PTOUser --> Owner Contacts // this should get moved to ContactServices
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/contacts/attorney/add/{contact_email}/{appInternalID}")
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
        PTOUserService ptoUserService = getServiceBeanFactory().getPTOUserService();

        PTOUser ptoUser = ptoUserService.findByEmail(email);//
        //////////////////////////////////////////////////////////
        // retrieve application using passed internal id
        //////////////////////////////////////////////////////////
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
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

                Lawyer application_lawyer = createLawyerCopy(lawyer, ptoUser, baseTrademarkApplication);
                /////////////////////////////////////////////////////////////////
                // copy over contact's lawyer's personal info
                /////////////////////////////////////////////////////////////////
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

            Lawyer application_lawyer = createLawyerCopy(lawyer, ptoUser, baseTrademarkApplication);
            /////////////////////////////////////////////////////////////////
            // copy over contact's lawyer's personal info
            /////////////////////////////////////////////////////////////////
            baseTrademarkApplication.addAvailableLawyer(application_lawyer);


            baseTradeMarkApplicationService.save(baseTrademarkApplication);
            // save application object  and check database and confirm values
        }

        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = "Contact with email address :"+contact_email+" has been added to the Application";

        return buildResponseEnity("200", responseMsg);

    }




    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/contacts/attorney/delete/{contact_email}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationRemoveContactsPool(@PathVariable String contact_email , @PathVariable String appInternalID){


        if(verifyValidUserSession(contact_email) == false){
            String responseMsg = "Contact with email address :"+contact_email+ " has not been saved";
            return buildResponseEnity("404", responseMsg);

        }
        //////////////////////////////////////////////////////////
        // retrieve application using passed internal id
        //////////////////////////////////////////////////////////
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);
        LawyerService lawyerService = getServiceBeanFactory().getLawyerService();
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
                    baseTrademarkApplication.setLastViewModel("application/attorney/AttorneyStart");
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
                lawyerService.delete(delLawyer);

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

            String responseMsg = "Contact with email address :"+contact_email+ "has not been set as Primary Attorney. invalid user session";
            return buildResponseEnity("404", responseMsg);

        }

        //////////////////////////////////////////////////////////
        // retrieve application using passed internal id
        //////////////////////////////////////////////////////////
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
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
           baseTrademarkApplication.setLastViewModel("application/attorney/AttorneySet");
           baseTrademarkApplication.setAttorneyCollapseID(primaryAttorney.getFirstName()+primaryAttorney.getLastName());
           baseTradeMarkApplicationService.save(baseTrademarkApplication);


       }
       else{
           // error

           String responseMsg = "Contact with email address :"+contact_email+ " has not been set as Primary Attorney. invalid contact email";
           //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
           return buildResponseEnity("404", responseMsg);
       }

        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = "Contact with email address :"+contact_email+"  have been set as Primary Attorney";
        return buildResponseEnity("200", responseMsg);

    }
    ////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////
    // helper functions
    ////////////////////////////////////////////////



    ////////////////////////////////////////////////
    // create copy of lawyer object
    ////////////////////////////////////////////////
    Lawyer createLawyerCopy(Lawyer lawyer, PTOUser ptoUser, BaseTrademarkApplication baseTrademarkApplication){


        Lawyer application_lawyer = new Lawyer();
        /////////////////////////////////////////////////////////////////
        // copy over contact's lawyer's personal info
        /////////////////////////////////////////////////////////////////
        application_lawyer.setFirstName(lawyer.getFirstName());
        application_lawyer.setLastName(lawyer.getLastName());
        application_lawyer.setCountry(lawyer.getCountry());
        application_lawyer.setAddress(lawyer.getAddress());
        application_lawyer.setCity(lawyer.getCity());
        application_lawyer.setState(lawyer.getState());
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
        return application_lawyer;
    }

    ////////////////////////////////////////////////

    ////////////////////////////////////////////////
    // create copy of owner object
    ////////////////////////////////////////////////
    Owner creaateOwnerCopy( Owner owner, PTOUser ptoUser, BaseTrademarkApplication baseTrademarkApplication){

        Owner application_owner = new Owner();
        /////////////////////////////////////////////////////////////////
        // copy over contact's lawyer's personal info
        /////////////////////////////////////////////////////////////////
        application_owner.setFirstName(owner.getFirstName());
        application_owner.setLastName(owner.getLastName());
        application_owner.setMidlleName(owner.getMidlleName());
        application_owner.setCountry(owner.getCountry());
        application_owner.setAddress(owner.getAddress());
        application_owner.setAddress1(owner.getAddress1());
        application_owner.setAddress2(owner.getAddress2());
        application_owner.setAddress3(owner.getAddress3());



        application_owner.setCity(owner.getCity());
        application_owner.setState(owner.getState());
        application_owner.setZipcode(owner.getZipcode());
        application_owner.setPrimaryPhonenumber(owner.getPrimaryPhonenumber());
        application_owner.setEmail(owner.getEmail());
        //////////////////////////////////////////////////////////////////
        // copy over contact's professional info
        //////////////////////////////////////////////////////////////////
        application_owner.setOwnerEnityType(owner.getOwnerEnityType());
        application_owner.setOwnerType(owner.getOwnerType());
        application_owner.setOwnersubType(owner.getOwnersubType());
        application_owner.setWebSiteURL(owner.getWebSiteURL());
        //////////////////////////////////////////////////////////////////
        // copy none individual owner info
        //////////////////////////////////////////////////////////////////


        application_owner.setOwnerName(owner.getOwnerName());
        application_owner.setOwnerDisplayname(owner.getOwnerDisplayname());
        application_owner.setGoverningEntities(owner.getGoverningEntities());
        application_owner.setOwnerOrganizationState(owner.getOwnerOrganizationState());



        ///////////////////////////////////////////////////////////////////
        // copy over contact's owner info  --->sets clientID to ptoUser
        ///////////////////////////////////////////////////////////////////
        application_owner.setClient(ptoUser);
        ///////////////////////////////////////////////////////////////////
        // set lawyer's pool ID ---> sets application's internal id as pool id
        ///////////////////////////////////////////////////////////////////
        application_owner.setTrademarkApplication(baseTrademarkApplication);
        return application_owner;
    }
    ////////////////////////////////////////////////




    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/mark/designType/{markType}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> setMarkType(@PathVariable String markType , @PathVariable String appInternalID){

   //////////////////////////////////////////////////////////
        // retrieve application using passed internal id
        //////////////////////////////////////////////////////////
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);

        baseTrademarkApplication.getTradeMark().setTrademarkDesignType(markType);



        //////////////////////////////////////////////////////////
        // find contact via email from PTOUser
        // create a copy of the contact object
        // add the copy of the contact to the application object
        // save application object
        ///////////////////////////////////////////////////////////



        baseTradeMarkApplicationService.save(baseTrademarkApplication);

        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = "Mark Type:"+markType+" has been set for the Application";
        return buildResponseEnity("200", responseMsg);

    }




    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/contacts/owner/delete/{contact_email}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationRemoveOwner(@PathVariable String contact_email , @PathVariable String appInternalID){


        if(verifyValidUserSession(contact_email) == false){
            String responseMsg = "Contact with email address :"+contact_email+ " has not been saved. invalid user session.";
            return buildResponseEnity("404", responseMsg);

        }
        //////////////////////////////////////////////////////////
        // retrieve application using passed internal id
        //////////////////////////////////////////////////////////
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);
        OwnerService ownerService = getServiceBeanFactory().getOwnerService();
        //////////////////////////////////////////////////////////
        // find contact via email from PTOUser
        // create a copy of the contact object
        // add the copy of the contact to the application object
        // save application object
        ///////////////////////////////////////////////////////////

        Owner delOwner;

        for(Iterator<Owner> iter = baseTrademarkApplication.getOwners().iterator(); iter.hasNext(); ) {
            //delLawyer = null;
            delOwner = iter.next();
            if(delOwner.getEmail().equals(contact_email)){

                if(baseTrademarkApplication.getOwners().size() == 1){
                    baseTrademarkApplication.setLastViewModel("application/owner/OwnerStart");
                    //baseTrademarkApplication.setAttorneyFiling(false);

                }

                baseTrademarkApplication.removeOwner(delOwner);

                ownerService.delete(delOwner);

            }
        }


        baseTradeMarkApplicationService.save(baseTrademarkApplication);

        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = "Contact with email address :"+contact_email+" has been removed from the Application";
        return buildResponseEnity("200", responseMsg);

    }







}
