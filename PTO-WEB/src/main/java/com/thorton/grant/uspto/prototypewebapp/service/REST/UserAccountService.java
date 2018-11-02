package com.thorton.grant.uspto.prototypewebapp.service.REST;


import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.IUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.RegistrationDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Service
public class UserAccountService {

    //@Autowired
    //private IUserService service;

    private final ServiceBeanFactory serviceBeanFactory;

    public UserAccountService(ServiceBeanFactory serviceBeanFactory) {
        this.serviceBeanFactory = serviceBeanFactory;
    }




    // will add request headers for security in the next update
    @RequestMapping(value = "/rest/api/test", headers = "key=val", method = GET)
    @ResponseBody
    public String restTest() {
        return "Get some Foos with Header";
    }





    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/user/update/pw/{password1}/{password2}")
    @ResponseBody
    ResponseEntity<String> updateUserPassword(@PathVariable String password1, @PathVariable String password2){




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

        // retrieve current userName from spring security

        // check if current password matches what is stored

        // set status code based on if that matched

        // if matched. update password for credentials object

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();

        UserCredentials userCredentials = userCredentialsService.findByEmail(email);




        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean pwMatched = bCryptPasswordEncoder.matches(password1, userCredentials.getPassword());

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
        HttpHeaders responseHeader = new HttpHeaders ();
        //responseHeader.set("Access-Control-Allow-Origin", "http://18.221.138.198:8080");
        responseHeader.setAccessControlAllowOrigin("http://efile-reimagined.com");
        ArrayList<String> headersAllowed = new ArrayList<String>();
        headersAllowed.add("Access-Control-Allow-Origin");
        responseHeader.setAccessControlAllowHeaders(headersAllowed);
        ArrayList<String> methAllowed = new ArrayList<String>();

        System.out.println("response header : "+responseHeader.getAccessControlAllowOrigin());


       return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;

    }



    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/user/update/{userAccountField}/{param}")
    @ResponseBody
    ResponseEntity<String> updateUserAccountInfo(@PathVariable String userAccountField , @PathVariable String param){




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

        // retrieve current userName from spring security

        // check if current password matches what is stored

        // set status code based on if that matched

        // if matched. update password for credentials object

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();



        UserCredentials userCredentials = userCredentialsService.findByEmail(email);
        PTOUser ptoUser = ptoUserService.findByEmail(email);

        if(userAccountField.equals("State")){
              ptoUser.setState(param); // sets new state code
        }

        // check if all required fields are set
        boolean profileSet = true;
        if(ptoUser.getAddress() == null || ptoUser.getAddress().equals("")){
            profileSet = false;
        }
        System.out.println(profileSet);
        if(ptoUser.getCity() == null || ptoUser.getCity().equals("")){
            profileSet = false;
        }
        System.out.println(profileSet);

        if(ptoUser.getState() == null || ptoUser.getState().equals("")){
            profileSet = false;
        }
        System.out.println(profileSet);

        if(ptoUser.getZipcode() == null || ptoUser.getZipcode().equals("")){
            profileSet = false;
        }
        System.out.println(profileSet);

        if(ptoUser.getPrimaryPhonenumber() == null || ptoUser.getPrimaryPhonenumber().equals("")){
            profileSet = false;
        }
        System.out.println(profileSet);

        ptoUser.setProfileComplete(profileSet);



        ptoUserService.save(ptoUser);




        String statusCode = "200";
        String responseMsg = userAccountField+" has been saved.";
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



    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/user/complete/")
    @ResponseBody
    ResponseEntity<String> checkProfileComplete(){
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

        // retrieve current userName from spring security

        // check if current password matches what is stored

        // set status code based on if that matched

        // if matched. update password for credentials object

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();




        PTOUser ptoUser = ptoUserService.findByEmail(email);

       if(ptoUser.isProfileComplete() == false){

           String statusCode = "444";
           String responseMsg = "User Profile required information is not complete.";
           responseMsg = "{status:" + statusCode +" } { msg:"+responseMsg+" }";
           HttpHeaders responseHeader = new HttpHeaders ();
           //responseHeader.set("Access-Control-Allow-Origin", "http://18.221.138.198:8080");
           responseHeader.setAccessControlAllowOrigin("http://efile-reimagined.com");
           ArrayList<String> headersAllowed = new ArrayList<String>();
           headersAllowed.add("Access-Control-Allow-Origin");
           responseHeader.setAccessControlAllowHeaders(headersAllowed);
           ArrayList<String> methAllowed = new ArrayList<String>();

           System.out.println("response header : "+responseHeader.getAccessControlAllowOrigin());


           return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;

       }



        String statusCode = "200";
        String responseMsg = "User Profile required information is  complete.";
        responseMsg = "{status:" + statusCode +" } { msg:"+responseMsg+" }";
        HttpHeaders responseHeader = new HttpHeaders ();
        //responseHeader.set("Access-Control-Allow-Origin", "http://18.221.138.198:8080");
        responseHeader.setAccessControlAllowOrigin("http://efile-reimagined.com");
        ArrayList<String> headersAllowed = new ArrayList<String>();
        headersAllowed.add("Access-Control-Allow-Origin");
        responseHeader.setAccessControlAllowHeaders(headersAllowed);
        ArrayList<String> methAllowed = new ArrayList<String>();

        System.out.println("response header : "+responseHeader.getAccessControlAllowOrigin());


        return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;

    }


    @WebFilter("/REST/apiGateway*")
    public class AddResponseHeaderFilter implements Filter {

        @Override
        public void doFilter(ServletRequest request, ServletResponse response,
                             FilterChain chain) throws IOException, ServletException {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setHeader(
                    "Access-Control-Allow-Origin", "http://efile-reimagined.com");
            chain.doFilter(request, response);
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            // ...
        }

        @Override
        public void destroy() {
            // ...
        }
    }


}
