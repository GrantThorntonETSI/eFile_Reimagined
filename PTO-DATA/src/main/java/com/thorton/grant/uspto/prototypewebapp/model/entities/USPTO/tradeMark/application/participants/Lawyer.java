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


    @OneToOne
    private BaseTrademarkApplication primaryConsole;

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

    public BaseTrademarkApplication getPrimaryConsole() {
        return primaryConsole;
    }

    public void setPrimaryConsole(BaseTrademarkApplication primaryConsole) {
        this.primaryConsole = primaryConsole;
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
        return Objects.equals(lawFirmName, lawyer.lawFirmName) &&
                Objects.equals(barLicense, lawyer.barLicense) &&
                Objects.equals(barJurisdiction, lawyer.barJurisdiction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lawFirmName, barLicense, barJurisdiction);
    }

    @Override
    public String toString() {
        return "Lawyer{" +
                "isPrimary=" + isPrimary +
                ", validBarAssociation=" + validBarAssociation +
                ", lawFirmName='" + lawFirmName + '\'' +
                ", barLicense='" + barLicense + '\'' +
                ", barJurisdiction='" + barJurisdiction + '\'' +
                ", primaryConsole=" + primaryConsole +
                ", poolMember=" + poolMember +
                ", client=" + client +
                '}';
    }
}
