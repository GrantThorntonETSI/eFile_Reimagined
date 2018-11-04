package com.thorton.grant.uspto.prototypewebapp.config.bootstrap;

import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserRoleService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.participants.LawyerService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.asset.TradeMarkService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Owner;
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
        trademarkApplication.setLastViewModel("application/OwnerStart");



        // tradeMark application needs an internal id that ties to the ptoUser ...
        // or examine how is application tied to the ptoUser

        PTOUser1.addApplication(trademarkApplication);
        //////////////////////////////////////////////////////////////////
        Lawyer newLawyer = new Lawyer();
        newLawyer.setClient(PTOUser1);
        newLawyer.setLawFirmName("Grant Thornton, LLC");
        newLawyer.setPoolMember(trademarkApplication);
        newLawyer.setBarLicense("DC234567889");
        newLawyer.setBarJurisdiction("DC");
        newLawyer.setFirstName("test");
        newLawyer.setLastName("lawyer");
        newLawyer.setEmail("li.zhang@us.gt.com");
        PTOUser1.addLawyer(newLawyer);
        trademarkApplication.setAvailableLawyers(PTOUser1.getMyLawyers());
        trademarkApplication.setPrimaryLawyer(PTOUser1.getMyLawyers().iterator().next());
        trademarkApplication.setOwnerEmail(PTOUser1.getEmail());

        Owner owner = new Owner();
        owner.setOwnerType("individual");
        owner.setEmail(PTOUser1.getEmail());
        owner.setAddress(PTOUser1.getAddress());
        owner.setFirstName(PTOUser1.getFirstName());
        owner.setLastName(PTOUser1.getLastName());
        owner.setCity(PTOUser1.getCity());
        owner.setState(PTOUser1.getState());

        trademarkApplication.setOwner(owner);
        /////////////////////////////////////////////////////////////////////////////////
        // add a method to PTOUser to just add one application
        /////////////////////////////////////////////////////////////////////////////////

        myPTOUserService.save(PTOUser1);
        userRoleService.save(userRole);
        userCredentialsService.save(ownerCreds);
        lawyerService.save(newLawyer);
        // userRoleService.save(userRole);
        tradeMarkApplicationService.save(trademarkApplication);
        trademarkApplication.setTrademarkName("my_first_trademark");
        trademarkApplication.setApplicationInternalID(trademarkApplication.getTrademarkName());
        tradeMarkApplicationService.save(trademarkApplication);


        // now add objects to repository, owner and user

        /*

                Privilege readPrivilege
                        = createPrivilegeIfNotFound("READ_PRIVILEGE");
                Privilege writePrivilege
                        = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

                HashSet<Privilege>   adminPrivileges= new HashSet<Privilege>(Arrays.asList(
                        readPrivilege, writePrivilege));
                createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
                createRoleIfNotFound("ROLE_USER", new HashSet<Privilege>(Arrays.asList(readPrivilege)));

                Role adminRole = myRoleService.findByRole("ROLE_ADMIN");
                User user = new User();
                //user.setFirstName("Test");
                //user.setLastName("Test");
                user.setUsername("test_user");
                user.setPassword(bCryptPasswordEncoder.encode("test123"));
                user.setEmail("test@test.com");
                user.setRoles( new HashSet<Role>(Arrays.asList(adminRole)));
                user.setActive(1);
                myUsersService.save(user);



        */
System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        Lawyer myLawerTEST = trademarkApplication.getPrimaryLawyer();
        System.out.println("trademark application: "+trademarkApplication.getApplicationInternalID()+  " represented by law firm :"+myLawerTEST.getLawFirmName());


    }
}
