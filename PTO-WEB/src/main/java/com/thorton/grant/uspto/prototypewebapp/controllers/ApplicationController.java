package com.thorton.grant.uspto.prototypewebapp.controllers;


import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.participants.OwnerService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.RegistrationDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.application.ContactsDisplayDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.application.SelectedContactsDisplayDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.application.form.NewAttorneyContactFormDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.application.form.NewOwnerContactFormDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.application.form.partnerDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Owner;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Partner;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.ManagedContact;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.*;

@Controller
public class ApplicationController {

    private final  ServiceBeanFactory serviceBeanFactory;
    private static long counter = 3000000;


    /////////////////////////////////////////////////////////////////////////////////////////
    // based on the profile  ...we should be able
    // to inject the correct bean mapped to the correct host file here
    ////////////////////////////////////////////////////////////////////////////////////////
    private final HostBean hostBean;


    private final ApplicationContext appContext;

    public ApplicationController(ServiceBeanFactory serviceBeanFactory, ApplicationContext appContext) {
        this.serviceBeanFactory = serviceBeanFactory;
        this.appContext = appContext;

        this.hostBean = (HostBean) appContext.getBean(HostBean.class);

    }

    //private boolean continuation = false;

    @Transactional
    @RequestMapping({"/application/start","application/start"})
    public String applicationStart
        (WebRequest request, Model model, @RequestParam("trademarkID") String trademarkInternalID) {

        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();

        ////////////////////////////////////////////////////////////////////////////////////////////
        // add contacts display info to model
        ////////////////////////////////////////////////////////////////////////////////////////////



        /////////////////////////////////////////////////////////////////////////////////////////////
        // create new check
        /////////////////////////////////////////////////////////////////////////////////////////////
        // check if user has LoadContinueUrl set ...
        // in the web url that triggers, you get that from an iteration of
        // myTradeMarks on the dashboard
        /////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////////////////
        // load my contacts list for thyemleaf
        /////////////////////////////////////////////
        ArrayList<String> contactNames = new ArrayList<>();
        ArrayList<String> contactEmails = new ArrayList<>();
        ArrayList<String> contactFirms = new ArrayList<>();
        Lawyer lawyer = null;

        for(Iterator<Lawyer> iter = ptoUser.getMyLawyers().iterator(); iter.hasNext(); ) {
            lawyer = iter.next();
            contactNames.add(lawyer.getFirstName()+" "+lawyer.getLastName());
            contactEmails.add(lawyer.getEmail());
            contactFirms.add(lawyer.getLawFirmName());

        }
        Collections.reverse(contactNames);
        Collections.reverse(contactEmails);
        Collections.reverse(contactFirms);
        ContactsDisplayDTO contactsDisplayDTO = new ContactsDisplayDTO();
        contactsDisplayDTO.setContactNames(contactNames);
        contactsDisplayDTO.setContactEmails(contactEmails);
        contactsDisplayDTO.setContactFirms(contactFirms);
        model.addAttribute("myContacts", contactsDisplayDTO);
        SelectedContactsDisplayDTO selectedContactsDisplayDTO = new SelectedContactsDisplayDTO();


        /////////////////////////////////////////////
        // load my contacts list for thyemleaf
        ////////////////////////////////////////////
        ArrayList<String> contactNamesMC = new ArrayList<>();
        ArrayList<String> contactEmailsMC = new ArrayList<>();
        ArrayList<String> contactSubTypesMC = new ArrayList<>();
        ManagedContact managedContact = null;

        for(Iterator<ManagedContact> iter = ptoUser.getMyManagedContacts().iterator(); iter.hasNext(); ) {
            managedContact = iter.next();
            contactNamesMC.add(managedContact.getDisplayName());
            contactEmailsMC.add(managedContact.getEmail());
            contactSubTypesMC.add(managedContact.getContactType());

        }
        Collections.reverse(contactNamesMC);
        Collections.reverse(contactEmailsMC);
        Collections.reverse(contactSubTypesMC);
        ContactsDisplayDTO mcDisplayDTO = new ContactsDisplayDTO();
        mcDisplayDTO.setContactNames(contactNamesMC);
        mcDisplayDTO.setContactEmails(contactEmailsMC);
        mcDisplayDTO.setContactEntitySubType(contactSubTypesMC);
        model.addAttribute("myManagedContacts", mcDisplayDTO);



        if(trademarkInternalID.equals("new")) {

            BaseTrademarkApplication trademarkApplication = new BaseTrademarkApplication();
            //trademarkApplication.setLastViewModel("application/owner/individual/ownerInfo");
            //trademarkApplication.setLastViewModel("application/OwnerStart");
            trademarkApplication.setLastViewModel("application/AttorneyStart");
            trademarkApplication.setAttorneySet(false);
            trademarkApplication.setAttorneyFiling(false);

            ///////////////////////////////////////////////////////////////////////////////////////////////
            // we need a copy constructor ...so that trademark Application lawyers are not the same ones
            // saved by PTOUser ...
            // as this will allow PTOUser to delete the application with out deleting his/hers lawyers.
            ///////////////////////////////////////////////////////////////////////////////////////////////
            // application also needs a better id for find ...build internal id. as user.email+trademark_name???
            //Lawyer appPrimaryConsole = new Lawyer(PTOUser1.getMyLawyers().iterator().next());
            trademarkApplication.setOwnerEmail(ptoUser.getEmail());

            //Owner owner = new Owner();
           // owner.setOwnerType("individual");
           // owner.setEmail(ptoUser.getEmail());
           // owner.setAddress(ptoUser.getAddress());
           // owner.setFirstName(ptoUser.getFirstName());
           // owner.setLastName(ptoUser.getLastName());
           // owner.setCity(ptoUser.getCity());
           // owner.setState(ptoUser.getState());

           // trademarkApplication.setOwner(owner);
            /////////////////////////////////////////////////////////////////////////////////
            // add a method to PTOUser to just add one application
            /////////////////////////////////////////////////////////////////////////////////
            baseTradeMarkApplicationService.save(trademarkApplication);
            trademarkApplication.setTrademarkName("my_first_trademark");
            trademarkApplication.setApplicationInternalID(UUID.randomUUID().toString());
            counter++;
            trademarkApplication.setTrademarkName(""+counter);
            ptoUser.addApplication(trademarkApplication); // adds to myApplications Collection
            ptoUserService.save(ptoUser);
            model.addAttribute("baseTrademarkApplication", trademarkApplication);


            // set empty selected contacts for thymeleaf
            ArrayList<String> scontactNames = new ArrayList<>();
            selectedContactsDisplayDTO.setSelectedNames(scontactNames);
            model.addAttribute("selectedContacts", selectedContactsDisplayDTO);





        }
        else{

            ////////////////////////////////////////////////////////////////////////////
            // existing trade  mark application
            ////////////////////////////////////////////////////////////////////////////
            // loaded baseTradeMarkapplication by internal id and add to model
            ////////////////////////////////////////////////////////////////////////////
            // BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(trademarkInternalID);
            // we are already setting this value in the begging for selected contacts rendering
            /////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////
            // add relationship (i.e grey out the correspondant contacts table)
            // we can do this when rendering the contacts table rows ...
            // do a check and insert different HTML for the row
            //////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////////////////
            // add selected contacts display info to model
            ////////////////////////////////////////////////////////////////////////////////////////////
            ArrayList<String> selectedContactNames = new ArrayList<>();
            Lawyer selected_lawyer = null;
            BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(trademarkInternalID);
            Set<Lawyer> applicationLawyerPool = baseTrademarkApplication.getAvailableLawyers();

            if(applicationLawyerPool != null){
                for(Iterator<Lawyer> iterSelectedContacts = baseTrademarkApplication.getAvailableLawyers().iterator(); iterSelectedContacts.hasNext(); ) {
                    selected_lawyer = iterSelectedContacts.next();
                    selectedContactNames.add(selected_lawyer.getFirstName()+" "+selected_lawyer.getLastName());

                }
                Collections.reverse(selectedContactNames);
                /////////////////////////////////////////////////////////////////////////////////////////////
                // we need a DTO for passing data to view layer
                /////////////////////////////////////////////////////////////////////////////////////////////

                selectedContactsDisplayDTO.setSelectedNames(selectedContactNames);
                model.addAttribute("selectedContacts", selectedContactsDisplayDTO);

            }
            else{
                // if there are no one in the application available pool ..
                // simply add empty value.

                selectedContactNames.add("");
                selectedContactsDisplayDTO.setSelectedNames(selectedContactNames);
                model.addAttribute("selectedContacts", selectedContactsDisplayDTO);
            }



            // baseTrademarkApplication.setLastViewModel("application/AttorneyStart");

            model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);

            /////////////////////////////////////////////
            // load my contacts list for thyemleaf



        }

