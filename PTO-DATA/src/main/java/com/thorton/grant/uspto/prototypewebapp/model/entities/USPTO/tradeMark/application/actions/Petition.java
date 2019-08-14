package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "petiton")
public class Petition extends OfficeActions {



        private String petitionTitle;


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

         private String petitoinDateSignedDisplay;

         private String petitionSignatoryName;
         private String petitionSignatoryPhone;

         private String petitionSinatoryPosition;




         // Response signature fields

        private String responseSignatureMethod;

        private String responseSignature;

        private String responseDateSignedDisplay;

        private String responseSignatoryName;
        private String responseSignatoryPhone;

        private String responseSignatoryPosition;





        // Response text field

        private String responseText;


        private boolean activePetition;

        private String actionID; // corresponding office action

        private String type ="office action";



        // sou petition response fields
        private boolean  reissueOfficeAction;
        private boolean reissueRadioSet;










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

    public String getPetitoinDateSignedDisplay() {
        return petitoinDateSignedDisplay;
    }

    public void setPetitoinDateSignedDisplay(String petitoinDateSignedDisplay) {
        this.petitoinDateSignedDisplay = petitoinDateSignedDisplay;
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

    public String getResponseDateSignedDisplay() {
        return responseDateSignedDisplay;
    }

    public void setResponseDateSignedDisplay(String responseDateSignedDisplay) {
        this.responseDateSignedDisplay = responseDateSignedDisplay;
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

    public boolean isActivePetition() {
        return activePetition;
    }

    public void setActivePetition(boolean activePetition) {
        this.activePetition = activePetition;
    }

    public String getPetitionsLink(){

        if(type.equals("noa") == false) {
            return "/petitions/revAbandoned/" + getInternalID() + "/?trademarkID=" + getTrademarkApplication().getApplicationInternalID();
        }
        else{
            return "/petitions/revAbandoned/" + getInternalID() + "/?trademarkID=" + getTrademarkApplication().getApplicationInternalID();
        }
    }

    public String getActionID() {
        return actionID;
    }

    public void setActionID(String actionID) {
        this.actionID = actionID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPetitionTitle() {
        return petitionTitle;
    }

    public void setPetitionTitle(String petitionTitle) {
        this.petitionTitle = petitionTitle;
    }


    public boolean isReissueOfficeAction() {
        return reissueOfficeAction;
    }

    public void setReissueOfficeAction(boolean reissueOfficeAction) {
        this.reissueOfficeAction = reissueOfficeAction;
    }

    public boolean isReissueRadioSet() {
        return reissueRadioSet;
    }

    public void setReissueRadioSet(boolean reissueRadioSet) {
        this.reissueRadioSet = reissueRadioSet;
    }
}
