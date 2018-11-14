package com.thorton.grant.uspto.prototypewebapp.repositories.jpa.security;

import com.thorton.grant.uspto.prototypewebapp.model.entities.security.AuthenticationToken;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.VerificationToken;
import org.springframework.data.repository.CrudRepository;

public interface AuthenticationRepository extends CrudRepository<AuthenticationToken, Long> {

    AuthenticationToken findByToken(String token);
    AuthenticationToken findByUserCredentials(UserCredentials userCredentials);
}
