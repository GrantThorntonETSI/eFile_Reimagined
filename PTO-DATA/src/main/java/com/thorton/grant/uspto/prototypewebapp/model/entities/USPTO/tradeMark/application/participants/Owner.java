package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Owner extends Contact{



    @OneToOne
    private BaseTrademarkApplication trademarkApplication;

    private String ownerType;

    private String ownersubType;

    public BaseTrademarkApplication getTrademarkApplication() {
        return trademarkApplication;
    }

    public void setTrademarkApplication(BaseTrademarkApplication trademarkApplication) {
        this.trademarkApplication = trademarkApplication;
    }

    public String getOwnersubType() {
        return ownersubType;
    }

    public void setOwnersubType(String ownersubType) {
        this.ownersubType = ownersubType;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Owner owner = (Owner) o;
        return Objects.equals(trademarkApplication, owner.trademarkApplication) &&
                Objects.equals(ownerType, owner.ownerType) &&
                Objects.equals(ownersubType, owner.ownersubType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), trademarkApplication, ownerType, ownersubType);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "trademarkApplication=" + trademarkApplication +
                ", ownerType='" + ownerType + '\'' +
                ", ownersubType='" + ownersubType + '\'' +
                '}';
    }
}
