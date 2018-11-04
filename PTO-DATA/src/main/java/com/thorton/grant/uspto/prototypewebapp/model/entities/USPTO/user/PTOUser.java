package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user;


import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
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


    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "ptoUser")
    private Set<BaseTrademarkApplication> myApplications;
    /////////////////////////////////////////////////////////
    // owning object i.e  ptoUser owns trademark applications
    /////////////////////////////////////////////////////////


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private Set<Lawyer> myLawyers;

    @Column(name =  "profile_complete" )
    private boolean profileComplete = false;

    @OneToOne(cascade = CascadeType.ALL)
    private UserCredentials userCredentials ;

    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////

    public Set<TradeMark> getUserTrademarks() {
        return userTrademarks;
    }

    public void setUserTrademarks(Set<TradeMark> userTrademarks) {
        this.userTrademarks = userTrademarks;
    }

    public Set<BaseTrademarkApplication> getMyApplications() {
        return myApplications;
    }

    public void setMyApplications(Set<BaseTrademarkApplication> myApplications) {
        this.myApplications = myApplications;
    }

    public Set<Lawyer> getMyLawyers() {
        return myLawyers;
    }

    public void setMyLawyers(Set<Lawyer> myLawyers) {
        this.myLawyers = myLawyers;
    }

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
        if (!super.equals(o)) return false;
        PTOUser ptoUser = (PTOUser) o;
        return profileComplete == ptoUser.profileComplete &&
                Objects.equals(userTrademarks, ptoUser.userTrademarks) &&
                Objects.equals(myApplications, ptoUser.myApplications) &&
                Objects.equals(myLawyers, ptoUser.myLawyers) &&
                Objects.equals(userCredentials, ptoUser.userCredentials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userTrademarks, myApplications, myLawyers, profileComplete, userCredentials);
    }

    @Override
    public String toString() {
        return "PTOUser{" +
                "userTrademarks=" + userTrademarks +
                ", myApplications=" + myApplications +
                ", myLawyers=" + myLawyers +
                ", profileComplete=" + profileComplete +
                ", userCredentials=" + userCredentials +
                '}';
    }
}
