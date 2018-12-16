package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;


@Entity
@Table(name = "trade_marks")
public class TradeMark extends BaseEntity {


    private String PTOtradeMarkID;
    private String description;

    private String trademarkDesignType;

    private String trademarkImagePath;
    private String trademarkBWImagePath;

    private String markLiteral;
    private String markDescription;
    private boolean markColorClaim;

    // translation
    private boolean foreignLanguageTranslationWording;
    private String foreignLanguageTranslationOriginalText;
    private String foreignLanguageTranslationUSText;

    // transliteration
    private boolean foreignLanguateTransliterationWording;
    private String foreignLanguateTransliterationOriginalText;
    private String foreignLanguateTransliterationUSText;

    // name / portrait / Signature
    private boolean containNamePortaitSignature;



    @OneToOne
    private BaseTrademarkApplication initialFilingInfo;

    private boolean typeSet = false;

    @ManyToOne
    private PTOUser trademarkOwner;


    public String getPTOtradeMarkID() {
        return PTOtradeMarkID;
    }

    public void setPTOtradeMarkID(String PTOtradeMarkID) {
        this.PTOtradeMarkID = PTOtradeMarkID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrademarkImagePath() {
        return trademarkImagePath;
    }

    public void setTrademarkImagePath(String trademarkImagePath) {
        this.trademarkImagePath = trademarkImagePath;
    }

    public BaseTrademarkApplication getInitialFilingInfo() {
        return initialFilingInfo;
    }

    public void setInitialFilingInfo(BaseTrademarkApplication initialFilingInfo) {
        this.initialFilingInfo = initialFilingInfo;
    }

    public PTOUser getTrademarkOwner() {
        return trademarkOwner;
    }

    public void setTrademarkOwner(PTOUser trademarkOwner) {
        this.trademarkOwner = trademarkOwner;
    }

    public String getTrademarkDesignType() {
        return trademarkDesignType;
    }

    public void setTrademarkDesignType(String trademarkDesignType) {
        this.trademarkDesignType = trademarkDesignType;
    }

    public boolean isTypeSet() {
        return typeSet;
    }

    public void setTypeSet(boolean typeSet) {
        this.typeSet = typeSet;
    }

    public String getTrademarkBWImagePath() {
        return trademarkBWImagePath;
    }

    public void setTrademarkBWImagePath(String trademarkBWImagePath) {
        this.trademarkBWImagePath = trademarkBWImagePath;
    }

    public String getMarkLiteral() {
        return markLiteral;
    }

    public void setMarkLiteral(String markLiteral) {
        this.markLiteral = markLiteral;
    }

    public String getMarkDescription() {
        return markDescription;
    }

    public void setMarkDescription(String markDescription) {
        this.markDescription = markDescription;
    }

    public boolean isMarkColorClaim() {
        return markColorClaim;
    }

    public void setMarkColorClaim(boolean markColorClaim) {
        this.markColorClaim = markColorClaim;
    }

    public boolean isForeignLanguageTranslationWording() {
        return foreignLanguageTranslationWording;
    }

    public void setForeignLanguageTranslationWording(boolean foreignLanguageTranslationWording) {
        this.foreignLanguageTranslationWording = foreignLanguageTranslationWording;
    }

    public String getForeignLanguageTranslationOriginalText() {
        return foreignLanguageTranslationOriginalText;
    }

    public void setForeignLanguageTranslationOriginalText(String foreignLanguageTranslationOriginalText) {
        this.foreignLanguageTranslationOriginalText = foreignLanguageTranslationOriginalText;
    }

    public String getForeignLanguageTranslationUSText() {
        return foreignLanguageTranslationUSText;
    }

    public void setForeignLanguageTranslationUSText(String foreignLanguageTranslationUSText) {
        this.foreignLanguageTranslationUSText = foreignLanguageTranslationUSText;
    }

    public boolean isForeignLanguateTransliterationWording() {
        return foreignLanguateTransliterationWording;
    }

    public void setForeignLanguateTransliterationWording(boolean foreignLanguateTransliterationWording) {
        this.foreignLanguateTransliterationWording = foreignLanguateTransliterationWording;
    }

    public String getForeignLanguateTransliterationOriginalText() {
        return foreignLanguateTransliterationOriginalText;
    }

    public void setForeignLanguateTransliterationOriginalText(String foreignLanguateTransliterationOriginalText) {
        this.foreignLanguateTransliterationOriginalText = foreignLanguateTransliterationOriginalText;
    }

    public String getForeignLanguateTransliterationUSText() {
        return foreignLanguateTransliterationUSText;
    }

    public void setForeignLanguateTransliterationUSText(String foreignLanguateTransliterationUSText) {
        this.foreignLanguateTransliterationUSText = foreignLanguateTransliterationUSText;
    }

    public boolean isContainNamePortaitSignature() {
        return containNamePortaitSignature;
    }

    public void setContainNamePortaitSignature(boolean containNamePortaitSignature) {
        this.containNamePortaitSignature = containNamePortaitSignature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeMark tradeMark = (TradeMark) o;
        return Objects.equals(PTOtradeMarkID, tradeMark.PTOtradeMarkID) &&
                Objects.equals(description, tradeMark.description) &&
                Objects.equals(trademarkDesignType, tradeMark.trademarkDesignType) &&
                Objects.equals(trademarkImagePath, tradeMark.trademarkImagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(PTOtradeMarkID, description, trademarkDesignType, trademarkImagePath);
    }

    @Override
    public String toString() {
        return "TradeMark{" +
                "PTOtradeMarkID='" + PTOtradeMarkID + '\'' +
                ", description='" + description + '\'' +
                ", trademarkDesignType='" + trademarkDesignType + '\'' +
                ", trademarkImagePath='" + trademarkImagePath + '\'' +
                ", initialFilingInfo=" + initialFilingInfo +
                ", trademarkOwner=" + trademarkOwner +
                '}';
    }
}
