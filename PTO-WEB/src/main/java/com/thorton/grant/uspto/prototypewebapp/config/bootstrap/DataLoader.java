package com.thorton.grant.uspto.prototypewebapp.config.bootstrap;

import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserRoleService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.participants.LawyerService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.participants.OwnerService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.asset.TradeMarkService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Owner;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Partner;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
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
        ownerCreds.setEmail("lzhang421@gmail.com");
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

       // trademarkApplication.setLastViewModel("application/owner/ownerEntity");
        trademarkApplication.setLastViewModel("application/OwnerStart");
        //trademarkApplication.setLastViewModel("application/AttorneyStart");
        trademarkApplication.setAttorneySet(true);
        trademarkApplication.setAttorneyFiling(true);


        // tradeMark application needs an internal id that ties to the ptoUser ...
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


        PTOUser1.addLawyer(newLawyer);


        ///////////////////////////////////////////////////////////////////////////////////////////////
        // we need a copy constructor ...so that trademark Application lawyers are not the same ones
        // saved by PTOUser ...
        // as this will allow PTOUser to delete the application with out deleting his/hers lawyers.
        ///////////////////////////////////////////////////////////////////////////////////////////////

        trademarkApplication.setOwnerEmail(PTOUser1.getEmail());
        Owner testOwner = new Owner();
        testOwner.setClient(PTOUser1);
        testOwner.setAddress1(PTOUser1.getAddress());
        testOwner.setAddress(PTOUser1.getAddress());
        testOwner.setFirstName(PTOUser1.getFirstName());
        testOwner.setLastName(PTOUser1.getLastName());
        testOwner.setCitizenShip(PTOUser1.getCountry());
        testOwner.setOwnerEnityType("US");
        testOwner.setOwnersubType("Individual");
        Partner testPartner = new Partner();
        testPartner.setPartnerLastName("ike");
        testPartner.setPartnerFirstName("mike");
        testPartner.setPartnerCitizenship(PTOUser1.getCountry());
        testOwner.addPartner(testPartner);

        OwnerService ownerService = serviceBeanFactory.getOwnerService();

        ownerService.save(testOwner);



        //Owner owner = new Owner();
        //owner.setOwnerType("individual");
        //owner.setEmail(PTOUser1.getEmail());
        //owner.setAddress(PTOUser1.getAddress());
        //owner.setFirstName(PTOUser1.getFirstName());
        //owner.setLastName(PTOUser1.getLastName());
        // owner.setCity(PTOUser1.getCity());
        //owner.setState(PTOUser1.getState());

        //trademarkApplication.setOwner(owner);
        /////////////////////////////////////////////////////////////////////////////////
        // add a method to PTOUser to just add one application
        /////////////////////////////////////////////////////////////////////////////////


        PTOUser1.addApplication(trademarkApplication);
        myPTOUserService.save(PTOUser1);
        userRoleService.save(userRole);
        userCredentialsService.save(ownerCreds);
        lawyerService.save(newLawyer);
        tradeMarkApplicationService.save(trademarkApplication);
        trademarkApplication.setTrademarkName("3000000");
        trademarkApplication.setApplicationInternalID(trademarkApplication.getTrademarkName()+trademarkApplication.getId());

        tradeMarkApplicationService.save(trademarkApplication);


        // create another user

        createUser("Jackie", "Babos", "333 Carlyle ave", "Alexendria", "Virginia", "22222", "X1", "333-333-3333", "Jackie.Babos@us.gt.com","12345");
        createUser("Jacob", "Goldstein", "333 Carlyle ave", "Alexendria", "Virginia", "22222", "X1", "333-333-3333", "Jacob.Goldstein@us.gt.com","12345");


        createUser("Lynn", "Stanikmas", "333 Carlyle ave", "Alexendria", "Virginia", "22222", "X1", "333-333-3333", "lstanikams@gmail.com","12345");

        createUser("Avo", "Reed", "333 Carlyle ave", "Alexendria", "Virginia", "22222", "X1", "333-333-3333", "Avo.Reid@us.gt.com","12345");

        createUser("Tina", "Donbeck", "333 Carlyle ave", "Alexendria", "Virginia", "22222", "X1", "333-333-3333", "tina.donbeck@uspto.gov","12345");


    }

    //////////////////////////////////////////////////
    // create helper function add user
    //////////////////////////////////////////////////
     public void createUser(String firstName, String lastName, String address, String city, String state, String zipcode, String country, String phone, String email, String password){


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

         PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
         ptoUserService.save(PTOUser1);
         UserRoleService  userRoleService = serviceBeanFactory.getUserRoleService();
         userRoleService.save(userRole);

         UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
         userCredentialsService.save(ownerCreds);

     }



}
