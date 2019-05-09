package com.thorton.grant.uspto.prototypewebapp.config.bootstrap;

import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserRoleService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.actions.PetitionService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.participants.LawyerService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.OfficeActions;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.Petition;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Owner;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.GoodAndService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.TradeMark;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.ManagedContact;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserRole;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent>         {

    private  final ServiceBeanFactory serviceBeanFactory;

    public DataLoader(ServiceBeanFactory serviceBeanFactory) {
        this.serviceBeanFactory = serviceBeanFactory;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        ////////////////////////////////////////////////////////////////////////////////
        // inject services from factory
        ////////////////////////////////////////////////////////////////////////////////
        PTOUserService myPTOUserService = serviceBeanFactory.getPTOUserService();
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserRoleService userRoleService = serviceBeanFactory.getUserRoleService();
        BaseTradeMarkApplicationService tradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        LawyerService lawyerService = serviceBeanFactory.getLawyerService();

        PetitionService petitionService = serviceBeanFactory.getPetitionService();

        ////////////////////////////////////////////////////////////////////////////////


        PTOUser PTOUser1 = new PTOUser();
        PTOUser1.setFirstName("test");
        PTOUser1.setLastName("user");
        PTOUser1.setAddress("1115 Reserve Champion Drive");
        PTOUser1.setCity("Rockville");
        PTOUser1.setState("MD");
        PTOUser1.setZipcode("20850");
        PTOUser1.setCountry("X1"); // country code for united states
        PTOUser1.setPrimaryPhonenumber("571-839-3730");
        PTOUser1.setProfileComplete(true);
        /////////////////////////////////////////////////////////////////////////////////
        // set username, password, email and role
        /////////////////////////////////////////////////////////////////////////////////
        UserCredentials ownerCreds = new UserCredentials();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        /////////////////////////////////////////////////////////////////////////////////
        // follow the same convention from the save method and save role for test user 1
        /////////////////////////////////////////////////////////////////////////////////

        ownerCreds.setUsername("test.user");
        ownerCreds.setPassword(bCryptPasswordEncoder.encode("xxxxx"));
        ownerCreds.setPasswordConfirm(bCryptPasswordEncoder.encode("xxxxx"));
        ownerCreds.setEmail("lzhang422@gmail.com");
        ownerCreds.setActive(1);

        UserRole userRole = new UserRole();
        userRole.setRoleName("ROLE_ADMIN");
        //myRoleService.save(userRole);
        ownerCreds.setUserRoles(new HashSet<UserRole>(Arrays.asList(userRole)));
        //////////////////////////////////////////////////////////////////////////////////
        // set credentails to active
        //////////////////////////////////////////////////////////////////////////////////
        ownerCreds.setActive(1);
        //////////////////////////////////////////////////////////////////////////////////
        // create bi-directional relationship between credentials and owner
        //////////////////////////////////////////////////////////////////////////////////
        ownerCreds.setUserPersonalData(PTOUser1);
        PTOUser1.setUserCredentials(ownerCreds);
        PTOUser1.setEmail(ownerCreds.getEmail());
        // let us create an application and add it to PTOUser1


        /////////////////////////////////////////////////////////////////////////////
        BaseTrademarkApplication trademarkApplication = new BaseTrademarkApplication();
        trademarkApplication.setPtoUser(PTOUser1);

        //trademarkApplication.setLastViewModel("application/MarkDetailsStart");
        //trademarkApplication.setLastViewModel("application/additional/additionalInfo");
        //trademarkApplication.setLastViewModel("application/mark/MarkDetailsDesignWText");
        //trademarkApplication.setLastViewModel("application/goods_services/GoodsServicesStart");

        trademarkApplication.setLastViewModel("application/success/index");
        //trademarkApplication.setLastViewModel("application/attorney/AttorneyStart");
        trademarkApplication.setAttorneySet(true);
        trademarkApplication.setAttorneyFiling(true);

        trademarkApplication.setValidateTEASFields(true);





        // tradeMark application needs an internal id that tieto the ptoUser ...
        // or examine how is application tied to the ptoUser

        //PTOUser1.addApplication(trademarkApplication);
        //////////////////////////////////////////////////////////////////
        Lawyer newLawyer = new Lawyer();
        newLawyer.setClient(PTOUser1);
        newLawyer.setLawFirmName("Grant Thornton, LLC");
        //newLawyer.setPoolMember(trademarkApplication);
        newLawyer.setBarLicense("DC234567889");
        newLawyer.setDocketNumber("100000000111");
        newLawyer.setBarJurisdiction("Washington DC Bar Association");
        newLawyer.setFirstName("test");
        newLawyer.setLastName("lawyer");
        newLawyer.setEmail("li.zhang@us.gt.com");
        newLawyer.setAddress("333 Carlyle Ave");
        newLawyer.setAddress1("333 Carlyle Ave");
        newLawyer.setCity("Alexandria");
        newLawyer.setState("VA");
        newLawyer.setZipcode("22222");
        newLawyer.setPrimaryPhonenumber("333-333-3333");
        newLawyer.addDocketNumber("12345.6679-QFG");


        PTOUser1.addLawyer(newLawyer);
        trademarkApplication.setPrimaryLawyer(newLawyer);


        // add default managed contact

        ManagedContact managedContact = createCopyPTOUserInfo4ManagedContactAttorney(PTOUser1);


        PTOUser1.addManagedContact(managedContact);


        ///////////////////////////////////////////////////////////////////////////////////////////////
        // we need a copy constructor ...so that trademark Application lawyers are not the same ones
        // saved by PTOUser ...
        // as this will allow PTOUser to delete the application with out deleting his/hers lawyers.
        ///////////////////////////////////////////////////////////////////////////////////////////////

        //trademarkApplication.setOwnerEmail(PTOUser1.getEmail());
        Owner testOwner = new Owner();
        testOwner.setClient(PTOUser1);
        testOwner.setAddress1(PTOUser1.getAddress());
        testOwner.setAddress(PTOUser1.getAddress());
        testOwner.setFirstName(PTOUser1.getFirstName());
        testOwner.setLastName(PTOUser1.getLastName());
        testOwner.setCitizenShip(PTOUser1.getCountry());
        testOwner.setCity(PTOUser1.getCity());
        testOwner.setState(PTOUser1.getState());
        testOwner.setZipcode(PTOUser1.getZipcode());

        testOwner.setOwnerEnityType("US");
        testOwner.setOwnersubType("Individual");
        testOwner.setEmail(PTOUser1.getEmail());

        // GoverningEntity testPartner = new GoverningEntity();
       // testPartner.setPartnerLastName("ike");
         //testPartner.setPartnerFirstName("mike");
        //testPartner.setPartnerCitizenship(PTOUser1.getCountry());
        //testOwner.addPartner(testPartner);

       //OwnerService ownerService = serviceBeanFactory.getOwnerService();

        //ownerService.save(testOwner);



        //Owner owner = new Owner();
        //owner.setOwnerType("individual");
        //owner.setEmail(PTOUser1.getEmail());
        //owner.setAddress(PTOUser1.getAddress());
        //owner.setFirstName(PTOUser1.getFirstName());
        //owner.setLastName(PTOUser1.getLastName());
        // owner.setCity(PTOUser1.getCity());
        //owner.setState(PTOUser1.getState());

        trademarkApplication.addOwner(testOwner);
        trademarkApplication.setPrimaryOwner(testOwner);

        // set up mark for default application
        // 1. attach mark image
        // 2. set mark literals

        TradeMark tradeMark = new TradeMark();
        tradeMark.setTrademarkDesignType("");
        trademarkApplication.setTradeMark(tradeMark);

        trademarkApplication.getTradeMark().setTrademarkImagePath("/files/"+"standardcharacter_alt2.gif");

        trademarkApplication.getTradeMark().setTrademarkBWImagePath("/files/"+"standardcharacter_alt2.gif");
        //trademarkApplication.getTradeMark().setBaseStoragePath("C:\\images\\attorney");
        trademarkApplication.getTradeMark().setBaseStoragePath("/home/zhangl/images/");
        trademarkApplication.getTradeMark().setStandardCharacterMark(false);
        trademarkApplication.getTradeMark().setColorClaimSet(true);
        trademarkApplication.getTradeMark().setMarkColorClaim(true);
        trademarkApplication.getTradeMark().setMarkColorClaimBW(false);
        trademarkApplication.getTradeMark().setMarkColors("black");

        trademarkApplication.getTradeMark().setMarkDescription("captial letters and acroynm.");
        trademarkApplication.getTradeMark().setTrademarkDesignType("Design with text");

        trademarkApplication.getTradeMark().setMarkLiteral("PK Prep");

        // this should be enought for mark standard characters type


        // set up goods and services
        trademarkApplication.setSearchExistingGSdatabase(true);


        // create the good and service
        GoodAndService goodAndService = new GoodAndService();
        goodAndService.setClassNumber("7");
        goodAndService.setClassDescription("Agriculture implements, namely, coulters");
        goodAndService.setInternalID("a0");
        trademarkApplication.addGoodAndService(goodAndService);






        // set up filing basis for default application
        // 1. add one filing basis

        trademarkApplication.setMarkInUseForAllGS(false);
        trademarkApplication.setMarkAllgsSet(true);


        // we need to loop though all gs and set its in use to false
        trademarkApplication.setDeclarationMarkInUse(false);
        trademarkApplication.setDeclarationMarkInUseSet(true);


        trademarkApplication.setMarkHasForeignRegistration(false);
        trademarkApplication.setMarkFappSet(true);


        // set up additional info for default application

        // set up signature and confirm for default appliation

        // set up final status for default application
        trademarkApplication.setFilingStatus("Abandoned");

        // set up office action for default application
        OfficeActions officeActions = new OfficeActions();
        officeActions.setParentMarkImagePath(trademarkApplication.getTradeMark().getTrademarkImagePath());
        officeActions.setParentMarkOwnerName(trademarkApplication.getPrimaryOwner().getOwnerDisplayname());
        officeActions.setParentSerialNumber("3000000");
        Petition petition = new Petition();
        petition.setRecievedOfficeAction(false);
        petition.setRecievedOfficeActionSet(false);
        petitionService.save(petition);

        officeActions.setPetition(petition);
        petition.setOfficeAction(officeActions);



        officeActions.setOfficeActionCode("Abandoned");


        trademarkApplication.addOfficeAction(officeActions);
        officeActions.setTrademarkApplication(trademarkApplication);






        // then on dash board ..for each application ...display each office action that it has











        PTOUser1.addApplication(trademarkApplication);
        myPTOUserService.save(PTOUser1);
        userRoleService.save(userRole);
        userCredentialsService.save(ownerCreds);
        lawyerService.save(newLawyer);
        tradeMarkApplicationService.save(trademarkApplication);
        trademarkApplication.setTrademarkName("3000000");
        trademarkApplication.setApplicationInternalID(trademarkApplication.getTrademarkName()+trademarkApplication.getId());

        tradeMarkApplicationService.save(trademarkApplication);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////







        // create another user

        Set<PTOUser> managedContactsAttorneys = new HashSet<>();
        Set<PTOUser> managedContactsOwners = new HashSet<>();
        PTOUser Jackie = createUser("Jackie", "Babos", "333 Carlyle ave", "Alexandria", "VA", "22222", "United States of America", "333-333-3333", "jackie.babos@us.gt.com","12345",managedContactsAttorneys, managedContactsOwners);

        // GT users

        PTOUser Jacob = createUser("Jacob", "Goldstein", "333 Carlyle ave", "Alexandria", "VA", "22222", "United States of America", "444-444-444", "jacob.goldstein@us.gt.com","12345",managedContactsAttorneys,managedContactsOwners);
        PTOUser Avo = createUser("Avo", "Reed", "333 Carlyle ave", "Alexandria", "VA", "22222", "United States of America", "222-22-2222", "avo.reid@us.gt.com","12345", managedContactsAttorneys, managedContactsOwners);
        PTOUser Li = createUser("Li", "Zhang", "333 Carlyle ave", "Alexandria", "VA", "22222", "United States of America", "555-555-5555", "li.zhang@us.gt.com","12345", managedContactsAttorneys, managedContactsOwners);
        PTOUser lynn = createUser("Lynn", "Istanikmas", "333 Carlyle ave", "Alexandria", "VA", "22222", "United States of America", "555-555-5555", "lynn.stanikmas@us.gt.com","12345", managedContactsAttorneys, managedContactsOwners);



        //USPTO test users


        PTOUser mattKim = createUser("Matt", "Kim", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "555-555-5555", "matthew.kim1@uspto.gov","12345", managedContactsAttorneys, managedContactsOwners);
        PTOUser kerryW = createUser("Kerrie", "Washington", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "555-555-5555", "kerrie.washington@uspto.gov","12345", managedContactsAttorneys, managedContactsOwners);
        PTOUser cameroC = createUser("Cameron", "Carter", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "555-555-5555", "cameron.carter1@uspto.gov","12345", managedContactsAttorneys, managedContactsOwners);
        PTOUser courtneyP = createUser("Courtney", "Postell", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "555-555-5555", "courtney.postell@uspto.gov","12345", managedContactsAttorneys, managedContactsOwners);
        PTOUser crystalH = createUser("Crystal", "Hlywa", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "555-555-5555", "crystal.hlywa@uspto.gov","12345", managedContactsAttorneys, managedContactsOwners);
        PTOUser stacyH = createUser("Stacy", "Hatfield", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "555-555-5555", "stacey.hatfield@uspto.gov","12345", managedContactsAttorneys, managedContactsOwners);
        PTOUser ninoA = createUser("Nino", "Arvanitis", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "555-555-5555", "nino.arvanitis@uspto.gov","12345", managedContactsAttorneys, managedContactsOwners);
        PTOUser TammieD = createUser("Tammie", "Diggs", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "555-555-5555", "tammie.diggs@uspto.gov","12345", managedContactsAttorneys, managedContactsOwners);
        PTOUser stephenA = createUser("Stephen", "Aquila", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "555-555-5555", "stephen.aquila@uspto.gov","12345", managedContactsAttorneys, managedContactsOwners);
        PTOUser nelsonB = createUser("Nelson", "Betancourt", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "555-555-5555", "nelson.betancourt@uspto.gov","12345", managedContactsAttorneys, managedContactsOwners);
        PTOUser TyleA = createUser("Tyle", "Auduong", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "555-555-5555", "tyle.auduong@uspto.gov","12345", managedContactsAttorneys, managedContactsOwners);
        PTOUser GlenB = createUser("Glen", "Brown", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "555-555-5555", "glen.brown@uspto.gov","12345", managedContactsAttorneys, managedContactsOwners);
        PTOUser HangT = createUser("Hang", "Tran", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "555-555-5555", "hang.tran@uspto.gov","12345", managedContactsAttorneys, managedContactsOwners);


        // preloaded accounts special  with additional data
        PTOUser alexB = createUser("Alex", "Blair", "600 Veal Street", "Aspen", "CO", "81611", "United States of America", "555-555-5555", "alex.blair@test.com","P@ssword", managedContactsAttorneys, managedContactsOwners);



        PTOUser bhamaMamas = createUser("Bahama Mama's", "Treats and Sweets", "600 S kingston, st", "Nassau", "", "00000", "Bahamas", "555-555-5555", "bahama.mamas@gmail.com","12345", managedContactsAttorneys, managedContactsOwners);
        managedContactsOwners.add(bhamaMamas);
        PTOUser hansRich = createUser("Hans", "Richardson", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "555-555-5555", "richhans@abc.com","P@ssword", managedContactsAttorneys, managedContactsOwners);


        managedContactsOwners.remove(bhamaMamas);

        managedContactsAttorneys.add(Jackie);
        managedContactsAttorneys.add(Jacob);
        managedContactsAttorneys.add(Avo);



        managedContactsOwners.add(lynn);
        managedContactsOwners.add(Li);



        // BA users

        PTOUser Lynn = createUser("Lynn", "Istanikmas", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "333-333-3333", "lstanikmas@gmail.com","12345",managedContactsAttorneys, managedContactsOwners);
        PTOUser Tina = createUser("Tina", "Donbeck", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "333-333-3333", "tina.donbeck@uspto.gov","12345",managedContactsAttorneys, managedContactsOwners);
        PTOUser Al = createUser("Albert", "Young", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "333-333-3333", "albert.young@uspto.gov","12345",managedContactsAttorneys, managedContactsOwners);
        PTOUser keyte = createUser("Keyte", "Ernst", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "333-333-3333", "keyte.ernst@uspto.gov","12345",managedContactsAttorneys, managedContactsOwners);
        PTOUser shelly = createUser("Shelly", "Matte", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "333-333-3333", "shelly.matte@uspto.gov","12345",managedContactsAttorneys, managedContactsOwners);
        PTOUser Vi = createUser("Tuong-Vi", "Nguyen", "600 Dulany Street", "Alexandria", "VA", "22222", "United States of America", "333-333-3333", "tuong-vi.nguyen@uspto.gov","12345",managedContactsAttorneys, managedContactsOwners);
        PTOUser Serra = createUser("Serra", "Trinh", "333 Carlyle ave", "Alexandria", "VA", "22222", "United States of America", "333-333-3333", "serra.trinh@us.gt.com","12345",managedContactsAttorneys, managedContactsOwners);




    }




    // create helper function add user
    //////////////////////////////////////////////////
    public PTOUser createUser(String firstName, String lastName, String address, String city, String state, String zipcode, String country, String phone, String email, String password, Set<PTOUser> managedContactsAttorneys, Set<PTOUser> managedContactsOwners){


        PTOUser PTOUser1 = new PTOUser();
        PTOUser1.setFirstName(firstName);
        PTOUser1.setLastName(lastName);
        PTOUser1.setAddress(address);
        PTOUser1.setCity(city);
        PTOUser1.setState(state);
        PTOUser1.setZipcode(zipcode);
        PTOUser1.setCountry(country); // country code for united states
        PTOUser1.setPrimaryPhonenumber(phone);
        PTOUser1.setProfileComplete(true);
        /////////////////////////////////////////////////////////////////////////////////
        // set username, password, email and role
        /////////////////////////////////////////////////////////////////////////////////
        UserCredentials ownerCreds = new UserCredentials();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        /////////////////////////////////////////////////////////////////////////////////
        // follow the same convention from the save method and save role for test user 1
        /////////////////////////////////////////////////////////////////////////////////

        ownerCreds.setUsername(firstName+"."+lastName);
        ownerCreds.setPassword(bCryptPasswordEncoder.encode(password));
        ownerCreds.setPasswordConfirm(bCryptPasswordEncoder.encode(password));
        ownerCreds.setEmail(email);
        ownerCreds.setActive(1);

        UserRole userRole = new UserRole();
        userRole.setRoleName("ROLE_USER");
        //myRoleService.save(userRole);
        ownerCreds.setUserRoles(new HashSet<UserRole>(Arrays.asList(userRole)));
        //////////////////////////////////////////////////////////////////////////////////
        // set credentails to active
        //////////////////////////////////////////////////////////////////////////////////
        ownerCreds.setActive(1);
        //////////////////////////////////////////////////////////////////////////////////
        // create bi-directional relationship between credentials and owner
        //////////////////////////////////////////////////////////////////////////////////
        ownerCreds.setUserPersonalData(PTOUser1);
        PTOUser1.setUserCredentials(ownerCreds);
        PTOUser1.setEmail(ownerCreds.getEmail());

        ManagedContact contact = createCopyPTOUserInfo4ManagedOwner(PTOUser1);
        PTOUser1.addManagedContact(contact);

        for(Iterator<PTOUser> iter = managedContactsOwners.iterator(); iter.hasNext(); ) {
            //this.availableLawyers.add(new Lawyer( iter.next() ));

            PTOUser current = iter.next();
            PTOUser1.addManagedContact(createCopyPTOUserInfo4ManagedOwner(current));

        }


        for(Iterator<PTOUser> iter = managedContactsAttorneys.iterator(); iter.hasNext(); ) {
            //this.availableLawyers.add(new Lawyer( iter.next() ));

            PTOUser current = iter.next();
            PTOUser1.addManagedContact(createCopyPTOUserInfo4ManagedContactAttorney(current));

        }



        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        ptoUserService.save(PTOUser1);
        UserRoleService  userRoleService = serviceBeanFactory.getUserRoleService();
        userRoleService.save(userRole);

        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        userCredentialsService.save(ownerCreds);

        return PTOUser1;

    }
    //////////////////////////////////////////////////////////////////////////////////



     //////////////////////////////////////////////////////////////////////////////////


     //////////////////////////////////////////////////////////////////////////////////
     // deep copy of field value to managedContact object
     //////////////////////////////////////////////////////////////////////////////////
     private ManagedContact createCopyPTOUserInfo4ManagedContactAttorney(PTOUser ptoUser){

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
         contact.setContactType("attorney");




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
    private ManagedContact createCopyPTOUserInfo4ManagedOwner(PTOUser ptoUser){

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
        contact.setContactType("owner");




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
