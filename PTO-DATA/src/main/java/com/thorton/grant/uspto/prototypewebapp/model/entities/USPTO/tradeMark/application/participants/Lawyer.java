package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants;


import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Lawyer extends Contact {

    private boolean isPrimary;
    private boolean validBarAssociation;

    private String lawFirmName;
    private String barLicense;
    private String barJurisdiction;

    public Lawyer() {
    }

    // object copy constructor
    public Lawyer(Lawyer lawyer) {
        this.setEmail(lawyer.getEmail());
        this.setFirstName(lawyer.getFirstName());
        this.setLastName(lawyer.getLastName());
        this.setLawFirmName(lawyer.getLawFirmName());
        this.setBarLicense(lawyer.getBarLicense());
        this.setBarJurisdiction(lawyer.getBarJurisdiction());
        this.setClient(lawyer.getClient());
        this.setPrimary(lawyer.isPrimary());
    }

    @OneToOne
    private BaseTrademarkApplication primaryCase;

    @ManyToOne
    private BaseTrademarkApplication poolMember;


    @ManyToOne
    private PTOUser client;

    public PTOUser getClient() {
        return client;
    }

    public void setClient(PTOUser client) {
        this.client = client;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public boolean isValidBarAssociation() {
        return validBarAssociation;
    }

    public void setValidBarAssociation(boolean validBarAssociation) {
        this.validBarAssociation = validBarAssociation;
    }

    public String getLawFirmName() {
        return lawFirmName;
    }

    public void setLawFirmName(String lawFirmName) {
        this.lawFirmName = lawFirmName;
    }

    public String getBarLicense() {
        return barLicense;
    }

    public void setBarLicense(String barLicense) {
        this.barLicense = barLicense;
    }

    public String getBarJurisdiction() {
        return barJurisdiction;
    }

    public void setBarJurisdiction(String barJurisdiction) {
        this.barJurisdiction = barJurisdiction;
    }

    public BaseTrademarkApplication getPrimaryCase() {
        return primaryCase;
    }

    public void setPrimaryCase(BaseTrademarkApplication primaryCase) {
        this.primaryCase = primaryCase;
    }

    public BaseTrademarkApplication getPoolMember() {
        return poolMember;
    }

    public void setPoolMember(BaseTrademarkApplication poolMember) {
        this.poolMember = poolMember;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Lawyer lawyer = (Lawyer) o;
        return isPrimary == lawyer.isPrimary &&
                validBarAssociation == lawyer.validBarAssociation &&
                Objects.equals(lawFirmName, lawyer.lawFirmName) &&
                Objects.equals(barLicense, lawyer.barLicense) &&
                Objects.equals(barJurisdiction, lawyer.barJurisdiction) &&
                Objects.equals(primaryCase, lawyer.primaryCase) &&
                Objects.equals(poolMember, lawyer.poolMember) &&
                Objects.equals(client, lawyer.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), validBarAssociation, lawFirmName, barLicense, barJurisdiction, primaryCase, poolMember, client);
    }

    @Override
    public String toString() {
        return "Lawyer{" +
                "isPrimary=" + isPrimary +
                ", validBarAssociation=" + validBarAssociation +
                ", lawFirmName='" + lawFirmName + '\'' +
                ", barLicense='" + barLicense + '\'' +
                ", barJurisdiction='" + barJurisdiction + '\'' +
                ", primaryCase=" + primaryCase +
                ", poolMember=" + poolMember +
                ", client=" + client +
                '}';
    }
}
