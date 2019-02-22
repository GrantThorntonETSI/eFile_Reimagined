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
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Service
public class ContactsService extends  BaseRESTapiService {

    /////////////////////////////////////////////////////////////////////////////////////////
    // REST methods
    // add contact  param: lawyer-email
    // update contact info  param1: lawyer-field-name  parma2: lawyer-field-value
    /////////////////////////////////////////////////////////////////////////////////////////


    /////////////////////////////////////////////////////////////////////////////////////////
    // need to refactor attorney creation using form
    // these REST servies can still be used for auto save on contact update module
    /////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////
    // based on the profile  ...we should be able
    // to inject the correct bean mapped to the correct host file here
    ////////////////////////////////////////////////////////////////////////////////////////


    public ContactsService(ServiceBeanFactory serviceBeanFactory, HostBean hostBean) {
        super(serviceBeanFactory, hostBean);
    }

    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/contacts/lawyer/add/{contact_email}")

    @ResponseBody
    ResponseEntity<String> createContact(@PathVariable String contact_email){

        String appFieldReadable = "New Contact";
        ////////////////////////////////////////////////////////////////////////////////////////////////
        // check for valid security session ...as new contacts are added for PTOUser with valid sessions
        ////////////////////////////////////////////////////////////////////////////////////////////////
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if(verifyValidUserSession("xxx") == false){

            String responseMsg = appFieldReadable+" has not been saved";
            return buildResponseEnity("404", responseMsg );

        }
        PTOUserService ptoUserService = getServiceBeanFactory().getPTOUserService();
        // create Lawyer object and add it to PTOUser and save
        PTOUser ptoUser = ptoUserService.findByEmail(email);// ?? we may not need to save this
        // ?? check if contact already exists ???

        Lawyer lawyer = ptoUser.findLawyerContactByEmail(contact_email);
        if(lawyer != null){

            String responseMsg = appFieldReadable+" has not been saved Contact email exists";
            return buildResponseEnity("404", responseMsg );
        }


        Lawyer newLawyer = new Lawyer();
        newLawyer.setClient(ptoUser);
        //newLawyer.setLawFirmName("Grant Thornton, LLC");
        //newLawyer.setPoolMember(trademarkApplication);
        //newLawyer.setBarLicense("DC234567889");
        //newLawyer.setDocketNumber("100000000111");
        //newLawyer.setBarJurisdiction("DC");
        //newLawyer.setFirstName("test");
        //newLawyer.setLastName("lawyer");
        newLawyer.setEmail(contact_email);
        ptoUser.addLawyer(newLawyer);


        ptoUserService.save(ptoUser);

        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = appFieldReadable+" has been saved";
        return buildResponseEnity("200", responseMsg );
    }


    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/contacts/attorney/update/{contact_email}/{contact_field_name}/{contact_field_value}")
    @ResponseBody
    ResponseEntity<String> updateContact(@PathVariable String contact_email,@PathVariable String contact_field_name, @PathVariable String contact_field_value ){

        String appFieldReadable = "Contact";
        ////////////////////////////////////////////////////////////////////////////////////////////////
        // check for valid security session ...as new contacts are added for PTOUser with valid sessions
        ////////////////////////////////////////////////////////////////////////////////////////////////
        PTOUserService ptoUserService = getServiceBeanFactory().getPTOUserService();
        PTOUser ptoUser = getCurrentPTOuser();
        // verify authentication is valid before moving on ....
        // have to have a valid session

        if(ptoUser == null){ // can probably put this in a function
            String responseMsgs = appFieldReadable+" has not been saved";
            return buildResponseEnity("404", responseMsgs);

        }
        Lawyer lawyer = ptoUser.findLawyerContactByEmail(contact_email);


        if(contact_field_name.equals("attorney-first-name")){
            lawyer.setFirstName(contact_field_value);
            appFieldReadable = "Contact First Name ";

        }
        if(contact_field_name.equals("attorney-last-name")){
            lawyer.setLastName(contact_field_value);
            appFieldReadable = "Contact Last Name ";
        }
        if(contact_field_name.equals("attorney-lawfirm-name" )){
            lawyer.setLawFirmName(contact_field_value);
            appFieldReadable = "Contact Law Firm Name ";

        }

        if(contact_field_name.equals("attorney-country" )){
            lawyer.setCountry(contact_field_value);
            appFieldReadable = "Contact Country  ";

        }
        if(contact_field_name.equals("attorney-address1" )){
            lawyer.setAddress(contact_field_value);
            appFieldReadable = "Contact Street Address  ";

        }

        if(contact_field_name.equals("attorney-city" )){
            lawyer.setCity(contact_field_value);
            appFieldReadable = "Contact City  ";

        }

        if(contact_field_name.equals("attorney-state" )){
            lawyer.setState(contact_field_value);
            appFieldReadable = "Contact State  ";

        }

        if(contact_field_name.equals("attorney-zipcode" )){
            lawyer.setZipcode(contact_field_value);
            appFieldReadable = "Contact Zipcode  ";

        }

        if(contact_field_name.equals("attorney-phone" )){
            lawyer.setPrimaryPhonenumber(contact_field_value);
            appFieldReadable = "Contact Phone number ";

        }

        if(contact_field_name.equals("docket-id" )){
            lawyer.setDocketNumber(contact_field_value);
            appFieldReadable = "Contact Docket number ";

        }

        if(contact_field_name.equals("attorney-bar-standing" )){
            lawyer.setBarJurisdiction(contact_field_value);
            appFieldReadable = "Contact Affiliation ";

        }


        ptoUserService.save(ptoUser);
        LawyerService lawyerService = getServiceBeanFactory().getLawyerService();
        lawyerService.save(lawyer);

        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = appFieldReadable+" has been saved";
        return buildResponseEnity("200", responseMsg);
    }




    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/attorney/update/{contact_email}/{contact_field_name}/{contact_field_value}/{trademarkInternalID}")
    @ResponseBody
    ResponseEntity<String> updateAttorneyContact(@PathVariable String contact_email,@PathVariable String contact_field_name, @PathVariable String contact_field_value, @PathVariable String trademarkInternalID ){

        String appFieldReadable = "Attorney contact";


        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(trademarkInternalID);
        // verify authentication is valid before moving on ....
        // have to have a valid session

        if(contact_field_name.equals("attorney-first-name")){
            baseTrademarkApplication.findContactByEmail(contact_email).setFirstName(contact_field_value);
            appFieldReadable = "Attorney First Name ";

        }

        if(contact_field_name.equals("attorney-middle-name")){
            baseTrademarkApplication.findContactByEmail(contact_email).setMidlleName(contact_field_value);
            appFieldReadable = "Attorney Middle Name ";

        }
        if(contact_field_name.equals("attorney-last-name")){
            baseTrademarkApplication.findContactByEmail(contact_email).setLastName(contact_field_value);
            appFieldReadable = "Attorney Last Name ";
        }
        if(contact_field_name.equals("attorney-lawfirm-name" )){
            baseTrademarkApplication.findContactByEmail(contact_email).setLawFirmName(contact_field_value);
            appFieldReadable = "Attorney Law Firm Name ";

        }

        if(contact_field_name.equals("attorney-country" )){
            baseTrademarkApplication.findContactByEmail(contact_email).setCountry(contact_field_value);
            appFieldReadable = "Attorney Address Country ";

        }
        if(contact_field_name.equals("attorney-address1" )){
            baseTrademarkApplication.findContactByEmail(contact_email).setAddress1(contact_field_value);
            appFieldReadable = "Attorney Address ";

        }

        if(contact_field_name.equals("attorney-city" )){
            baseTrademarkApplication.findContactByEmail(contact_email).setCity(contact_field_value);
            appFieldReadable = "Attorney Address City ";

        }

        if(contact_field_name.equals("attorney-state" )){
            baseTrademarkApplication.findContactByEmail(contact_email).setState(contact_field_value);
            appFieldReadable = "Attorney Address State ";

        }

        if(contact_field_name.equals("attorney-zipcode" )){
            baseTrademarkApplication.findContactByEmail(contact_email).setZipcode(contact_field_value);
            appFieldReadable = "Attorney Address Zipcode ";

        }

        if(contact_field_name.equals("attorney-phone" )){
            baseTrademarkApplication.findContactByEmail(contact_email).setPrimaryPhonenumber(contact_field_value);
            appFieldReadable = "Attorney Phone Number ";

        }

        if(contact_field_name.equals("docket-id" )){
            baseTrademarkApplication.findContactByEmail(contact_email).setDocketNumber(contact_field_value);
            appFieldReadable = "Attorney Docket Number ";

        }

        if(contact_field_name.equals("attorney-bar-standing" )){
            baseTrademarkApplication.findContactByEmail(contact_email).setAffiliationStatus(contact_field_value);
            appFieldReadable = "Attorney Bar Standing status ";

        }

        if(contact_field_name.equals("attorney-bar-jurisdiction" )){
            baseTrademarkApplication.findContactByEmail(contact_email).setBarJurisdiction(contact_field_value);
            appFieldReadable = "Attorney Bar Jurisdiction ";

        }

        if(contact_field_name.equals("attorney-bar-membership" )){
            baseTrademarkApplication.findContactByEmail(contact_email).setMembershipNumber(contact_field_value);
            appFieldReadable = "Attorney Bar Membership Number ";

        }

        if(contact_field_name.equals("attorney-admission-date" )){
            //baseTrademarkApplication.findContactByEmail(contact_email).setBarAdmissionDate(contact_field_value);
            appFieldReadable = "Attorney Bar Admission Date ";

        }

        if(contact_field_name.equals("attorney-email" )){
            baseTrademarkApplication.findContactByEmail(contact_email).setEmail(contact_field_value);
            appFieldReadable = "Attorney Email ";

        }
       baseTradeMarkApplicationService.save(baseTrademarkApplication);


        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = appFieldReadable+" has been saved";
        return buildResponseEnity("200", responseMsg);
    }






