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

    private String trademarkImage_awsS3Path;


    @OneToOne
    private BaseTrademarkApplication initialFilingInfo;


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

    public String getTrademarkImage_awsS3Path() {
        return trademarkImage_awsS3Path;
    }

    public void setTrademarkImage_awsS3Path(String trademarkImage_awsS3Path) {
        this.trademarkImage_awsS3Path = trademarkImage_awsS3Path;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeMark tradeMark = (TradeMark) o;
        return Objects.equals(PTOtradeMarkID, tradeMark.PTOtradeMarkID) &&
                Objects.equals(description, tradeMark.description) &&
                Objects.equals(trademarkImage_awsS3Path, tradeMark.trademarkImage_awsS3Path) &&
                Objects.equals(initialFilingInfo, tradeMark.initialFilingInfo) &&
                Objects.equals(trademarkOwner, tradeMark.trademarkOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(PTOtradeMarkID, description, trademarkImage_awsS3Path, initialFilingInfo, trademarkOwner);
    }

    @Override
    public String toString() {
        return "TradeMark{" +
                "PTOtradeMarkID='" + PTOtradeMarkID + '\'' +
                ", description='" + description + '\'' +
                ", trademarkImage_awsS3Path='" + trademarkImage_awsS3Path + '\'' +
                ", initialFilingInfo=" + initialFilingInfo +
                ", trademarkOwner=" + trademarkOwner +
                '}';
    }
}
