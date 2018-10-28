package com.thorton.grant.uspto.prototypewebapp.service.registratrion;

import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.IUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserRoleService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.RegistrationDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserRole;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.VerificationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;


@Service
public class UserRegistrationService implements IUserService {


    private final PTOUserService ptoUserService;
    private final UserCredentialsService userCredentialsService;
    private final UserRoleService userRoleService;
    private final  BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserRegistrationService(PTOUserService ptoUserService, UserCredentialsService userCredentialsService, UserRoleService userRoleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.ptoUserService = ptoUserService;
        this.userCredentialsService = userCredentialsService;
        this.userRoleService = userRoleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    @Override
    public UserCredentials registerNewUserAccount(RegistrationDTO accountDto)  {

        if(emailExist(accountDto.getEmail())){
            return null;
        } // check of existing account, return null if account exists


        UserCredentials newUserCredentials = new UserCredentials();
        PTOUser newUser = new PTOUser();
        UserRole defaultRole = new UserRole();
        defaultRole.setRoleName("ROLE_FILER");
        userRoleService.save(defaultRole);

        newUser.setFirstName(accountDto.getFirstName());
        newUser.setLastName(accountDto.getLastName());
        newUser.setEmail(accountDto.getEmail());
        ptoUserService.save(newUser);

        newUserCredentials.setEmail(accountDto.getEmail());
       // newUserCredentials.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
        //newUserCredentials.setPasswordConfirm(bCryptPasswordEncoder.encode(accountDto.getMatchingPassword()));
        newUserCredentials.setUserRoles(new HashSet<UserRole>(Arrays.asList(defaultRole)));
        newUserCredentials.setUserPersonalData(newUser);
        ////newUserCredentials.setActive(1);

        userCredentialsService.save(newUserCredentials);




        return newUserCredentials;
    }

    private boolean emailExist(String email) {
        UserCredentials user = userCredentialsService.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }


    @Override
    public void createVerificationToken(UserCredentials userCredentials, String token) {

        System.out.println("creating token for "+token);

    }

    @Override
    public UserCredentials getUser(String verificationToken) {
        return null;
    }

    @Override
    public void saveRegisteredUser(UserCredentials userCredentials) {

    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return null;
    }
}
