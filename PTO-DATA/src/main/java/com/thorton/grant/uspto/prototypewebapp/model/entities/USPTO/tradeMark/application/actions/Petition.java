package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions;

import com.thorton.grant.uspto.prototypewebapp.model.entities.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "petiton")
public class Petition extends BaseEntity {



         @OneToOne
         private OfficeActions officeAction;

         // check box
         private boolean claimDelayUnintentional;

         // first radio pair
         private boolean recievedOfficeAction;
         private boolean recievedOfficeActionSet;



         // second radio pair
         private boolean wantToFileResponse;
         private boolean wantToFileResponseSet;

         // Petition signature fields

         private String petitionSignatureMethod;

         private String petitionSignature;

         private Date petitoinDateSigned;

         private String petitionSignatoryName;
         private String petitionSignatoryPhone;

         private String petitionSinatoryPosition;




         // Response signature fields

        private String responseSignatureMethod;

        private String responseSignature;

        private Date responseDateSigned;

        private String responseSignatoryName;
        private String responseSignatoryPhone;

        private String responseSignatoryPosition;





        // Response text field

        private String responseText;




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

    public boolean isWantToFileResponse() {
        return wantToFileResponse;
    }

    public void setWantToFileResponse(boolean wantToFileResponse) {
        this.wantToFileResponse = wantToFileResponse;
    }

    public boolean isWantToFileResponseSet() {
        return wantToFileResponseSet;
    }

    public void setWantToFileResponseSet(boolean wantToFileResponseSet) {
        this.wantToFileResponseSet = wantToFileResponseSet;
    }

    public String getPetitionSignatureMethod() {
        return petitionSignatureMethod;
    }

    public void setPetitionSignatureMethod(String petitionSignatureMethod) {
        this.petitionSignatureMethod = petitionSignatureMethod;
    }

    public String getPetitionSignature() {
        return petitionSignature;
    }

    public void setPetitionSignature(String petitionSignature) {
        this.petitionSignature = petitionSignature;
    }

    public Date getPetitoinDateSigned() {
        return petitoinDateSigned;
    }

    public void setPetitoinDateSigned(Date petitoinDateSigned) {
        this.petitoinDateSigned = petitoinDateSigned;
    }

    public String getPetitionSignatoryName() {
        return petitionSignatoryName;
    }

    public void setPetitionSignatoryName(String petitionSignatoryName) {
        this.petitionSignatoryName = petitionSignatoryName;
    }

    public String getPetitionSignatoryPhone() {
        return petitionSignatoryPhone;
    }

    public void setPetitionSignatoryPhone(String petitionSignatoryPhone) {
        this.petitionSignatoryPhone = petitionSignatoryPhone;
    }

    public String getPetitionSinatoryPosition() {
        return petitionSinatoryPosition;
    }

    public void setPetitionSinatoryPosition(String petitionSinatoryPosition) {
        this.petitionSinatoryPosition = petitionSinatoryPosition;
    }

    public String getResponseSignatureMethod() {
        return responseSignatureMethod;
    }

    public void setResponseSignatureMethod(String responseSignatureMethod) {
        this.responseSignatureMethod = responseSignatureMethod;
    }

    public String getResponseSignature() {
        return responseSignature;
    }

    public void setResponseSignature(String responseSignature) {
        this.responseSignature = responseSignature;
    }

    public Date getResponseDateSigned() {
        return responseDateSigned;
    }

    public void setResponseDateSigned(Date responseDateSigned) {
        this.responseDateSigned = responseDateSigned;
    }

    public String getResponseSignatoryName() {
        return responseSignatoryName;
    }

    public void setResponseSignatoryName(String responseSignatoryName) {
        this.responseSignatoryName = responseSignatoryName;
    }

    public String getResponseSignatoryPhone() {
        return responseSignatoryPhone;
    }

    public void setResponseSignatoryPhone(String responseSignatoryPhone) {
        this.responseSignatoryPhone = responseSignatoryPhone;
    }

    public String getResponseSignatoryPosition() {
        return responseSignatoryPosition;
    }

    public void setResponseSignatoryPosition(String responseSignatoryPosition) {
        this.responseSignatoryPosition = responseSignatoryPosition;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
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
