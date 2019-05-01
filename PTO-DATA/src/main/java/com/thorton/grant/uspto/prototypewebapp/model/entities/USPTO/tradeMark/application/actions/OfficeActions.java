package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.petition.Petition;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Objects;


@Entity
public class OfficeActions extends BaseEntity {


    private String officeAction;

    private String oficeActionCode;

    @ManyToOne
    private BaseTrademarkApplication trademarkApplication;





    @OneToOne
    private Petition  petition;


    public String getOfficeAction() {
        return officeAction;
    }

    public void setOfficeAction(String officeAction) {
        this.officeAction = officeAction;
    }

    public String getOficeActionCode() {
        return oficeActionCode;
    }

    public void setOficeActionCode(String oficeActionCode) {
        this.oficeActionCode = oficeActionCode;
    }

    public BaseTrademarkApplication getTrademarkApplication() {
        return trademarkApplication;
    }

    public void setTrademarkApplication(BaseTrademarkApplication trademarkApplication) {
        this.trademarkApplication = trademarkApplication;
    }

    public Petition getPetition() {
        return petition;
    }

    public void setPetition(Petition petition) {
        this.petition = petition;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeActions that = (OfficeActions) o;
        return Objects.equals(officeAction, that.officeAction) &&
                Objects.equals(oficeActionCode, that.oficeActionCode) &&
                Objects.equals(trademarkApplication, that.trademarkApplication) &&
                Objects.equals(petition, that.petition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeAction, oficeActionCode, trademarkApplication, petition);
    }
}
