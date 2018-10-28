package com.thorton.grant.uspto.prototypewebapp.factories;

import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserRoleService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.registration.VerificationTokenService;
import org.springframework.stereotype.Component;

@Component
public class ServiceBeanFactory {

    private final PTOUserService PTOUserService;
    private final UserCredentialsService userCredentialsService;
    private final UserRoleService userRoleService;
    private final VerificationTokenService verificationTokenService;


    public ServiceBeanFactory(com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService PTOUserService, UserCredentialsService userCredentialsService, UserRoleService userRoleService, VerificationTokenService verificationTokenService) {
        this.PTOUserService = PTOUserService;
        this.userCredentialsService = userCredentialsService;
        this.userRoleService = userRoleService;
        this.verificationTokenService = verificationTokenService;
    }

    public com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService getPTOUserService() {
        return PTOUserService;
    }

    public UserCredentialsService getUserCredentialsService() {
        return userCredentialsService;
    }

    public UserRoleService getUserRoleService() {
        return userRoleService;
    }

    public VerificationTokenService getVerificationTokenService() {
        return verificationTokenService;
    }
}
