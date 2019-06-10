package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


@Entity
@Table(name = "office_actions")
public class OfficeActions extends BaseEntity {


    public OfficeActions() {

        requiredActions = new HashSet<>();
    }

    private String officeAction;

    private String officeActionCode;


    private Date  dueDate;


    private String parentSerialNumber;

    private String  parentMarkImagePath;

    private String parentMarkOwnerName;



    private boolean standardCharacterMark;

    private String standardCharacterText;



    private boolean activeAction;



    public String getDueDateDisplay(){

        return  dueDate.toString().substring(0,10);
    }


    public String returnSomeDate(){

        return  "2/20/2020";

    }




    @ManyToOne
    private BaseTrademarkApplication trademarkApplication;


    @OneToMany(cascade = CascadeType.ALL)
    private Set<RequiredActions> requiredActions;







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


    public Set<RequiredActions> getRequiredActions() {
        return requiredActions;
    }

    public void setRequiredActions(Set<RequiredActions> requiredActions) {
        this.requiredActions = requiredActions;
    }

    public BaseTrademarkApplication getTrademarkApplication() {
        return trademarkApplication;
    }

    public void setTrademarkApplication(BaseTrademarkApplication trademarkApplication) {
        this.trademarkApplication = trademarkApplication;
    }

    public RequiredActions addRequiredActions(RequiredActions requiredActions){
        this.requiredActions.add(requiredActions);
        return requiredActions;
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

    public String getInternalID(){
        return String.valueOf(this.getId());
    }

    public boolean isActiveAction() {
        return activeAction;
    }

    public void setActiveAction(boolean activeAction) {
        this.activeAction = activeAction;
    }

    public boolean isStandardCharacterMark() {
        return standardCharacterMark;
    }

    public void setStandardCharacterMark(boolean standardCharacterMark) {
        this.standardCharacterMark = standardCharacterMark;
    }

    public String getStandardCharacterText() {
        return standardCharacterText;
    }

    public void setStandardCharacterText(String standardCharacterText) {
        this.standardCharacterText = standardCharacterText;
    }

    public String getOfficeActionLink(){
        return "/officeAction/response/"+getInternalID()+"/?trademarkID="+getTrademarkApplication().getApplicationInternalID();
    }



    public RequiredActions findRequiredActionById(String id){
        RequiredActions action = null;
        for(Iterator<RequiredActions> iter = requiredActions.iterator(); iter.hasNext(); ) {
            RequiredActions current = iter.next();

            if(current.getInternalID().equals(id)){
                action = current;
            }
        }
        return action;
    }


}
