package com.thorton.grant.uspto.prototypewebapp.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;



@Entity
public class VerificationToken {

    private static final int EXPIRATION = 60*24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



    private String token;



    // account associated with this token
    @OneToOne(targetEntity = UserCredentials.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name="user_credentials_id")
    private UserCredentials newCredential;


    // calculate expiration date

    private Date calculateExpDate(int expTimeInMinutes){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, expTimeInMinutes);
        return new Date(calendar.getTime().getTime());

    }

}
