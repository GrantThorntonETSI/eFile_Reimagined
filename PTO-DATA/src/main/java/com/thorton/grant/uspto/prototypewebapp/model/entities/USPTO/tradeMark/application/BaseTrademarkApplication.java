package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application;


import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.OfficeActions;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Owner;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.TradeMark;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;


@Entity
public class BaseTrademarkApplication {

    // need to have an owner

    // could have an lawyer as well


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
    // owning entity - or parent entity
    ////////////////////////////////////////////////////////
    @OneToOne
    private PTOUser ptoUser;
    ////////////////////////////////////////////////////////
    // sub ordinate objects
    ////////////////////////////////////////////////////////
    // lawyer is the subordinate object here
    @OneToOne
    private Lawyer primaryLawyer;

    @OneToMany
    private Set<Lawyer> additionalLawyers;


    // can be a lawyer or owner ???
    @OneToOne
    private Owner owner;


    @OneToOne
    @Nullable
    private TradeMark tradeMark;


    @OneToMany
    @Nullable
    private Set<OfficeActions> actions;
    ////////////////////////////////////////////////////////




    // need a public method that returns the next View and DTO object needed for the view
    // we need some type of mapping, of a list of views and DTO objects associated with each stage of the
    // application











}
