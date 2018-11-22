package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Owner extends Contact{



    @OneToOne
    private BaseTrademarkApplication trademarkApplication;


    @ManyToOne
    private PTOUser client;



    private String ownerType;

    private String ownerEnityType;

    private String ownersubType;

    private String webSiteURL;

    private String CitizenShip;

    private String  address1;

    private String address2;

    private String address3;


    public BaseTrademarkApplication getTrademarkApplication() {
        return trademarkApplication;
    }

    public void setTrademarkApplication(BaseTrademarkApplication trademarkApplication) {
        this.trademarkApplication = trademarkApplication;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnersubType() {
        return ownersubType;
    }

    public void setOwnersubType(String ownersubType) {
        this.ownersubType = ownersubType;
    }
    public PTOUser getClient() {
        return client;
    }

    public void setClient(PTOUser client) {
        this.client = client;
    }

    public String getWebSiteURL() {
        return webSiteURL;
    }

    public void setWebSiteURL(String webSiteURL) {
        this.webSiteURL = webSiteURL;
    }

    public String getOwnerEnityType() {
        return ownerEnityType;
    }

    public void setOwnerEnityType(String ownerEnityType) {
        this.ownerEnityType = ownerEnityType;
    }

    public String getCitizenShip() {
        return CitizenShip;
    }

    public void setCitizenShip(String citizenShip) {
        CitizenShip = citizenShip;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Owner owner = (Owner) o;
        return Objects.equals(trademarkApplication, owner.trademarkApplication) &&
                Objects.equals(client, owner.client) &&
                Objects.equals(ownerType, owner.ownerType) &&
                Objects.equals(ownersubType, owner.ownersubType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), trademarkApplication, client, ownerType, ownersubType);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "trademarkApplication=" + trademarkApplication +
                ", client=" + client +
                ", ownerType='" + ownerType + '\'' +
                ", ownersubType='" + ownersubType + '\'' +
                '}';
    }
}
