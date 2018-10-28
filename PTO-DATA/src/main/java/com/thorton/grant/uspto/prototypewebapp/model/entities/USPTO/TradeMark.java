package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO;

import com.thorton.grant.uspto.prototypewebapp.model.entities.BaseEntity;
import com.thorton.grant.uspto.prototypewebapp.model.entities.PTOUser;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Blob;


@Entity
@Table(name = "trade_marks")
public class TradeMark extends BaseEntity {


    private String PTOtradeMarkID;
    private String description;

    private String trademarkImage_awsS3Path;


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



    public PTOUser getTrademarkOwner() {
        return trademarkOwner;
    }

    public void setTrademarkOwner(PTOUser trademarkOwner) {
        this.trademarkOwner = trademarkOwner;
    }


}
