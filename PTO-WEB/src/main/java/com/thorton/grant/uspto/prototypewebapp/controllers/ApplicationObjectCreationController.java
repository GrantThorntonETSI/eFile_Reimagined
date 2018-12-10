package com.thorton.grant.uspto.prototypewebapp.controllers;


import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.application.ContactsDisplayDTO;
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
import com.thorton.grant.uspto.prototypewebapp.service.storage.StorageService;
import com.thorton.grant.uspto.prototypewebapp.service.storage.error.StorageException;
import org.springframework.context.ApplicationContext;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;



@Controller
public class ApplicationObjectCreationController {


    private final ServiceBeanFactory serviceBeanFactory;
    private static long counter = 3000000;

    private final StorageService storageService;



    /////////////////////////////////////////////////////////////////////////////////////////
    // based on the profile  ...we should be able
    // to inject the correct bean mapped to the correct host file here
    ////////////////////////////////////////////////////////////////////////////////////////
    private final HostBean hostBean;


    private final ApplicationContext appContext;

    public ApplicationObjectCreationController(ServiceBeanFactory serviceBeanFactory, ApplicationContext appContext, StorageService storageService) {
        this.serviceBeanFactory = serviceBeanFactory;
        this.appContext = appContext;

        this.hostBean = (HostBean) appContext.getBean(HostBean.class);
        this.storageService = storageService;

    }




    // hopefully just a redirect here, we won't need to add the applicaiton and credentials to the model
    @PostMapping(value = "/attorney/add")
    public String addAttorneyContact( @ModelAttribute("addNewAttorneyContactFormDTO") @Valid NewAttorneyContactFormDTO newAttorneyContactFormDTO,
                                      @RequestParam(name="file", required=false) MultipartFile file,
                                      Model model,
                                      WebRequest request,
                                      BindingResult result,
                                      Errors errors) {


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

            lawyer.setEmail(newAttorneyContactFormDTO.getAttorneyEmail());

        }
        //////////////////////////////////////////////////////////////////////////////////////
        // add attorney bar information here ...
        //////////////////////////////////////////////////////////////////////////////////////

        if(file != null){

              if(file.isEmpty() == false) {
                  lawyer.setBarCertificateImageKey("/files/"+storageService.getCounter()+file.getOriginalFilename());
                  try {
                      storageService.store(file);

                  }
                  catch ( StorageException ex){
                      model.addAttribute("message", "ERROR: Attorney Credentials upload failed due to error: "+ex );
                      return "forward:/application/start/?trademarkID="+trademarkInternalID;

                  }
              }

        }

        if(newAttorneyContactFormDTO.getAttorneyPhone() != null){
            lawyer.setPrimaryPhonenumber(newAttorneyContactFormDTO.getAttorneyPhone());
        }
        if(newAttorneyContactFormDTO.getAttorneyDocketNumber()!= null){
            lawyer.setDocketNumber(newAttorneyContactFormDTO.getAttorneyDocketNumber());
        }
        if(newAttorneyContactFormDTO.getAttorneyAffiliation()!= null){
            lawyer.setAffiliationStatus(newAttorneyContactFormDTO.getAttorneyAffiliation());
        }
        if(newAttorneyContactFormDTO.getAttorneyBarJurisdiction()!= null){
            lawyer.setBarJurisdiction(newAttorneyContactFormDTO.getAttorneyBarJurisdiction());
        }

        if(newAttorneyContactFormDTO.getAttorneyBarMembershipNumber()!= null){
            lawyer.setMembershipNumber(newAttorneyContactFormDTO.getAttorneyBarMembershipNumber());
        }

        if(newAttorneyContactFormDTO.getAttorneyBarAdmissionDate()!= null){

            String string = newAttorneyContactFormDTO.getAttorneyBarAdmissionDate();

            System.out.println("Date value : "+string);
            try {
                DateFormat format = new SimpleDateFormat("yyyy-dd-mm", Locale.ENGLISH);
                Date date = format.parse(string);

                lawyer.setBarAdmissionDate(date);
            }
            catch(Exception ex){

                model.addAttribute("message", "ERROR: Could not save Bar Admission Date, invalid Date format.");
            }
        }

        if(newAttorneyContactFormDTO.getAttorneyCAagentName()!= null){
            lawyer.setCanadianAgentName(newAttorneyContactFormDTO.getAttorneyCAagentName());
        }
        if(newAttorneyContactFormDTO.applicantCA != null){
            if(newAttorneyContactFormDTO.getApplicantCA() == "true") {
                lawyer.setApplicantCA(true);
            }
            else {
                lawyer.setApplicantCA(false);
            }
        }



