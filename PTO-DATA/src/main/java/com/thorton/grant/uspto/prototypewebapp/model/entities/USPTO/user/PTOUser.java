package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user;


import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.TradeMark;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PTOUsers")
public class PTOUser extends UserPersonalData {


    @OneToMany(mappedBy = "trademarkOwner")
    private Set<TradeMark> userTrademarks;



    /////////////////////////////////////////////////////////
    // owning object i.e  ptoUser owns trademark applications
    /////////////////////////////////////////////////////////
    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "ptoUser")
    private Set<BaseTrademarkApplication> myApplications;



    @Column(name =  "profile_complete" )
    private boolean profileComplete = false;

    public Set<TradeMark> getUserTrademarks() {
        return userTrademarks;
    }


    @OneToOne(cascade = CascadeType.ALL)
    private UserCredentials userCredentials ;


    public boolean isProfileComplete() {
        return profileComplete;
    }

    public void setProfileComplete(boolean profileComplete) {
        this.profileComplete = profileComplete;
    }


    public UserCredentials getUserCredentials() {
        return userCredentials;
    }


    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PTOUser ptoUser = (PTOUser) o;
        return profileComplete == ptoUser.profileComplete &&
                Objects.equals(userTrademarks, ptoUser.userTrademarks) &&
                Objects.equals(userCredentials, ptoUser.userCredentials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userTrademarks, profileComplete, userCredentials);
    }
}
