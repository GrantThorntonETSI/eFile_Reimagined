package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions;

import com.thorton.grant.uspto.prototypewebapp.model.entities.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;


@Entity
@Table(name = "petiton")
public class Petition extends BaseEntity {



         @OneToOne
         private OfficeActions officeAction;


         private boolean claimDelayUnintentional;
         private boolean recievedOfficeAction;
         private boolean recievedOfficeActionSet;


    public OfficeActions getOfficeAction() {
        return officeAction;
    }

    public void setOfficeAction(OfficeActions officeAction) {
        this.officeAction = officeAction;
    }

    public boolean isClaimDelayUnintentional() {
        return claimDelayUnintentional;
    }

    public void setClaimDelayUnintentional(boolean claimDelayUnintentional) {
        this.claimDelayUnintentional = claimDelayUnintentional;
    }

    public boolean isRecievedOfficeAction() {
        return recievedOfficeAction;
    }

    public void setRecievedOfficeAction(boolean recievedOfficeAction) {
        this.recievedOfficeAction = recievedOfficeAction;
    }

    public boolean isRecievedOfficeActionSet() {
        return recievedOfficeActionSet;
    }

    public void setRecievedOfficeActionSet(boolean recievedOfficeActionSet) {
        this.recievedOfficeActionSet = recievedOfficeActionSet;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Petition petition = (Petition) o;
        return claimDelayUnintentional == petition.claimDelayUnintentional &&
                recievedOfficeAction == petition.recievedOfficeAction &&
                recievedOfficeActionSet == petition.recievedOfficeActionSet &&
                Objects.equals(officeAction, petition.officeAction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeAction, claimDelayUnintentional, recievedOfficeAction, recievedOfficeActionSet);
    }
}
