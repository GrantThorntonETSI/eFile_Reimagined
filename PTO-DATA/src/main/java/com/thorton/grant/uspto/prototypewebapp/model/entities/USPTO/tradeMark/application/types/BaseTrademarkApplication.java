package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types;


import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.OfficeActions;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Owner;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.TradeMark;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.base.BaseEntity;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
public class BaseTrademarkApplication extends BaseEntity {

    // need to have an owner

    // could have an lawyer as well

    ////////////////////////////////////////////////////////
    // stage 1 save point flags
    ////////////////////////////////////////////////////////
    private boolean isAttorneyFiling;
    private boolean isForeignEnityFiling;
    private String currentStage;
    private String lastViewModel;

    private String ownerEmail;

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }

    public String getLastViewModel() {
        return lastViewModel;
    }

    public void setLastViewModel(String lastViewModel) {
        this.lastViewModel = lastViewModel;
    }

    // need to have 5 stages (some info is stored for each stage)

    // need to know what the next view and DTO object is needed (write a function for this)
    // controller will call this method to know what to return to the client

    // stage 1
    // what info do we need for each stage ???
    // we need to define join table
    // pto user is the owning object
    //stage 2

    ////////////////////////////////////////////////////////
    // modeling
    ////////////////////////////////////////////////////////
    //  parent entity
    ////////////////////////////////////////////////////////
    @ManyToOne
    private PTOUser ptoUser;

    ////////////////////////////////////////////////////////
    // sub ordinate objects
    ////////////////////////////////////////////////////////
    // set these details in stage
    // lawyer is the subordinate object here

    /////////////////////////////////////////////////////////////////////
    // stage 1
    /////////////////////////////////////////////////////////////////////

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "primaryConsole")
    @Nullable
    private Lawyer primaryLawyer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "poolMember")
    @Nullable
    private Set<Lawyer> availableLawyers;


    // can be a lawyer or owner ???
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "trademarkApplication")
    private Owner owner;


    /////////////////////////////////////////////////////////////////////
    // stage 2
    /////////////////////////////////////////////////////////////////////
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "initialFilingInfo")
    @Nullable
    private TradeMark tradeMark;


    @OneToMany(cascade =  CascadeType.ALL, mappedBy = "trademarkApplication")
    @Nullable
    private Set<OfficeActions> actions;
    ////////////////////////////////////////////////////////

    public PTOUser getPtoUser() {
        return ptoUser;
    }

    public void setPtoUser(PTOUser ptoUser) {
        this.ptoUser = ptoUser;
    }

    @Nullable
    public Lawyer getPrimaryLawyer() {
        return primaryLawyer;
    }

    public void setPrimaryLawyer(@Nullable Lawyer primaryLawyer) {
        this.primaryLawyer = primaryLawyer;
    }



    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public boolean isAttorneyFiling() {
        return isAttorneyFiling;
    }

    public void setAttorneyFiling(boolean attorneyFiling) {
        isAttorneyFiling = attorneyFiling;
    }

    public boolean isForeignEnityFiling() {
        return isForeignEnityFiling;
    }

    public void setForeignEnityFiling(boolean foreignEnityFiling) {
        isForeignEnityFiling = foreignEnityFiling;
    }

    @Nullable
    public TradeMark getTradeMark() {
        return tradeMark;
    }

    public void setTradeMark(@Nullable TradeMark tradeMark) {
        this.tradeMark = tradeMark;
    }

    @Nullable
    public Set<OfficeActions> getActions() {
        return actions;
    }

    public void setActions(@Nullable Set<OfficeActions> actions) {
        this.actions = actions;
    }


    // need a public method that returns the next View and DTO object needed for the view
    // we need some type of mapping, of a list of views and DTO objects associated with each stage of the
    // application


    @Nullable
    public Set<Lawyer> getAvailableLawyers() {
        return availableLawyers;
    }

    public void setAvailableLawyers(@Nullable Set<Lawyer> availableLawyers) {
        this.availableLawyers = availableLawyers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseTrademarkApplication that = (BaseTrademarkApplication) o;
        return isAttorneyFiling == that.isAttorneyFiling &&
                isForeignEnityFiling == that.isForeignEnityFiling &&
                Objects.equals(currentStage, that.currentStage) &&
                Objects.equals(lastViewModel, that.lastViewModel) &&
                Objects.equals(ownerEmail, that.ownerEmail) &&
                Objects.equals(ptoUser, that.ptoUser) &&
                Objects.equals(primaryLawyer, that.primaryLawyer) &&
                Objects.equals(availableLawyers, that.availableLawyers) &&
                Objects.equals(owner, that.owner) &&
                Objects.equals(tradeMark, that.tradeMark) &&
                Objects.equals(actions, that.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isAttorneyFiling, isForeignEnityFiling, currentStage, lastViewModel, ownerEmail, ptoUser, primaryLawyer, availableLawyers, owner, tradeMark, actions);
    }

    @Override
    public String toString() {
        return "BaseTrademarkApplication{" +
                "isAttorneyFiling=" + isAttorneyFiling +
                ", isForeignEnityFiling=" + isForeignEnityFiling +
                ", currentStage='" + currentStage + '\'' +
                ", lastViewModel='" + lastViewModel + '\'' +
                ", ownerEmail='" + ownerEmail + '\'' +
                ", ptoUser=" + ptoUser +
                ", primaryLawyer=" + primaryLawyer +
                ", availableLawyers=" + availableLawyers +
                ", owner=" + owner +
                ", tradeMark=" + tradeMark +
                ", actions=" + actions +
                '}';
    }
}
