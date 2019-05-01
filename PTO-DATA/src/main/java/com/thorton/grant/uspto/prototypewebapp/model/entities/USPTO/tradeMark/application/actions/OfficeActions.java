package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;


@Entity
public class OfficeActions extends BaseEntity {


    private String officeAction;

    private String officeActionCode;


    private Date  dueDate;


    private String parentSerialNumber;

    private String  parentMarkImagePath;

    private String parentMarkOwnerName;






    public String getDueDateDisplay(){

        return  dueDate.toString().substring(0,10);
    }


    public String returnSomeDate(){

        return  "2/20/2020";

    }




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

    public String getOfficeActionCode() {
        return officeActionCode;
    }

    public void setOfficeActionCode(String officeActionCode) {
        this.officeActionCode = officeActionCode;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getParentSerialNumber() {
        return parentSerialNumber;
    }

    public void setParentSerialNumber(String parentSerialNumber) {
        this.parentSerialNumber = parentSerialNumber;
    }

    public String getParentMarkImagePath() {
        return parentMarkImagePath;
    }

    public void setParentMarkImagePath(String parentMarkImagePath) {
        this.parentMarkImagePath = parentMarkImagePath;
    }

    public String getParentMarkOwnerName() {
        return parentMarkOwnerName;
    }

    public void setParentMarkOwnerName(String parentMarkOwnerName) {
        this.parentMarkOwnerName = parentMarkOwnerName;
    }
}
