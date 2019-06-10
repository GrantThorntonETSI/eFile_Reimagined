package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "required_actions")
public class RequiredActions extends OfficeActions{






    @ManyToOne
    private OfficeActions parentOfficeAction;


    private String requiredActionType;



    // fields for translation

    private String translationTextLanguage;

    private String translationTextForeign;
    private String translationTextEnglish;


    // fields for disclaimer
    private String disclaimerText;


    // fields for not entitled to register
    private String additionalInfoNotEntitled;

    private String specimenSupportNotEntitled;



    // insufficient fees ???




    public String getRequiredActionType() {
        return requiredActionType;
    }

    public void setRequiredActionType(String requiredActionType) {
        this.requiredActionType = requiredActionType;
    }

    public String getTranslationTextForeign() {
        return translationTextForeign;
    }

    public void setTranslationTextForeign(String translationTextForeign) {
        this.translationTextForeign = translationTextForeign;
    }

    public String getTranslationTextEnglish() {
        return translationTextEnglish;
    }

    public void setTranslationTextEnglish(String translationTextEnglish) {
        this.translationTextEnglish = translationTextEnglish;
    }

    public String getDisclaimerText() {
        return disclaimerText;
    }

    public void setDisclaimerText(String disclaimerText) {
        this.disclaimerText = disclaimerText;
    }

    public String getAdditionalInfoNotEntitled() {
        return additionalInfoNotEntitled;
    }

    public void setAdditionalInfoNotEntitled(String additionalInfoNotEntitled) {
        this.additionalInfoNotEntitled = additionalInfoNotEntitled;
    }

    public String getSpecimenSupportNotEntitled() {
        return specimenSupportNotEntitled;
    }

    public void setSpecimenSupportNotEntitled(String specimenSupportNotEntitled) {
        this.specimenSupportNotEntitled = specimenSupportNotEntitled;
    }


    public OfficeActions getParentOfficeAction() {
        return parentOfficeAction;
    }

    public void setParentOfficeAction(OfficeActions parentOfficeAction) {
        this.parentOfficeAction = parentOfficeAction;
    }


    public String getTranslationTextLanguage() {
        return translationTextLanguage;
    }

    public void setTranslationTextLanguage(String translationTextLanguage) {
        this.translationTextLanguage = translationTextLanguage;
    }
}
