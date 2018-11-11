package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user;


import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.TradeMark;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PTOUsers")
public class PTOUser extends UserPersonalData {

    public PTOUser() {
        myTrademarks = new HashSet<>();
        myApplications = new HashSet<>();
        myLawyers = new HashSet<>();
    }

    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////

    @OneToMany(cascade = CascadeType.ALL)
    private Set<TradeMark> myTrademarks;


    @OneToMany( cascade = CascadeType.ALL)
    private Set<BaseTrademarkApplication> myApplications;
    /////////////////////////////////////////////////////////
    // owning object i.e  ptoUser owns trademark applications
    /////////////////////////////////////////////////////////


    @OneToMany(cascade = CascadeType.ALL)
    private Set<Lawyer> myLawyers;

    @Column(name =  "profile_complete" )
    private boolean profileComplete = false;

    @OneToOne(cascade = CascadeType.ALL)
    private UserCredentials userCredentials ;

    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    private String continuationURL = null;

    public String getContinuationURL() {
        return continuationURL;
    }

    public void setContinuationURL(String continuationURL) {
        this.continuationURL = continuationURL;
    }

    public BaseTrademarkApplication addApplication(BaseTrademarkApplication newApplication){

            myApplications.add(newApplication);
        return  newApplication;
    }


    public Lawyer addLawyer(Lawyer newLawyer){

           myLawyers.add(newLawyer);
        return newLawyer;
    }

    public void removeLawyer(Lawyer lawyer){
        myLawyers.remove(lawyer);

    }


    public TradeMark addTradeMark(TradeMark newTrademark){

          myTrademarks.add(newTrademark);
        return  newTrademark;
    }
    ///////////////////////////////////////////////////////////


    public Set<TradeMark> getMyTrademarks() {
        return myTrademarks;
    }

    public void setMyTrademarks(Set<TradeMark> myTrademarks) {
        this.myTrademarks = myTrademarks;
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
        return Objects.equals(userCredentials, ptoUser.userCredentials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userCredentials);
    }

    @Override
    public String toString() {
        return "PTOUser{" +
                "myTrademarks=" + myTrademarks +
                ", myApplications=" + myApplications +
                ", myLawyers=" + myLawyers +
                ", profileComplete=" + profileComplete +
                ", userCredentials=" + userCredentials +
                '}';
    }
}