    // need to add app id variable
    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/owner/update/{contact_email}/{contact_field_name}/{contact_field_value}/{trademarkInternalID}")
    @ResponseBody
    ResponseEntity<String> updateOwnerContact(@PathVariable String contact_email,@PathVariable String contact_field_name, @PathVariable String contact_field_value, @PathVariable String trademarkInternalID ){

        String appFieldReadable = "Owner contact";


        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(trademarkInternalID);
        ////////////////////////////////////////////////////////////////////////////////////////////////
        // check for valid security session ...as new contacts are added for PTOUser with valid sessions
        ////////////////////////////////////////////////////////////////////////////////////////////////

        if(contact_field_name.equals("first-name")){
            baseTrademarkApplication.findOwnerByEmail(contact_email).setFirstName(contact_field_value);
            appFieldReadable = "OwnerFirst Name ";

        }

        if(contact_field_name.equals("middle-name")){
            baseTrademarkApplication.findOwnerByEmail(contact_email).setMidlleName(contact_field_value);
            appFieldReadable = "Owner Middle Name ";

        }
        if(contact_field_name.equals("last-name")){
            baseTrademarkApplication.findOwnerByEmail(contact_email).setLastName(contact_field_value);
            appFieldReadable = "OwnerLast Name ";
        }

        if(contact_field_name.equals("owner-name-corp")){
            baseTrademarkApplication.findOwnerByEmail(contact_email).setOwnerName(contact_field_value);
            appFieldReadable = "Owner Name";

        }

        if(contact_field_name.equals("owner-type-additional-name")){
            baseTrademarkApplication.findOwnerByEmail(contact_email).setOwnerType(contact_field_value);
            appFieldReadable = "Owner Additional Name Type";

        }

        if(contact_field_name.equals("state-of-organization")){
            baseTrademarkApplication.findOwnerByEmail(contact_email).setOwnerOrganizationState(contact_field_value);
            appFieldReadable = "Owner State of Organization";

        }
        if(contact_field_name.equals("citizenship" )){
            baseTrademarkApplication.findOwnerByEmail(contact_email).setCitizenShip(contact_field_value);
            appFieldReadable = "Contact Citizenship ";

        }

        if(contact_field_name.equals("email" )){
            baseTrademarkApplication.findOwnerByEmail(contact_email).setEmail(contact_field_value);
            appFieldReadable = "Contact Email ";

        }
        if(contact_field_name.equals("country" )){
            baseTrademarkApplication.findOwnerByEmail(contact_email).setCountry(contact_field_value);
            appFieldReadable = "Contact Address Country ";

        }
        if(contact_field_name.equals("address1" )){
            baseTrademarkApplication.findOwnerByEmail(contact_email).setAddress(contact_field_value);
            appFieldReadable = "Contact Street Address  ";

        }

        if(contact_field_name.equals("city" )){
            baseTrademarkApplication.findOwnerByEmail(contact_email).setCity(contact_field_value);
            appFieldReadable = "Contact City  ";

        }

        if(contact_field_name.equals("state" )){
            baseTrademarkApplication.findOwnerByEmail(contact_email).setState(contact_field_value);
            appFieldReadable = "Contact State  ";

        }

        if(contact_field_name.equals("zipcode" )){
            baseTrademarkApplication.findOwnerByEmail(contact_email).setZipcode(contact_field_value);
            appFieldReadable = "Contact Zipcode  ";

        }

        if(contact_field_name.equals("phone" )){
            baseTrademarkApplication.findOwnerByEmail(contact_email).setPrimaryPhonenumber(contact_field_value);
            appFieldReadable = "Contact Phone number ";

        }

        if(contact_field_name.equals("web" )){
            baseTrademarkApplication.findOwnerByEmail(contact_email).setWebSiteURL(contact_field_value);
            appFieldReadable = "Contact Phone number ";

        }





        baseTradeMarkApplicationService.save(baseTrademarkApplication);


        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = appFieldReadable+" has been saved";
        return buildResponseEnity("200", responseMsg);





    }


}