        // check if managed contact should be added to PTOUser
        if(ptoUser.findManagedContactByDisplayName(lawyer.getFirstName()+" "+lawyer.getLastName()) == null && ptoUser.findManagedContactByEmail(lawyer.getEmail()) == null){
            ManagedContact newContact = createCopyAttorneyInfo4ManagedContact(lawyer);
            ptoUser.addManagedContact(newContact);
            ptoUserService.save(ptoUser);

        }

        baseTrademarkApplication.addAvailableLawyer(lawyer);
        baseTradeMarkApplicationService.save(baseTrademarkApplication);

        return "forward:/application/AttorneySet/?trademarkID="+trademarkInternalID;
    }
    ///////////////////////////////////////////////////////////////////////////////
     // end of attorney add
    ///////////////////////////////////////////////////////////////////////////////



    // hopefully just a redirect here, we won't need to add the applicaiton and credentials to the model
    @RequestMapping(value = "/owner/add", method = RequestMethod.POST)
    public String addOwnerContact( Model model,
                                   @ModelAttribute("addNewOwnerContactFormDTO") @Valid NewOwnerContactFormDTO newOwnerContactFormDTO,
                                   WebRequest request,
                                   BindingResult result,
                                   Errors errors) {


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


        // check if managed contact should be added to PTOUser
        if(ptoUser.findManagedContactByDisplayName(owner.getOwnerDisplayname()) == null && ptoUser.findManagedContactByEmail(owner.getEmail()) == null){
            ManagedContact newContact = createCopyOwnerInfo4ManagedContact(owner);
            ptoUser.addManagedContact(newContact);
            ptoUserService.save(ptoUser);

        }


        return "forward:/application/OwnerSetView/?trademarkID="+newOwnerContactFormDTO.getAppInternalID();
        //return "application/OwnerSetView";
    }
    ///////////////////////////////////////////////////////////////////////////////
    // end of owner add
    ///////////////////////////////////////////////////////////////////////////////








    //////////////////////////////////////////////////////////////////////////////////
    // deep copy of field value to managedContact object
    //////////////////////////////////////////////////////////////////////////////////
    private ManagedContact createCopyAttorneyInfo4ManagedContact(Lawyer ptoUser){

        ManagedContact contact = new ManagedContact();
        /////////////////////////////////////////////////////////////////
        // copy over contact's lawyer's personal info
        /////////////////////////////////////////////////////////////////
        contact.setFirstName(ptoUser.getFirstName());
        contact.setLastName(ptoUser.getLastName());
        contact.setMidlleName(ptoUser.getMidlleName());
        contact.setCountry(ptoUser.getCountry());
        contact.setAddress(ptoUser.getAddress());
        contact.setDisplayName(ptoUser.getFirstName()+ " "+ptoUser.getLastName());
        contact.setContactType("Individual");

        contact.setCity(ptoUser.getCity());
        contact.setState(ptoUser.getState());
        contact.setZipcode(ptoUser.getZipcode());
        contact.setPrimaryPhonenumber(ptoUser.getPrimaryPhonenumber());
        contact.setEmail(ptoUser.getEmail());
        //////////////////////////////////////////////////////////////////
        // copy over contact's professional info
        //////////////////////////////////////////////////////////////////

        return contact;
    }

    //////////////////////////////////////////////////////////////////////////////////
    // deep copy of field value to managedContact object
    //////////////////////////////////////////////////////////////////////////////////
    private ManagedContact createCopyOwnerInfo4ManagedContact(Owner ptoUser){

        ManagedContact contact = new ManagedContact();
        /////////////////////////////////////////////////////////////////
        // copy over contact's lawyer's personal info
        /////////////////////////////////////////////////////////////////
        contact.setFirstName(ptoUser.getFirstName());
        contact.setLastName(ptoUser.getLastName());
        contact.setMidlleName(ptoUser.getMidlleName());
        contact.setCountry(ptoUser.getCountry());
        contact.setAddress(ptoUser.getAddress());
        contact.setEntityName(ptoUser.getOwnerName());
        if(contact.getFirstName() != null) {
            contact.setDisplayName(ptoUser.getFirstName() + " " + ptoUser.getLastName());
            contact.setContactType("Individual");
        }
        else{
            contact.setDisplayName(ptoUser.getOwnerName());
            contact.setContactType("Entity");
        }

        contact.setCity(ptoUser.getCity());
        contact.setState(ptoUser.getState());
        contact.setZipcode(ptoUser.getZipcode());
        contact.setPrimaryPhonenumber(ptoUser.getPrimaryPhonenumber());
        contact.setEmail(ptoUser.getEmail());
        //////////////////////////////////////////////////////////////////
        // copy over contact's professional info
        //////////////////////////////////////////////////////////////////

        return contact;
    }




}
