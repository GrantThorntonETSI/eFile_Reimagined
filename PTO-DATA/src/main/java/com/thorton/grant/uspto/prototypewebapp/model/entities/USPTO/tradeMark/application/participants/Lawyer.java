package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants;


import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Lawyer extends Contact {

    private boolean isPrimary;
    private boolean validBarAssociation;

    private String lawFirmName;
    private String barLicense;
    private String barJurisdiction;


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
                Objects.equals(barJurisdiction, lawyer.barJurisdiction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isPrimary, validBarAssociation, lawFirmName, barLicense, barJurisdiction);
    }


    @Override
    public String toString() {
        return "Lawyer{" +
                "isPrimary=" + isPrimary +
                ", validBarAssociation=" + validBarAssociation +
                ", lawFirmName='" + lawFirmName + '\'' +
                ", barLicense='" + barLicense + '\'' +
                ", barJurisdiction='" + barJurisdiction + '\'' +
                '}';
    }
}
