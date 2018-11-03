package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Owner extends Contact{


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
        return Objects.equals(ownerType, owner.ownerType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ownerType);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerType='" + ownerType + '\'' +
                '}';
    }
}
