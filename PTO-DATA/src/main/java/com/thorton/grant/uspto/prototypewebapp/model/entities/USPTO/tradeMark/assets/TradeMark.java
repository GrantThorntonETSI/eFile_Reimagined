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
