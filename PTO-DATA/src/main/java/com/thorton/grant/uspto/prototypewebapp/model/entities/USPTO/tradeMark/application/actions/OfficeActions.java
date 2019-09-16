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
        dueDate = new Date();

    }

    private String officeAction;

    private String officeActionCode;


    private Date  dueDate;




    private String parentSerialNumber;

    private String parentRegistrationNumber;

    private String  parentMarkImagePath;

    private String parentMarkOwnerName;



    private boolean standardCharacterMark;

    private String standardCharacterText;



    private boolean activeAction;



    public String getDueDateDisplay(){

        return  dueDate.toString().substring(0,10);
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
    private ArrayList<String> optionalActionsCompletedList;



    int currentActionIndex = -1;

    // optional actions list
    // we probably do not need this ...
    // should be able to use flags below
    private ArrayList<String> optionalActionsSelectedList;

    private boolean attorneyOptional;

    private boolean ownerOptional;

    private boolean markOptional;

    private boolean fbOptional;

    private boolean additionalOptional;



    boolean step1_firstTime = true;

    boolean step2_firstTime = true;

    boolean step3_firstTime = true;

    boolean step4_firstTime = true;

    boolean step5_firstTime = true;

    boolean step6_firstTime = true;

    boolean step7_firstTime = true;



    // create matching bread crumbs




    // office action is complete when both optional and required are completed.
    // optional actions can be completed by skipping







    ///////////////////////////////////////////////
   // signature fields and declaration fields
   ///////////////////////////////////////////////
    private String officeActionSignature;
    private Date officeActionDateSigned;
    private String officeActionDateSignedDisplay;

    private String officeActionSignatoryName;
    private  String officeActionSignatoryPosition;
    private String officeActionSignatoryPhone;

    private String officeActionSignatureType;
    private boolean officeActionSignDirect;



    private boolean unrepresentedOwner = false;

    private boolean authorizedUSattorney = false;

    private boolean authorizedCAattorney = false;

    private boolean autorizationComplete = false;
    ///////////////////////////////////////////////








   //section 8 feilds
    private boolean optionalSection8;

    private boolean optionalSection8Set;
    private String section8PrevLink;

    private String actionType = "default";




    // global action helper fields

    private String uniqueKey;



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

        if(getOfficeActionCode().equals("Issuance Of Allowance")){
            return "/noa/response/" + getInternalID() + "/?trademarkID=" + getTrademarkApplication().getApplicationInternalID();
        }
        else {
            if(getOfficeActionCode().equals("Registered")){
                return "/renew/response/" + getInternalID() + "/?trademarkID=" + getTrademarkApplication().getApplicationInternalID();
            }
            else {
                return "/officeAction/response/" + getInternalID() + "/?trademarkID=" + getTrademarkApplication().getApplicationInternalID();
            }


        }
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


   public ArrayList<String> getOrderedSelectedList(){
        ArrayList<String> ordereddList = new ArrayList<>();

        if(this.attorneyOptional == true){
            ordereddList.add("attorney");
            ordereddList.add("attorney-select");
        }
        if(this.ownerOptional == true){
            ordereddList.add("owner");

        }
        if(this.markOptional == true){
            ordereddList.add("mark");
        }
        if(this.fbOptional == true){
            ordereddList.add("fb");
        }
        if(this.additionalOptional == true){
            ordereddList.add("additional");
        }

        // this one is always there
        ordereddList.add("signature");

        return ordereddList;
   }


   public ArrayList<BreadCrumb> getOAbreadCrumbs(){
       ArrayList<BreadCrumb> crumbList = new ArrayList<>();

       if(this.attorneyOptional == true){
          BreadCrumb breadCrumb = new BreadCrumb();
          breadCrumb.setDescription("Attorney");


          ArrayList<String> orderedList = getOrderedSelectedList();

          if( orderedList.get(currentActionIndex).equals("attorney") || orderedList.get(currentActionIndex).equals("attorney-select") ){
              breadCrumb.setActiveBreadCrumb(true);
          }
          crumbList.add(breadCrumb);
       }
       if(this.ownerOptional == true){
           BreadCrumb breadCrumb = new BreadCrumb();
           breadCrumb.setDescription("Owner");
           ArrayList<String> orderedList = getOrderedSelectedList();

           if( orderedList.get(currentActionIndex).equals("owner") ){
               breadCrumb.setActiveBreadCrumb(true);
           }
           crumbList.add(breadCrumb);

       }
       if(this.markOptional == true){
           BreadCrumb breadCrumb = new BreadCrumb();
           breadCrumb.setDescription("Mark");
           ArrayList<String> orderedList = getOrderedSelectedList();

           if( orderedList.get(currentActionIndex).equals("mark") ){
               breadCrumb.setActiveBreadCrumb(true);
           }
           crumbList.add(breadCrumb);
       }
       if(this.fbOptional == true){
           BreadCrumb breadCrumb = new BreadCrumb();
           breadCrumb.setDescription("Filing Basis");
           ArrayList<String> orderedList = getOrderedSelectedList();

           if( orderedList.get(currentActionIndex).equals("fb") ){
               breadCrumb.setActiveBreadCrumb(true);
           }
           crumbList.add(breadCrumb);
       }
       if(this.additionalOptional == true){
           BreadCrumb breadCrumb = new BreadCrumb();
           breadCrumb.setDescription("Additional");
           ArrayList<String> orderedList = getOrderedSelectedList();

           if( orderedList.get(currentActionIndex).equals("additional") ){
               breadCrumb.setActiveBreadCrumb(true);
           }
           crumbList.add(breadCrumb);
       }

       // this one is always there
       BreadCrumb breadCrumb = new BreadCrumb();
       breadCrumb.setDescription("Signature");
       ArrayList<String> orderedList = getOrderedSelectedList();

       if( orderedList.get(currentActionIndex).equals("signature") ){
           breadCrumb.setActiveBreadCrumb(true);
       }
       crumbList.add(breadCrumb);

       return crumbList;


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

    public boolean isFbOptional() {
        return fbOptional;
    }

    public boolean isStep1_firstTime() {
        return step1_firstTime;
    }

    public void setStep1_firstTime(boolean step1_firstTime) {
        this.step1_firstTime = step1_firstTime;
    }

    public boolean isStep2_firstTime() {
        return step2_firstTime;
    }

    public void setStep2_firstTime(boolean step2_firstTime) {
        this.step2_firstTime = step2_firstTime;
    }

    public boolean isStep3_firstTime() {
        return step3_firstTime;
    }

    public void setStep3_firstTime(boolean step3_firstTime) {
        this.step3_firstTime = step3_firstTime;
    }

    public boolean isStep4_firstTime() {
        return step4_firstTime;
    }

    public void setStep4_firstTime(boolean step4_firstTime) {
        this.step4_firstTime = step4_firstTime;
    }

    public boolean isStep5_firstTime() {
        return step5_firstTime;
    }

    public void setStep5_firstTime(boolean step5_firstTime) {
        this.step5_firstTime = step5_firstTime;
    }

    public boolean isStep6_firstTime() {
        return step6_firstTime;
    }

    public void setStep6_firstTime(boolean step6_firstTime) {
        this.step6_firstTime = step6_firstTime;
    }

    public boolean isStep7_firstTime() {
        return step7_firstTime;
    }

    public void setStep7_firstTime(boolean step7_firstTime) {
        this.step7_firstTime = step7_firstTime;
    }

    public void setFbOptional(boolean fbOptional) {
        this.fbOptional = fbOptional;
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


    public String getOfficeActionSignature() {
        return officeActionSignature;
    }

    public void setOfficeActionSignature(String officeActionSignature) {
        this.officeActionSignature = officeActionSignature;
    }

    public Date getOfficeActionDateSigned() {
        return officeActionDateSigned;
    }

    public void setOfficeActionDateSigned(Date officeActionDateSigned) {
        this.officeActionDateSigned = officeActionDateSigned;
    }

    public String getOfficeActionDateSignedDisplay() {
        return officeActionDateSignedDisplay;
    }

    public void setOfficeActionDateSignedDisplay(String officeActionDateSignedDisplay) {
        this.officeActionDateSignedDisplay = officeActionDateSignedDisplay;
    }

    public String getOfficeActionSignatoryName() {
        return officeActionSignatoryName;
    }

    public void setOfficeActionSignatoryName(String officeActionSignatoryName) {
        this.officeActionSignatoryName = officeActionSignatoryName;
    }

    public String getOfficeActionSignatoryPosition() {
        return officeActionSignatoryPosition;
    }

    public void setOfficeActionSignatoryPosition(String officeActionSignatoryPosition) {
        this.officeActionSignatoryPosition = officeActionSignatoryPosition;
    }

    public String getOfficeActionSignatoryPhone() {
        return officeActionSignatoryPhone;
    }

    public void setOfficeActionSignatoryPhone(String officeActionSignatoryPhone) {
        this.officeActionSignatoryPhone = officeActionSignatoryPhone;
    }

    public String getOfficeActionSignatureType() {
        return officeActionSignatureType;
    }

    public void setOfficeActionSignatureType(String officeActionSignatureType) {
        this.officeActionSignatureType = officeActionSignatureType;
    }

    public boolean isOfficeActionSignDirect() {
        return officeActionSignDirect;
    }

    public void setOfficeActionSignDirect(boolean officeActionSignDirect) {
        this.officeActionSignDirect = officeActionSignDirect;
    }

    public boolean isUnrepresentedOwner() {
        return unrepresentedOwner;
    }

    public void setUnrepresentedOwner(boolean unrepresentedOwner) {
        this.unrepresentedOwner = unrepresentedOwner;
    }

    public boolean isAuthorizedUSattorney() {
        return authorizedUSattorney;
    }

    public void setAuthorizedUSattorney(boolean authorizedUSattorney) {
        this.authorizedUSattorney = authorizedUSattorney;
    }

    public boolean isAuthorizedCAattorney() {
        return authorizedCAattorney;
    }

    public void setAuthorizedCAattorney(boolean authorizedCAattorney) {
        this.authorizedCAattorney = authorizedCAattorney;
    }

    public boolean isAutorizationComplete() {
        return autorizationComplete;
    }

    public void setAutorizationComplete(boolean autorizationComplete) {
        this.autorizationComplete = autorizationComplete;
    }



    // bread crumb methods
    private Date actionDate;

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public String getActionDateDisplay(){
        if(actionDate != null){
            return actionDate.toString().substring(0, 10);
        }
        else {
            return "";
        }

    }

    public boolean isOptionalSection8() {
        return optionalSection8;
    }

    public void setOptionalSection8(boolean optionalSection8) {
        this.optionalSection8 = optionalSection8;
    }

    public boolean isOptionalSection8Set() {
        return optionalSection8Set;
    }

    public void setOptionalSection8Set(boolean optionalSection8Set) {
        this.optionalSection8Set = optionalSection8Set;
    }

    public String getSection8PrevLink() {
        return section8PrevLink;
    }

    public void setSection8PrevLink(String section8PrevLink) {
        this.section8PrevLink = section8PrevLink;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getParentRegistrationNumber() {
        return parentRegistrationNumber;
    }

    public void setParentRegistrationNumber(String parentRegistrationNumber) {
        this.parentRegistrationNumber = parentRegistrationNumber;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }
}
