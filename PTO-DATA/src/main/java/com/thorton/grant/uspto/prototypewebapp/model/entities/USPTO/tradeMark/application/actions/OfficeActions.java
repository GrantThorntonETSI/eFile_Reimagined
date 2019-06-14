package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "office_actions")
public class OfficeActions extends BaseEntity {


    public OfficeActions() {

        requiredActions = new HashSet<>();

        optionalActionsSelectedList = new ArrayList<>();
        optionalActionsCompletedList = new ArrayList<>();
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


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<RequiredActions> requiredActions;





    // optional action fields


    // this can be completed by either selecting no action or completing optional actions
    boolean optianlCompleted = false;

    // optional actions complete


    // use these two lists to figure out what the next page should be..

    // last value in this list is the last completed step
    ArrayList<String> optionalActionsCompletedList;



    int currentActionIndex = 0;

    // optional actions list
    ArrayList<String> optionalActionsSelectedList;

    private boolean attorneyOptional;

    private boolean ownerOptional;

    private boolean markOptional;

    private boolean gsOptional;

    private boolean additionalOptional;







    // office action is complete when both optional and required are completed.
    // optional actions can be completed by skipping






















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

    public boolean isOptianlCompleted() {
        return optianlCompleted;
    }

    public void setOptianlCompleted(boolean optianlCompleted) {
        this.optianlCompleted = optianlCompleted;
    }


    public ArrayList<String> getOptionalActionsCompletedList() {
        return optionalActionsCompletedList;
    }

    public void setOptionalActionsCompletedList(ArrayList<String> optionalActionsCompletedList) {
        this.optionalActionsCompletedList = optionalActionsCompletedList;
    }

    public ArrayList<String> getOptionalActionsSelectedList() {
        return optionalActionsSelectedList;
    }

    public void setOptionalActionsSelectedList(ArrayList<String> optionalActionsSelectedList) {
        this.optionalActionsSelectedList = optionalActionsSelectedList;
    }

    // add and remove functions for each of the lists

   public String addOptionalActionSelectedList( String action ){
        optionalActionsSelectedList.add(action);

        return action;
   }
   public void removeOptionalActionSelectedList( String action ){
        optionalActionsSelectedList.remove(action);
   }

   public String addOptionalActionCompletedList( String action ){

        optionalActionsCompletedList.add(action);

        return action;
   }

   public void removeOptionalActionCompletedList( String action ){

        optionalActionsCompletedList.remove(action);
   }

    public int getCurrentActionIndex() {
        return currentActionIndex;
    }

    public void setCurrentActionIndex(int currentActionIndex) {
        this.currentActionIndex = currentActionIndex;
    }

    public boolean isAttorneyOptional() {
        return attorneyOptional;
    }

    public void setAttorneyOptional(boolean attorneyOptional) {
        this.attorneyOptional = attorneyOptional;
    }

    public boolean isOwnerOptional() {
        return ownerOptional;
    }

    public void setOwnerOptional(boolean ownerOptional) {
        this.ownerOptional = ownerOptional;
    }

    public boolean isMarkOptional() {
        return markOptional;
    }

    public void setMarkOptional(boolean markOptional) {
        this.markOptional = markOptional;
    }

    public boolean isGsOptional() {
        return gsOptional;
    }

    public void setGsOptional(boolean gsOptional) {
        this.gsOptional = gsOptional;
    }

    public boolean isAdditionalOptional() {
        return additionalOptional;
    }

    public void setAdditionalOptional(boolean additionalOptional) {
        this.additionalOptional = additionalOptional;
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

    public boolean isRequiredActionsCompleted(){

        boolean returnValue = true;
        // check all required actions ...
        // if all completed then it is completed.

        for(Iterator<RequiredActions> iter = requiredActions.iterator(); iter.hasNext(); ) {
            RequiredActions current = iter.next();

            if(current.isRequiredActionCompleted() == false){
                returnValue = false;
            }
        }


        return returnValue;
    }


    public boolean isOfficeActionCompleted(){

        return (isRequiredActionsCompleted() && this.isOptianlCompleted());


    }


}