        model.addAttribute("hostBean", hostBean);
        return "application/AttorneyStart";




    }

    // hopefully just a redirecta here, we won't need to add the applicaiton and credentials to the model
    @RequestMapping({"/application/attorneyEntity/"})
    public String attorneyEntity(WebRequest request, Model model, @RequestParam("trademarkID") String trademarkInternalID) {

        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(trademarkInternalID);
        model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);

        model.addAttribute("hostBean", hostBean);
        return "application/attorney/attorneyEntity";
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////
    // controller for AttorneySet page
    /////////////////////////////////////////////////////////////////////////////////////////////////
    @Transactional
    @RequestMapping({"/application/AttorneySet","application/AttorneySet"})
    public String applicationAttorneySet
            (WebRequest request, Model model, @RequestParam("trademarkID") String trademarkInternalID) {

        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();

        ////////////////////////////////////////////////////////////////////////////////////////////
        // add contacts display info to model
        ////////////////////////////////////////////////////////////////////////////////////////////



        /////////////////////////////////////////////////////////////////////////////////////////////
        // create new check
        /////////////////////////////////////////////////////////////////////////////////////////////
        // check if user has LoadContinueUrl set ...
        // in the web url that triggers, you get that from an iteration of
        // myTradeMarks on the dashboard
        /////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////////////////
        // load my contacts list for thyemleaf
        /////////////////////////////////////////////
        ArrayList<String> contactNames = new ArrayList<>();
        ArrayList<String> contactEmails = new ArrayList<>();
        ArrayList<String> contactFirms = new ArrayList<>();
        Lawyer lawyer = null;

        for(Iterator<Lawyer> iter = ptoUser.getMyLawyers().iterator(); iter.hasNext(); ) {
            lawyer = iter.next();
            contactNames.add(lawyer.getFirstName()+" "+lawyer.getLastName());
            contactEmails.add(lawyer.getEmail());
            contactFirms.add(lawyer.getLawFirmName());

        }
        Collections.reverse(contactNames);
        Collections.reverse(contactEmails);
        Collections.reverse(contactFirms);
        ContactsDisplayDTO contactsDisplayDTO = new ContactsDisplayDTO();
        contactsDisplayDTO.setContactNames(contactNames);
        contactsDisplayDTO.setContactEmails(contactEmails);
        contactsDisplayDTO.setContactFirms(contactFirms);
        model.addAttribute("myContacts", contactsDisplayDTO);
        SelectedContactsDisplayDTO selectedContactsDisplayDTO = new SelectedContactsDisplayDTO();



        if(trademarkInternalID.equals("new")) {

            BaseTrademarkApplication trademarkApplication = new BaseTrademarkApplication();
            //trademarkApplication.setLastViewModel("application/owner/individual/ownerInfo");
            //trademarkApplication.setLastViewModel("application/OwnerStart");
            trademarkApplication.setLastViewModel("application/AttorneySet");
            trademarkApplication.setAttorneySet(false);
            trademarkApplication.setAttorneyFiling(false);

            ///////////////////////////////////////////////////////////////////////////////////////////////
            // we need a copy constructor ...so that trademark Application lawyers are not the same ones
            // saved by PTOUser ...
            // as this will allow PTOUser to delete the application with out deleting his/hers lawyers.
            ///////////////////////////////////////////////////////////////////////////////////////////////
            // application also needs a better id for find ...build internal id. as user.email+trademark_name???
            //Lawyer appPrimaryConsole = new Lawyer(PTOUser1.getMyLawyers().iterator().next());
            trademarkApplication.setOwnerEmail(ptoUser.getEmail());

            //Owner owner = new Owner();
            //owner.setOwnerType("individual");
            //owner.setEmail(ptoUser.getEmail());
            //owner.setAddress(ptoUser.getAddress());
            //owner.setFirstName(ptoUser.getFirstName());
            //owner.setLastName(ptoUser.getLastName());
            //owner.setCity(ptoUser.getCity());
            //owner.setState(ptoUser.getState());

            //trademarkApplication.setOwner(owner);
            /////////////////////////////////////////////////////////////////////////////////
            // add a method to PTOUser to just add one application
            /////////////////////////////////////////////////////////////////////////////////
            baseTradeMarkApplicationService.save(trademarkApplication);
            trademarkApplication.setTrademarkName("my_first_trademark");
            trademarkApplication.setApplicationInternalID(UUID.randomUUID().toString());
            counter++;
            trademarkApplication.setTrademarkName(""+counter);
            ptoUser.addApplication(trademarkApplication); // adds to myApplications Collection
            ptoUserService.save(ptoUser);
            model.addAttribute("baseTrademarkApplication", trademarkApplication);


            // set empty selected contacts for thymeleaf
            ArrayList<String> scontactNames = new ArrayList<>();
            scontactNames.add("");
            selectedContactsDisplayDTO.setSelectedNames(scontactNames);
            model.addAttribute("selectedContacts", selectedContactsDisplayDTO);
            model.addAttribute("lawyerPool", trademarkApplication.getAvailableLawyers());



        }
        else{

            ////////////////////////////////////////////////////////////////////////////
            // existing trade  mark application
            ////////////////////////////////////////////////////////////////////////////
            // loaded baseTradeMarkapplication by internal id and add to model
            ////////////////////////////////////////////////////////////////////////////
            // BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(trademarkInternalID);
            // we are already setting this value in the begging for selected contacts rendering
            /////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////
            // add relationship (i.e grey out the correspondant contacts table)
            // we can do this when rendering the contacts table rows ...
            // do a check and insert different HTML for the row
            //////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////////////////
            // add selected contacts display info to model
            ////////////////////////////////////////////////////////////////////////////////////////////
            ArrayList<String> selectedContactNames = new ArrayList<>();
            Lawyer selected_lawyer = null;
            BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(trademarkInternalID);
            Set<Lawyer> applicationLawyerPool = baseTrademarkApplication.getAvailableLawyers();

            if(applicationLawyerPool != null){
                for(Iterator<Lawyer> iterSelectedContacts = baseTrademarkApplication.getAvailableLawyers().iterator(); iterSelectedContacts.hasNext(); ) {
                    selected_lawyer = iterSelectedContacts.next();
                    selectedContactNames.add(selected_lawyer.getFirstName()+" "+selected_lawyer.getLastName());

                }
                Collections.reverse(selectedContactNames);
                /////////////////////////////////////////////////////////////////////////////////////////////
                // we need a DTO for passing data to view layer
                /////////////////////////////////////////////////////////////////////////////////////////////

                selectedContactsDisplayDTO.setSelectedNames(selectedContactNames);
                model.addAttribute("selectedContacts", selectedContactsDisplayDTO);

            }
            else{
                // if there are no one in the application available pool ..
                // simply add empty value.

                selectedContactNames.add("");
                selectedContactsDisplayDTO.setSelectedNames(selectedContactNames);
                model.addAttribute("selectedContacts", selectedContactsDisplayDTO);
            }



            // baseTrademarkApplication.setLastViewModel("application/AttorneyStart");

            model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);
            model.addAttribute("lawyerPool", baseTrademarkApplication.getAvailableLawyers());


        }



        model.addAttribute("hostBean", hostBean);
        return "application/AttorneySet";

    }


    // show form for attorney add module ... this page is loaded into modal
    @RequestMapping({"/application/attorney/attorneyInfo"})
    public String attorneyContactshowFormInfo(WebRequest request, Model model, @RequestParam("trademarkID") String trademarkInternalID) {

        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(trademarkInternalID);
        model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);

        model.addAttribute("hostBean", hostBean);

        NewAttorneyContactFormDTO attorneyContactFormDTO = new NewAttorneyContactFormDTO();
        model.addAttribute("addNewAttorneyContactFormDTO", attorneyContactFormDTO);


        return "application/attorney/attorneyInfo";


    }




    // hopefully just a redirect here, we won't need to add the applicaiton and credentials to the model
    @RequestMapping(value = "/attorney/add", method = RequestMethod.POST)
    public String addAttorneyContact( @RequestParam(name="file", required=false) MultipartFile file,
                                        Model model,
                                      @ModelAttribute("addNewAttorneyContactFormDTO") @Valid NewAttorneyContactFormDTO newAttorneyContactFormDTO,
                                      WebRequest request,
                                      BindingResult result,
                                      Errors errors) {

        System.out.println("11111111111111111111111111111111111111111111");
        System.out.println("attorney add module !!!!!!!!");
        System.out.println("11111111111111111111111111111111111111111111");

        System.out.println("app inernal id: "+newAttorneyContactFormDTO.getAppInternalID());
        // is this even necessary ..since this is added to the PTO user object ???


        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());


        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(newAttorneyContactFormDTO.getAppInternalID());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);
        model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);

        model.addAttribute("hostBean", hostBean);
        String trademarkInternalID = baseTrademarkApplication.getApplicationInternalID();

        ////////////////////////////////////////////////////////////////////////
        //add new attorney contact business logic
        ////////////////////////////////////////////////////////////////////////
        // create lawyer ...and set it to PTO user ..then save PTO user object and add it to model
        // set all fields from DTO
        ////////////////////////////////////////////////////////////////////////
        Lawyer lawyer = new Lawyer();
        //owner.setOwnerEnityType(baseTrademarkApplication.getOwnerType());
        //owner.setOwnersubType(baseTrademarkApplication.getOwnerSubType());
        //baseTrademarkApplication.setOwnerSubType(null);
        //baseTrademarkApplication.setOwnerType(null);
        //
        ////////////////////////////////////////////////////////////////////////
        // transfer and reset owner type and subtype
        ////////////////////////////////////////////////////////////////////////
        // required field no null checks
        ////////////////////////////////////////////////////////////////////////

        if(newAttorneyContactFormDTO.getFirstName()!= null){
            if(baseTrademarkApplication.findAttorneyContactByDisplayName(newAttorneyContactFormDTO.getFirstName()+ " "+newAttorneyContactFormDTO.getLastName()) != null){
                model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);
                ArrayList<String> contactNames = new ArrayList<>();
                ArrayList<String> contactEmails = new ArrayList<>();
                ArrayList<String> contactSubTypes = new ArrayList<>();
                ManagedContact managedContact = null;

                for(Iterator<ManagedContact> iter = ptoUser.getMyManagedContacts().iterator(); iter.hasNext(); ) {
                    managedContact = iter.next();
                    contactNames.add(managedContact.getDisplayName());
                    contactEmails.add(managedContact.getEmail());
                    contactSubTypes.add(managedContact.getContactType());

                }
                Collections.reverse(contactNames);
                Collections.reverse(contactEmails);
                Collections.reverse(contactSubTypes);
                ContactsDisplayDTO contactsDisplayDTO = new ContactsDisplayDTO();
                contactsDisplayDTO.setContactNames(contactNames);
                contactsDisplayDTO.setContactEmails(contactEmails);
                contactsDisplayDTO.setContactEntitySubType(contactSubTypes);
                model.addAttribute("myManagedContacts", contactsDisplayDTO);

                // also add error message
                model.addAttribute("message", "ERROR: Attorney Name exists for this Application.");

                return "application/AttorneyStart";
                // return to  ownerStartPage with error message
            }


            //owner.setOwnerDisplayname(newOwnerContactFormDTO.getFirstName()+ " "+newOwnerContactFormDTO.getLastName());
            //owner.setFirstName(newOwnerContactFormDTO.getFirstName());

            lawyer.setFirstName(newAttorneyContactFormDTO.getFirstName());

        }




        lawyer.setLastName(newAttorneyContactFormDTO.getLastName());
        if(newAttorneyContactFormDTO.getMiddleName() != null){
            lawyer.setMidlleName(newAttorneyContactFormDTO.getMiddleName());
        }
        if(newAttorneyContactFormDTO.getSuffix() != null){
            lawyer.setSuffix(newAttorneyContactFormDTO.getSuffix());
        }
        lawyer.setLawFirmName(newAttorneyContactFormDTO.getLawFirmName());

        if(newAttorneyContactFormDTO.getAttorneyAddressCountry() != null){
            lawyer.setCountry(newAttorneyContactFormDTO.getAttorneyAddressCountry());
        }

        if(newAttorneyContactFormDTO.getAttorneyAddress1() != null){
            lawyer.setAddress1(newAttorneyContactFormDTO.getAttorneyAddress1());
            lawyer.setAddress(newAttorneyContactFormDTO.getAttorneyAddress1());
        }

        if(newAttorneyContactFormDTO.getAttorneyAddress2() != null){
            lawyer.setAddress2(newAttorneyContactFormDTO.getAttorneyAddress2());
        }

        if(newAttorneyContactFormDTO.getAttorneyAddress3() != null){
            lawyer.setAddress3(newAttorneyContactFormDTO.getAttorneyAddress3());
        }
        if(newAttorneyContactFormDTO.getAttorneyCity() != null){
            lawyer.setCity(newAttorneyContactFormDTO.getAttorneyCity());
        }

        if(newAttorneyContactFormDTO.getAttorneyState() != null){
            lawyer.setState(newAttorneyContactFormDTO.getAttorneyState());
        }
        if(newAttorneyContactFormDTO.getAttorneyZipcode() != null){
            lawyer.setZipcode(newAttorneyContactFormDTO.getAttorneyZipcode());
        }
        if(newAttorneyContactFormDTO.getAttorneyEmail()!= null){
            if(baseTrademarkApplication.findContactByEmail(newAttorneyContactFormDTO.getAttorneyEmail()) != null){
                model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);
                ArrayList<String> contactNames = new ArrayList<>();
                ArrayList<String> contactEmails = new ArrayList<>();
                ArrayList<String> contactSubTypes = new ArrayList<>();
                ManagedContact managedContact = null;

                for(Iterator<ManagedContact> iter = ptoUser.getMyManagedContacts().iterator(); iter.hasNext(); ) {
                    managedContact = iter.next();
                    contactNames.add(managedContact.getDisplayName());
                    contactEmails.add(managedContact.getEmail());
                    contactSubTypes.add(managedContact.getContactType());

                }
                Collections.reverse(contactNames);
                Collections.reverse(contactEmails);
                Collections.reverse(contactSubTypes);
                ContactsDisplayDTO contactsDisplayDTO = new ContactsDisplayDTO();
                contactsDisplayDTO.setContactNames(contactNames);
                contactsDisplayDTO.setContactEmails(contactEmails);
                contactsDisplayDTO.setContactEntitySubType(contactSubTypes);
                model.addAttribute("myManagedContacts", contactsDisplayDTO);

                // also add error message
                model.addAttribute("message", "ERROR: Attorney email exists for this Application.");

                return "application/AttorneyStart";
                // return to  ownerStartPage with error message
            }


            //owner.setOwnerDisplayname(newOwnerContactFormDTO.getFirstName()+ " "+newOwnerContactFormDTO.getLastName());
            //owner.setFirstName(newOwnerContactFormDTO.getFirstName());


                lawyer.setEmail(newAttorneyContactFormDTO.getAttorneyEmail());



        }


        if(newAttorneyContactFormDTO.getAttorneyPhone() != null){
            lawyer.setPrimaryPhonenumber(newAttorneyContactFormDTO.getAttorneyPhone());
        }
        if(newAttorneyContactFormDTO.getAttorneyDocketNumber()!= null){
            lawyer.setDocketNumber(newAttorneyContactFormDTO.getAttorneyDocketNumber());
        }
        if(newAttorneyContactFormDTO.getAttorneyAffiliation()!= null){
            lawyer.setBarJurisdiction(newAttorneyContactFormDTO.getAttorneyAffiliation());
        }

       baseTrademarkApplication.addAvailableLawyer(lawyer);
        baseTradeMarkApplicationService.save(baseTrademarkApplication);

        return "forward:/application/AttorneySet/?trademarkID="+trademarkInternalID;
    }











    // hopefully just a redirect here, we won't need to add the applicaiton and credentials to the model
    @RequestMapping({"/application/OwnerStart"})
    public String ownerStart (WebRequest request, Model model, @RequestParam("trademarkID") String trademarkInternalID) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());

        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);

        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(trademarkInternalID);
        baseTrademarkApplication.setLastViewModel("application/OwnerStart");
        model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);

        model.addAttribute("hostBean", hostBean);
        /////////////////////////////////////////////
        // load my contacts list for thyemleaf
        /////////////////////////////////////////////
        ArrayList<String> contactNames = new ArrayList<>();
        ArrayList<String> contactEmails = new ArrayList<>();
        ArrayList<String> contactSubTypes = new ArrayList<>();
        ManagedContact managedContact = null;

        for(Iterator<ManagedContact> iter = ptoUser.getMyManagedContacts().iterator(); iter.hasNext(); ) {
            managedContact = iter.next();
            contactNames.add(managedContact.getDisplayName());
            contactEmails.add(managedContact.getEmail());
            contactSubTypes.add(managedContact.getContactType());

        }
        Collections.reverse(contactNames);
        Collections.reverse(contactEmails);
        Collections.reverse(contactSubTypes);
        ContactsDisplayDTO contactsDisplayDTO = new ContactsDisplayDTO();
        contactsDisplayDTO.setContactNames(contactNames);
        contactsDisplayDTO.setContactEmails(contactEmails);
        contactsDisplayDTO.setContactEntitySubType(contactSubTypes);
        model.addAttribute("myManagedContacts", contactsDisplayDTO);


        ///////////////////////////////////////////////////////////////
        // set selected owner contacts ...
        // since there is just one owner ...
        // just set it and test for null


        return "application/OwnerStart";
    }


    // hopefully just a redirect here, we won't need to add the applicaiton and credentials to the model
    @RequestMapping({"/owner/entity/"})
    public String ownerEntity(WebRequest request, Model model, @RequestParam("trademarkID") String trademarkInternalID) {

        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(trademarkInternalID);
        model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);

        model.addAttribute("hostBean", hostBean);
        return "application/owner/ownerEntity";
    }




    // hopefully just a redirect here, we won't need to add the applicaiton and credentials to the model
    @RequestMapping({"/application/owner/Ind/info"})
    public String ownerIndvUSInfo(WebRequest request, Model model, @RequestParam("trademarkID") String trademarkInternalID) {

        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(trademarkInternalID);
        model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);

        model.addAttribute("hostBean", hostBean);

        NewOwnerContactFormDTO newOwnerContactFormDTO = new NewOwnerContactFormDTO();
        model.addAttribute("addNewOwnerContactFormDTO", newOwnerContactFormDTO);


        return "application/owner/individual/ownerInfo";
    }

    // hopefully just a redirect here, we won't need to add the applicaiton and credentials to the model
    @RequestMapping(value = "/owner/add", method = RequestMethod.POST)
    public String addOwnerContact( Model model,
                                   @ModelAttribute("addNewOwnerContactFormDTO") @Valid NewOwnerContactFormDTO newOwnerContactFormDTO,
                                   WebRequest request,
                                   BindingResult result,
                                   Errors errors) {



        // check if owner already exists



        // reset application entity type and subtype
        // transfer those entity type and subtype to the new owner object



        System.out.println("11111111111111111111111111111111111111111111");
        System.out.println("owner add module !!!!!!!!");
        System.out.println("11111111111111111111111111111111111111111111");

        System.out.println("app inernal id: "+newOwnerContactFormDTO.getAppInternalID());

        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());


        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(newOwnerContactFormDTO.getAppInternalID());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);
        model.addAttribute("hostBean", hostBean);

        ////////////////////////////////////////////////////////////////////////
        //add new owner contact business logic
        ////////////////////////////////////////////////////////////////////////
        // create owner ...and set it to PTO user ..then save PTO user object and add it to model
        Owner owner = new Owner();
        owner.setOwnerEnityType(baseTrademarkApplication.getOwnerType());
        owner.setOwnersubType(baseTrademarkApplication.getOwnerSubType());
        baseTrademarkApplication.setOwnerSubType(null);
        baseTrademarkApplication.setOwnerType(null);

        System.out.println("OWNER SUB TYPE : "+owner.getOwnersubType());
        // transfer and reset owner type and subtype

        if(newOwnerContactFormDTO.getFirstName()!= null){
            if(baseTrademarkApplication.findOwnerByDisplayName(newOwnerContactFormDTO.getFirstName()+ " "+newOwnerContactFormDTO.getLastName()) != null){
                model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);
                ArrayList<String> contactNames = new ArrayList<>();
                ArrayList<String> contactEmails = new ArrayList<>();
                ArrayList<String> contactSubTypes = new ArrayList<>();
                ManagedContact managedContact = null;

                for(Iterator<ManagedContact> iter = ptoUser.getMyManagedContacts().iterator(); iter.hasNext(); ) {
                    managedContact = iter.next();
                    contactNames.add(managedContact.getDisplayName());
                    contactEmails.add(managedContact.getEmail());
                    contactSubTypes.add(managedContact.getContactType());

                }
                Collections.reverse(contactNames);
                Collections.reverse(contactEmails);
                Collections.reverse(contactSubTypes);
                ContactsDisplayDTO contactsDisplayDTO = new ContactsDisplayDTO();
                contactsDisplayDTO.setContactNames(contactNames);
                contactsDisplayDTO.setContactEmails(contactEmails);
                contactsDisplayDTO.setContactEntitySubType(contactSubTypes);
                model.addAttribute("myManagedContacts", contactsDisplayDTO);

                // also add error message
                model.addAttribute("message", "ERROR: Owner Name exists for this Application.");

                return "application/OwnerStart";
                // return to  ownerStartPage with error message
            }


            owner.setOwnerDisplayname(newOwnerContactFormDTO.getFirstName()+ " "+newOwnerContactFormDTO.getLastName());
            owner.setFirstName(newOwnerContactFormDTO.getFirstName());



        }
        if(newOwnerContactFormDTO.getLastName()!= null) {
            owner.setLastName(newOwnerContactFormDTO.getLastName());
        }
        if(newOwnerContactFormDTO.getMiddleName() != null){
            owner.setMidlleName(newOwnerContactFormDTO.getMiddleName());
        }
        if(newOwnerContactFormDTO.getSuffix() != null){
            owner.setSuffix(newOwnerContactFormDTO.getSuffix());
        }
        if(newOwnerContactFormDTO.getOwnerType() != null){
          owner.setOwnerType(newOwnerContactFormDTO.getOwnerType());
        }
        if(newOwnerContactFormDTO.getOwnerCitizenShip()!= null){
            owner.setCitizenShip(newOwnerContactFormDTO.getOwnerCitizenShip());
        }
        owner.setCountry(newOwnerContactFormDTO.getOwnerAddressCountry());
        owner.setAddress1(newOwnerContactFormDTO.getOwnerAddress1());
        owner.setAddress(newOwnerContactFormDTO.getOwnerAddress1());
        // set both address and address1
        if(newOwnerContactFormDTO.getOwnerAddress2() != null){
            owner.setAddress2(newOwnerContactFormDTO.getOwnerAddress2());
        }
        if(newOwnerContactFormDTO.getOwnerAddress3() != null){
            owner.setAddress3(newOwnerContactFormDTO.getOwnerAddress3());
        }

        owner.setCity(newOwnerContactFormDTO.getOwnerCity());

        if(newOwnerContactFormDTO.getOwnerState() != null){
            owner.setState(newOwnerContactFormDTO.getOwnerState());
        }
        if(newOwnerContactFormDTO.getOwnerZipcode() != null){
            owner.setZipcode(newOwnerContactFormDTO.getOwnerZipcode());
        }
        if(newOwnerContactFormDTO.getOwnerEmail() != null){
            if(baseTrademarkApplication.findOwnerByEmail(newOwnerContactFormDTO.getOwnerEmail()) != null){
                model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);
                ArrayList<String> contactNames = new ArrayList<>();
                ArrayList<String> contactEmails = new ArrayList<>();
                ArrayList<String> contactSubTypes = new ArrayList<>();
                ManagedContact managedContact = null;

                for(Iterator<ManagedContact> iter = ptoUser.getMyManagedContacts().iterator(); iter.hasNext(); ) {
                    managedContact = iter.next();
                    contactNames.add(managedContact.getDisplayName());
                    contactEmails.add(managedContact.getEmail());
                    contactSubTypes.add(managedContact.getContactType());

                }
                Collections.reverse(contactNames);
                Collections.reverse(contactEmails);
                Collections.reverse(contactSubTypes);
                ContactsDisplayDTO contactsDisplayDTO = new ContactsDisplayDTO();
                contactsDisplayDTO.setContactNames(contactNames);
                contactsDisplayDTO.setContactEmails(contactEmails);
                contactsDisplayDTO.setContactEntitySubType(contactSubTypes);
                model.addAttribute("myManagedContacts", contactsDisplayDTO);

                // also add error message
                model.addAttribute("message", "ERROR: Owner Email exists for this Application.");

                return "application/OwnerStart";
                // return to  ownerStartPage with error message
            }

            owner.setEmail(newOwnerContactFormDTO.getOwnerEmail());
        }
        if(newOwnerContactFormDTO.getOwnerWebSite() != null){
            owner.setWebSiteURL(newOwnerContactFormDTO.getOwnerWebSite());
        }
        if(newOwnerContactFormDTO.getOwnerPhone() != null){
            owner.setPrimaryPhonenumber(newOwnerContactFormDTO.getOwnerPhone());
        }

        // set new fields that are specific to sole Proprietorship
        // should be 3 fields
        // check for null then add
        if(newOwnerContactFormDTO.getOwnerName() != null){


            if(baseTrademarkApplication.findOwnerByDisplayName(newOwnerContactFormDTO.getOwnerName()) != null){
                model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);
                ArrayList<String> contactNames = new ArrayList<>();
                ArrayList<String> contactEmails = new ArrayList<>();
                ArrayList<String> contactSubTypes = new ArrayList<>();
                ManagedContact managedContact = null;

                for(Iterator<ManagedContact> iter = ptoUser.getMyManagedContacts().iterator(); iter.hasNext(); ) {
                    managedContact = iter.next();
                    contactNames.add(managedContact.getDisplayName());
                    contactEmails.add(managedContact.getEmail());
                    contactSubTypes.add(managedContact.getContactType());

                }
                Collections.reverse(contactNames);
                Collections.reverse(contactEmails);
                Collections.reverse(contactSubTypes);
                ContactsDisplayDTO contactsDisplayDTO = new ContactsDisplayDTO();
                contactsDisplayDTO.setContactNames(contactNames);
                contactsDisplayDTO.setContactEmails(contactEmails);
                contactsDisplayDTO.setContactEntitySubType(contactSubTypes);
                model.addAttribute("myManagedContacts", contactsDisplayDTO);

                // also add error message
                model.addAttribute("message", "ERROR: Owner Name exists for this Application.");

                return "application/OwnerStart";
                // return to  ownerStartPage with error message
            }


            owner.setOwnerName(newOwnerContactFormDTO.getOwnerName());
            owner.setOwnerDisplayname(newOwnerContactFormDTO.getOwnerName());
        }

        if(newOwnerContactFormDTO.getOwnerAdditionalName() !=  null){
            owner.setOwnerAdditionalName(newOwnerContactFormDTO.getOwnerAdditionalName());
        }
        if(newOwnerContactFormDTO.getOwnerOrganizationState() != null){
            owner.setOwnerOrganizationState(newOwnerContactFormDTO.getOwnerOrganizationState());
        }


        // crate partner if partner fields are not null
        List<partnerDTO> partnerDTOS = newOwnerContactFormDTO.getPartnerDTOs();
        if(partnerDTOS.size() > 0){

            //for(Iterator<partnerDTO> iter = partnerDTOS.iterator(); iter.hasNext(); ) {
                //partnerDTO partner = partnerDTOS.iterator().next();
            partnerDTO partner = partnerDTOS.get(0);
                Partner newPartner = new Partner();
                if(partner.getFirstName() != null){
                    newPartner.setPartnerFirstName(partner.getFirstName());

                }
                if(partner.getLastName() != null){
                    newPartner.setPartnerLastName(partner.getLastName());
                }
                if(partner.getMiddleName() != null){
                    newPartner.setPartnerMiddleName(partner.getMiddleName());
                }
                if(partner.getSuffix() != null){
                    newPartner.setPartnerSuffix(partner.getSuffix());
                }
                if(partner.getCitizenShip() != null){
                    newPartner.setPartnerCitizenship(partner.getCitizenShip());
                }
                if(partner.getPartnerName() != null){
                    newPartner.setPartnerName(partner.getPartnerName());
                }
                if(partner.getState() != null){
                    newPartner.setGetOrganizationState(partner.getState());
                }
                if(partner.getType() != null){
                    newPartner.setPartnerEntityType(partner.getType());
                }

                owner.addPartner(newPartner);
           // }

        }
        baseTrademarkApplication.addOwner(owner);
        owner.setTrademarkApplication(baseTrademarkApplication);

        //owner.setClient(p);
       // ptoUser.addOwner(owner);
        //ptoUserService.save(ptoUser);
        baseTradeMarkApplicationService.save(baseTrademarkApplication);



        model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);



        /////////////////////////////////////////////
        // load my contacts list for thyemleaf
        /////////////////////////////////////////////
        ArrayList<String> contactNames = new ArrayList<>();
        ArrayList<String> contactEmails = new ArrayList<>();
        ArrayList<String> contactSubTypes = new ArrayList<>();
        Owner owner1 = null;

        for(Iterator<Owner> iter = ptoUser.getMyOwners().iterator(); iter.hasNext(); ) {
            owner1 = iter.next();
            contactNames.add(owner.getOwnerDisplayname());
            contactEmails.add(owner1.getEmail());
            contactSubTypes.add(owner1.getOwnersubType());

        }
        Collections.reverse(contactNames);
        Collections.reverse(contactEmails);
        Collections.reverse(contactSubTypes);
        ContactsDisplayDTO contactsDisplayDTO = new ContactsDisplayDTO();
        contactsDisplayDTO.setContactNames(contactNames);
        contactsDisplayDTO.setContactEmails(contactEmails);
        contactsDisplayDTO.setContactEntitySubType(contactSubTypes);
        model.addAttribute("myOwnerContacts", contactsDisplayDTO);




        //return "forward:/application/OwnerSetView/?trademarkID="+newOwnerContactFormDTO.getAppInternalID();
        return "application/OwnerSetView";
    }

    @RequestMapping({"/application/owner/us/solp"})
    public String ownerSolPUSInfo(WebRequest request, Model model, @RequestParam("trademarkID") String trademarkInternalID) {

        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(trademarkInternalID);
        model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);

        model.addAttribute("hostBean", hostBean);

        NewOwnerContactFormDTO newOwnerContactFormDTO = new NewOwnerContactFormDTO();
        model.addAttribute("addNewOwnerContactFormDTO", newOwnerContactFormDTO);


        return "application/owner/solP/ownerInfo";
    }

    @RequestMapping({"/application/owner/us/corp"})
    public String ownerCorpUSInfo(WebRequest request, Model model, @RequestParam("trademarkID") String trademarkInternalID) {

        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(trademarkInternalID);
        model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);

        model.addAttribute("hostBean", hostBean);

        NewOwnerContactFormDTO newOwnerContactFormDTO = new NewOwnerContactFormDTO();
        model.addAttribute("addNewOwnerContactFormDTO", newOwnerContactFormDTO);


        return "application/owner/corp/ownerInfo";
    }

    @RequestMapping({"/application/owner/us/llc"})
    public String ownerLLCUSInfo(WebRequest request, Model model, @RequestParam("trademarkID") String trademarkInternalID) {

        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(trademarkInternalID);
        model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);

        model.addAttribute("hostBean", hostBean);

        NewOwnerContactFormDTO newOwnerContactFormDTO = new NewOwnerContactFormDTO();
        model.addAttribute("addNewOwnerContactFormDTO", newOwnerContactFormDTO);


        return "application/owner/llc/ownerInfo";
    }

    @RequestMapping({"/application/owner/us/partnership"})
    public String ownerPartnershipUSInfo(WebRequest request, Model model, @RequestParam("trademarkID") String trademarkInternalID) {

        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(trademarkInternalID);
        model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);

        model.addAttribute("hostBean", hostBean);

        NewOwnerContactFormDTO newOwnerContactFormDTO = new NewOwnerContactFormDTO();
        partnerDTO partner = new partnerDTO();
        newOwnerContactFormDTO.addPartner(partner);






        model.addAttribute("addNewOwnerContactFormDTO", newOwnerContactFormDTO);

        return "application/owner/partnership/ownerInfo";
    }








    /////////////////////////////////////////////////////////////////////////////////////////////////
    // controller for AttorneySet page
    /////////////////////////////////////////////////////////////////////////////////////////////////
    @Transactional
    @RequestMapping({"/application/OwnerSetView","application/OwnerSetView"})
    public String applicationOwnerView
    (WebRequest request, Model model, @RequestParam("trademarkID") String trademarkInternalID) {

        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication  baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(trademarkInternalID);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // add contacts display info to model
        ////////////////////////////////////////////////////////////////////////////////////////////



        /////////////////////////////////////////////////////////////////////////////////////////////
        // create new check
        /////////////////////////////////////////////////////////////////////////////////////////////
        // check if user has LoadContinueUrl set ...
        // in the web url that triggers, you get that from an iteration of
        // myTradeMarks on the dashboard
        /////////////////////////////////////////////////////////////////////////////////////////////





        model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);


        model.addAttribute("hostBean", hostBean);
        return "application/OwnerSetView";

    }






    @Transactional
    @RequestMapping(value = "/application/continueApplication", method = RequestMethod.GET)
    public String continueApplication
            (WebRequest request, Model model, @RequestParam("trademarkID") String trademarkInternalID) {

        String applcationLookupID = trademarkInternalID;
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(trademarkInternalID);
        //////////////////////////////////////////////////////////////////////////
        // retrieve trade mark using internal id
        // add trademark to the model
        // and return the the view that trademark object has saved.
        //////////////////////////////////////////////////////////////////////////

        model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        PTOUserService  ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        //////////////////////////////////////////////////////
        // this is set back to null upon verification check
        //////////////////////////////////////////////////////
        ptoUser.setContinuationURL(trademarkInternalID);
        ptoUserService.save(ptoUser);
        //////////////////////////////////////////////////////
        //continuation = true;

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);


        model.addAttribute("hostBean", hostBean);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // add contacts display info to model
        ////////////////////////////////////////////////////////////////////////////////////////////
        ArrayList<String> contactNames = new ArrayList<>();
        ArrayList<String> contactEmails = new ArrayList<>();
        ArrayList<String> contactFirms = new ArrayList<>();
        Lawyer lawyer = null;

        for(Iterator<Lawyer> iter = ptoUser.getMyLawyers().iterator(); iter.hasNext(); ) {
            lawyer = iter.next();
            contactNames.add(lawyer.getFirstName()+" "+lawyer.getLastName());
            contactEmails.add(lawyer.getEmail());
            contactFirms.add(lawyer.getLawFirmName());
        }
        Collections.reverse(contactNames);
        Collections.reverse(contactEmails);
        Collections.reverse(contactFirms);
        ContactsDisplayDTO contactsDisplayDTO = new ContactsDisplayDTO();
        contactsDisplayDTO.setContactNames(contactNames);
        contactsDisplayDTO.setContactEmails(contactEmails);
        contactsDisplayDTO.setContactFirms(contactFirms);

        model.addAttribute("myContacts", contactsDisplayDTO);


        ArrayList<String> selectedContactNames = new ArrayList<>();
        Lawyer selected_lawyer = null;
        Set<Lawyer> applicationLawyerPool = baseTrademarkApplication.getAvailableLawyers();

        if(applicationLawyerPool != null){
            for(Iterator<Lawyer> iterSelectedContacts = baseTrademarkApplication.getAvailableLawyers().iterator(); iterSelectedContacts.hasNext(); ) {
                selected_lawyer = iterSelectedContacts.next();
                selectedContactNames.add(selected_lawyer.getFirstName()+" "+selected_lawyer.getLastName());
            }
            Collections.reverse(selectedContactNames);
            /////////////////////////////////////////////////////////////////////////////////////////////
            // we need a DTO for passing data to view layer
            /////////////////////////////////////////////////////////////////////////////////////////////
            SelectedContactsDisplayDTO selectedContactsDisplayDTO = new SelectedContactsDisplayDTO();
            selectedContactsDisplayDTO.setSelectedNames(selectedContactNames);
            model.addAttribute("selectedContacts", selectedContactsDisplayDTO);
        }
        else{
            // if there are no one in the application available pool ..
            // simply add empty value.

            SelectedContactsDisplayDTO selectedContactsDisplayDTO = new SelectedContactsDisplayDTO();
            selectedContactNames.add("");
            selectedContactsDisplayDTO.setSelectedNames(selectedContactNames);
            model.addAttribute("selectedContacts", selectedContactsDisplayDTO);
        }

        model.addAttribute("lawyerPool", baseTrademarkApplication.getAvailableLawyers());
        NewOwnerContactFormDTO newOwnerContactFormDTO = new NewOwnerContactFormDTO();
        model.addAttribute("addNewOwnerContactFormDTO", newOwnerContactFormDTO);

        ArrayList<String> managedContactNames = new ArrayList<>();
        ArrayList<String> managedContactEmails = new ArrayList<>();
        ArrayList<String> managedCcontactSubTypes = new ArrayList<>();
        ManagedContact managedContact = null;

        for(Iterator<ManagedContact> iter = ptoUser.getMyManagedContacts().iterator(); iter.hasNext(); ) {
            managedContact = iter.next();
            managedContactNames.add(managedContact.getDisplayName());
            managedContactEmails.add(managedContact.getEmail());
            managedCcontactSubTypes.add(managedContact.getContactType());

        }
        Collections.reverse(managedContactNames);
        Collections.reverse(managedContactEmails);
        Collections.reverse(managedCcontactSubTypes);
        ContactsDisplayDTO managedContactsDisplayDTO = new ContactsDisplayDTO();
        managedContactsDisplayDTO.setContactNames(managedContactNames);
        managedContactsDisplayDTO.setContactEmails(managedContactEmails);
        managedContactsDisplayDTO.setContactEntitySubType(managedCcontactSubTypes);
        model.addAttribute("myManagedContacts", managedContactsDisplayDTO);



        System.out.println("las view model : "+baseTrademarkApplication.getLastViewModel());

        return baseTrademarkApplication.getLastViewModel();

    }




}
