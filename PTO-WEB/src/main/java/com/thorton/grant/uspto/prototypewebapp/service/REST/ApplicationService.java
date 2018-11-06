package com.thorton.grant.uspto.prototypewebapp.service.REST;


import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Service
public class ApplicationService {

    private final ServiceBeanFactory serviceBeanFactory;

    public ApplicationService(ServiceBeanFactory serviceBeanFactory) {
        this.serviceBeanFactory = serviceBeanFactory;
    }

    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/update/{applicationField}/{param}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationFields(@PathVariable String applicationField , @PathVariable String param, @PathVariable String appInternalID){




        // verify token before preceding
/*
        VerificationToken verificationToken = service.getVerificationToken(token);
        if (verificationToken == null) {
            String message = "INVALID ACCESS TOKEN.";
            HttpHeaders responseHeader = new HttpHeaders ();
            ArrayList<String> headersAllowed = new ArrayList<String>();
            responseHeader.setAccessControlAllowHeaders(headersAllowed);
            return ResponseEntity.badRequest().headers(responseHeader).body(message) ;
        }


        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiredTime().getTime() - cal.getTime().getTime()) <= 0) {
            String message = "EXPIRED ACCESS TOKEN.";
            HttpHeaders responseHeader = new HttpHeaders ();
            ArrayList<String> headersAllowed = new ArrayList<String>();
            responseHeader.setAccessControlAllowHeaders(headersAllowed);
            return ResponseEntity.badRequest().headers(responseHeader).body(message) ;
        }


        UserCredentials userCredentials = verificationToken.getNewCredential();
*/


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        //UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        //UserCredentials userCredentials = userCredentialsService.findByEmail(email);
        PTOUser ptoUser = ptoUserService.findByEmail(email);// ?? we may not need to save this
        // verify authentication is valid before moving on ....
        // have to have a valid session


        // retrieve application using passed internal id





        if(applicationField.equals("owner-state")){
           // ptoUser.setState(param); // sets state code
        }

        if(applicationField.equals("owner-city")){
            //ptoUser.setCity(param);
        }

        if(applicationField.equals("owner-zipcode")){
            //ptoUser.setZipcode(param);
        }

        if(applicationField.equals("owner-address")){
            //ptoUser.setAddress(param);
        }

        if(applicationField.equals("owner-country")){
            //ptoUser.setCountry(param); // sets country code
        }

        if(applicationField.equals("own-Phone")){
            //ptoUser.setPrimaryPhonenumber(param);
        }



        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////
        String statusCode = "200";
        String responseMsg = applicationField+" has been saved.";
        responseMsg = "{status:" + statusCode +" } { msg:"+responseMsg+" }";
        HttpHeaders responseHeader = new HttpHeaders ();
        responseHeader.setAccessControlAllowOrigin("http://efile-reimagined.com");
        ArrayList<String> headersAllowed = new ArrayList<String>();
        headersAllowed.add("Access-Control-Allow-Origin");
        responseHeader.setAccessControlAllowHeaders(headersAllowed);
        ArrayList<String> methAllowed = new ArrayList<String>();

        System.out.println("response header : "+responseHeader.getAccessControlAllowOrigin());


        return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;

    }
}
