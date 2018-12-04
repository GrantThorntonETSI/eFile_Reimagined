package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types;


import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.OfficeActions;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Owner;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.TradeMark;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.base.BaseEntity;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;


@Entity
public class BaseTrademarkApplication  {

    // need to have an owner

    // could have an lawyer as well

    public BaseTrademarkApplication() {
        availableLawyers = new HashSet<>();
        actions = new HashSet<>();
        owners = new HashSet<>();
    }

    ////////////////////////////////////////////////////////
    // stage 1 save point flags
    ////////////////////////////////////////////////////////
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trademarkName;
    private String applicationInternalID;
    private boolean isAttorneySet = false;
    private boolean isAttorneyFiling;
    private boolean isForeignEnityFiling;
    private String currentStage;
    private String lastViewModel;

    private String ownerEmail;

    public String getOwnerEmail() {
        return ownerEmail;
    }


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

    @OneToOne(cascade = CascadeType.ALL)
    @Nullable
    private Lawyer primaryLawyer;

    @OneToMany(cascade = CascadeType.ALL)
    @Nullable
    private Set<Lawyer> availableLawyers;


    // can be a lawyer or owner ???

    @OneToMany(cascade = CascadeType.ALL)
    @Nullable
    private Set<Owner> owners; // default owner   PTO user




    /////////////////////////////////////////////////////////////////////
    // stage 2
    /////////////////////////////////////////////////////////////////////
    @OneToOne(cascade = CascadeType.ALL)
    @Nullable
    private TradeMark tradeMark;


    @OneToMany(cascade =  CascadeType.ALL)
    @Nullable
    private Set<OfficeActions> actions;
    ////////////////////////////////////////////////////////


    private String ownerType;

    private String ownerSubType;

    public boolean isAttorneyPoolEmpty() {


        boolean status = false;

        if (availableLawyers.size() > 0) {
            status = true;
        }

        return  status;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerSubType() {
        return ownerSubType;
    }

    public void setOwnerSubType(String ownerSubType) {
        this.ownerSubType = ownerSubType;
    }

    public PTOUser getPtoUser() {
        return ptoUser;
    }

    public void setPtoUser(PTOUser ptoUser) {
        this.ptoUser = ptoUser;
    }

    public Lawyer getPrimaryLawyer() {
        return primaryLawyer;
    }

    public void setPrimaryLawyer(@Nullable Lawyer primaryLawyer) {
        this.primaryLawyer = primaryLawyer;
    }


    public Set<Lawyer> getAvailableLawyers() {
        return availableLawyers;
    }

    public void setAvailableLawyers(@Nullable Set<Lawyer> availableLawyers) {

        this.availableLawyers = availableLawyers;

    }

    public void addAvailableLawyer(Lawyer lawyer){
        availableLawyers.add(lawyer);

    }
    public void removeAvailableLawyer(Lawyer lawyer){
        availableLawyers.remove(lawyer);

    }
    public Lawyer findContactByEmail(String email){
        Lawyer lawyer = null;
        for(Iterator<Lawyer> iter = availableLawyers.iterator(); iter.hasNext(); ) {
             Lawyer current = iter.next();

            if(current.getEmail().equals(email)){
                lawyer = current;
            }
        }
        return lawyer;
    }

    public Lawyer findAttorneyContactByDisplayName(String name){
        Lawyer lawyer = null;
        for(Iterator<Lawyer> iter = availableLawyers.iterator(); iter.hasNext(); ) {
            Lawyer current = iter.next();

            if((current.getFirstName()+" "+current.getLastName()).equals(name)){
                lawyer = current;
            }
        }
        return lawyer;
    }


    @Nullable
    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(@Nullable Set<Owner> owners) {
        this.owners = owners;
    }

    public Owner addOwner(Owner newOwner){

        this.owners.add(newOwner);
        return newOwner;

    }

    public void removeOwner(Owner owner){
        this.owners.remove(owner);
    }

    public Owner findOwnerByEmail(String email){
        Owner owner = null;
        for(Iterator<Owner> iter = owners.iterator(); iter.hasNext(); ) {
            Owner current = iter.next();

            if(current.getEmail().equals(email)){
                owner = current;
            }
        }
        return owner;
    }

    public Owner findOwnerByDisplayName(String name){
        Owner owner = null;
        for(Iterator<Owner> iter = owners.iterator(); iter.hasNext(); ) {
            Owner current = iter.next();

            if(current.getOwnerDisplayname().equals(name)){
                owner = current;
            }
        }
        return owner;
    }


     public boolean isOwnerSet(){

        boolean ownerSet = false;

        if(owners.size() > 0){
            ownerSet = true;
        }
        return ownerSet;
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


    public TradeMark getTradeMark() {
        return tradeMark;
    }

    public void setTradeMark(@Nullable TradeMark tradeMark) {
        this.tradeMark = tradeMark;
    }


    public Set<OfficeActions> getActions() {
        return actions;
    }

    public void setActions(@Nullable Set<OfficeActions> actions) {
        this.actions = actions;
    }



    public Long getId() {
        return id;
    }

    public void copyAvailableLawyers(Set<Lawyer> availableLawyers){

        for(Iterator<Lawyer> iter = availableLawyers.iterator(); iter.hasNext(); ) {
           this.availableLawyers.add(new Lawyer( iter.next() ));
        }

    }


    public String getTrademarkName() {
        return trademarkName;
    }

    public void setTrademarkName(String trademarkName) {
        this.trademarkName = trademarkName;
    }

    public String getApplicationInternalID() {
        return applicationInternalID;
    }

    public void setApplicationInternalID(String applicationInternalID) {
        this.applicationInternalID = applicationInternalID;
    }

    public boolean isAttorneySet() {
        return isAttorneySet;
    }

    public void setAttorneySet(boolean attorneySet) {
        isAttorneySet = attorneySet;
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

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseTrademarkApplication that = (BaseTrademarkApplication) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(trademarkName, that.trademarkName) &&
                Objects.equals(applicationInternalID, that.applicationInternalID) &&
                Objects.equals(ownerEmail, that.ownerEmail) &&
                Objects.equals(availableLawyers, that.availableLawyers) &&
                Objects.equals(ownerType, that.ownerType) &&
                Objects.equals(ownerSubType, that.ownerSubType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trademarkName, applicationInternalID, ownerEmail, availableLawyers, ownerType, ownerSubType);
    }

    @Override
    public String toString() {
        return "BaseTrademarkApplication{" +
                "id=" + id +
                ", trademarkName='" + trademarkName + '\'' +
                ", applicationInternalID='" + applicationInternalID + '\'' +
                ", isAttorneySet=" + isAttorneySet +
                ", isAttorneyFiling=" + isAttorneyFiling +
                ", isForeignEnityFiling=" + isForeignEnityFiling +
                ", currentStage='" + currentStage + '\'' +
                ", lastViewModel='" + lastViewModel + '\'' +
                ", ownerEmail='" + ownerEmail + '\'' +
                ", ptoUser=" + ptoUser +
                ", primaryLawyer=" + primaryLawyer +
                ", availableLawyers=" + availableLawyers +
                ", owners=" + owners +
                ", tradeMark=" + tradeMark +
                ", actions=" + actions +
                ", ownerType='" + ownerType + '\'' +
                ", ownerSubType='" + ownerSubType + '\'' +
                '}';
    }
}
