package com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity;

import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.RegistrationDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.VerificationToken;

public interface IUserService {
    UserCredentials registerNewUserAccount( RegistrationDTO accountDto);
    void createVerificationToken(UserCredentials userCredentials, String token);
    UserCredentials getUser(String verificationToken);

    void saveRegisteredUser(UserCredentials userCredentials);

    VerificationToken getVerificationToken(String VerificationToken);

}