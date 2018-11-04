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
        return Objects.equals(trademarkApplication, owner.trademarkApplication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), trademarkApplication);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerType='" + ownerType + '\'' +
                '}';
    }
}
