package com.thorton.grant.uspto.prototypewebapp.service.rest;


import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.UserCredentials;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@Service
public class UserAccountService {

    private final ServiceBeanFactory serviceBeanFactory;

    public UserAccountService(ServiceBeanFactory serviceBeanFactory) {
        this.serviceBeanFactory = serviceBeanFactory;
    }










    @RequestMapping(method = RequestMethod.GET, value="/account/user/email/update/{password1}/{password2}")
    @ResponseBody
    String updateUserPassword(@PathVariable String password1, @PathVariable String password2){



        // retrieve current userName from spring security

        // check if current password matches what is stored

        // set status code based on if that matched

        // if matched. update password for credentials object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials userCredentials = userCredentialsService.findByEmail(email);
        boolean pwMatched = bCryptPasswordEncoder.matches(password1, userCredentials.getPassword());
        //String storedPw = authentication.getPrincipal().toString();

        String statusCode = "200";
        String responseMsg;
        if(pwMatched == false){
            statusCode = "420";
            responseMsg = "Entered current password did not match!";
        }
        else {
            // update pass word
            // verify password is not null
            if(password2 != "" || password2 != null){

                // user userCredentials object to save passowrd, then save object to repository via service
                userCredentials.setPassword(bCryptPasswordEncoder.encode(password2));
                userCredentialsService.save(userCredentials);

                responseMsg = "Your new password have been saved";

            }
            else{
                statusCode = "444";
                responseMsg = "New password can not be empty.";
            }
        }

        responseMsg = "{status:" + statusCode +" } { msg:"+responseMsg+" }";
        System.out.println(password1);
        //UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        //userCredentialsService.findByEmail(email);


        return responseMsg;
    }
}
