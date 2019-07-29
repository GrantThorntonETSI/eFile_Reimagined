package com.thorton.grant.uspto.prototypewebapp.config.bootstrap;

import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.NoticeOfAllowance;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.OfficeActions;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.Petition;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.RequiredActions;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.document_events.FilingDocumentEvent;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.GSClassCategory;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.GoodAndService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Iterator;
import java.util.TimerTask;


@Component
@PropertySource("classpath:server-host-AWS-prod1.properties")

public class FilingStatusUpdateTask extends TimerTask {



    private  final ServiceBeanFactory serviceBeanFactory;

    public FilingStatusUpdateTask(ServiceBeanFactory serviceBeanFactory) {
        this.serviceBeanFactory = serviceBeanFactory;

        System.out.println("task consturctor called.");
    }

    // read properties from property file
    // for duration intervals

    // create variables




    // note. these numbers will be in days
    @Value("${uspto.blackoutPeriod}")
    private long blackOutPeriodDuration = 1*60*1000;  // 1 mins

    @Value("${uspto.officeaction1}")
    private long firstOfficeActionDuration = 1*60*1000;  // 5 mins


    @Value("${uspto.officeaction2b}")
    private long durationToRevivieWithoutClaim = 10*60*1000; //1 minsh

    @Value("${uspto.officeaction2}")
    private long durationToReviveWithClaim = 1*60*1000;   // 1 mins



    @PostConstruct
    public void init() {

        System.out.println("================== " + "post construct check" + "================== ");


        System.out.println("================== " + blackOutPeriodDuration + "================== ");

        System.out.println("================== " + firstOfficeActionDuration + "================== ");

        System.out.println("================== " + durationToReviveWithClaim + "================== ");
    }



    // add date fields to the filings object

    @Override
    public void run() {



        System.out.println("run scheduled jobs.");


        checkBlackOutPeriod();
        checkOfficeActionPeriod1();
        checkOfficeActionPeriod2();



    }



    // check submitted date + interval

   public void checkBlackOutPeriod(){


        // loop through all filings and output filing date value in milli seconds
        System.out.println("checking black out period status for filings : "+blackOutPeriodDuration);

        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();


        for(Iterator<BaseTrademarkApplication> iter = baseTradeMarkApplicationService.findAll().iterator(); iter.hasNext(); ) {
            BaseTrademarkApplication current = iter.next();

            if((current.getApplicationFilingDate() != null && current.getFilingStatus().equals("TEAS RF New Application") )|| (current.getApplicationFilingDate() != null && current.getFilingStatus().equals("New Application") ) ){
                // check that date + duration against current time
              if((current.getApplicationFilingDate().getTime() + current.getBlackOutPeriod()) < new Date().getTime()){

                  System.out.println("Filing has expired from the black out period");

                  //baseTradeMarkApplicationService.save(current);

                  // we need to check current filings to make sure there are no active office action

                  if(current.hasActiveOfficeAction() == false){

                      OfficeActions officeActions = new OfficeActions();
                      officeActions.setParentMarkImagePath(current.getTradeMark().getTrademarkImagePath());
                      officeActions.setStandardCharacterMark(current.isStandardTextMark());
                      officeActions.setStandardCharacterText(current.getTradeMark().getTrademarkStandardCharacterText());
                      officeActions.setParentMarkOwnerName(current.getPrimaryOwner().getOwnerDisplayname());
                      officeActions.setParentSerialNumber(current.getTrademarkName());
                      officeActions.setActiveAction(true);
                      long dueDate = new Date().getTime()+current.getBlackOutPeriod()+current.getOfficeActionResponsePeriod();
                      officeActions.setDueDate(new Date(dueDate));
                      //officeActions.setOfficeActionCode("Missing transliteration");


                      // create office action event here
                      // filing document is only created if an office action is created with required actions




                      // required actions section


                      // required action Translation
                      if (current.getTradeMark().isStandardCharacterMark() || current.getTradeMark().getTrademarkDesignType().equals("Design with Text")) {
                          if (current.getTradeMark().getForeignLanguageTranslationUSText() == null || current.getTradeMark().getForeignLanguageTranslationUSText() == null || current.getTradeMark().getForeignLanguageType_translation() == null ){

                              // create required action here
                              RequiredActions requiredActions = new RequiredActions();
                              requiredActions.setRequiredActionType("Translation of Foreign Wording");
                              requiredActions.setTranslationTextForeign(current.getTradeMark().getForeignLanguageTranslationOriginalText());
                              requiredActions.setTranslationTextEnglish(current.getTradeMark().getForeignLanguageTranslationUSText());
                              requiredActions.setTranslationTextLanguage(current.getTradeMark().getForeignLanguageType_translation());



                              officeActions.addRequiredActions(requiredActions);



                          }


                      }

                      // required action disclaimer

                      if(current.getTradeMark().getDisclaimerDeclarationList().size() == 0){
                          // you have to provide at least one disclaimer
                          RequiredActions requiredActions = new RequiredActions();
                          requiredActions.setRequiredActionType("Disclaimer Required");

                          officeActions.addRequiredActions(requiredActions);

                      }


                      // required action SOU i.e if all goods and services are in use for each of the class

                      // if the class is declared to be in use, and have dates
                          // then check for specimen
                      // if not declared in use


                      /*

                      boolean SOUmissing = true;
                      for(Iterator<GSClassCategory> iterClassCategory = current.getGoodAndServicesCategories().iterator(); iterClassCategory.hasNext(); ) {
                         GSClassCategory currentCategory = iterClassCategory.next();
                         if(currentCategory.isAtLeastOneGoodInCommerce()){
                             // check if class level specimen is set
                             for(Iterator<GoodAndService> iterGS = currentCategory.getGoodAndServices().iterator(); iterGS.hasNext(); ) {
                                 GoodAndService currentGS = iterGS.next();
                                 if(currentGS.getSampleImagePath() != null){ // found a speciemn for one of the goods and service
                                     SOUmissing = false;
                                 }

                             }

                         }
                      }

                      if(SOUmissing){
                          // create required action for SOU * not all goods and services declared in use have a specimen
                          RequiredActions requiredActions = new RequiredActions();
                          requiredActions.setRequiredActionType("SOU");

                          officeActions.addRequiredActions(requiredActions);

                      }

                    */



                      // end of required actions section



                      // office action is created only if there are required actions
                      if(officeActions.getRequiredActions().size() > 0){
                          current.setFilingStatus("Non-Final Action Mailed");
                          officeActions.setOfficeActionCode("Non-Final Action Mailed");


                          // create  an default office action object and attach it to filing
                          current.addOfficeAction(officeActions);
                          officeActions.setTrademarkApplication(current);

                          FilingDocumentEvent filingDocumentEvent = new FilingDocumentEvent();
                          filingDocumentEvent.setEventDescription("Office Action Outgoing");

                          filingDocumentEvent.setDocumentType("XML");
                          Date date = new Date();
                          filingDocumentEvent.setEventDate(date);

                          current.addFilingDocumentEvent(filingDocumentEvent);
                      }

                      baseTradeMarkApplicationService.save(current);




                  }




                  // also check for number of required actions.
                  // if there are none. office action is not saved





                  // set relevant office actions



              }
              else{
                  System.out.println("filing is still in the black out period");

              }
            }
            else{
                System.out.println("Filing is not Submitted yet.");
            }
        }


    }



    // check submitted date + interval + office action interval
    public void checkOfficeActionPeriod1(){

        System.out.println("checking Office Action period 1 status for filings : "+firstOfficeActionDuration);

        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();


        for(Iterator<BaseTrademarkApplication> iter = baseTradeMarkApplicationService.findAll().iterator(); iter.hasNext(); ) {

            BaseTrademarkApplication current = iter.next();
            if(current.getApplicationFilingDate() != null && current.getFilingStatus().equals("Non-Final Action Mailed") == true){
                // check that date + duration against current time
                if((current.getApplicationFilingDate().getTime() + current.getBlackOutPeriod() + current.getOfficeActionResponsePeriod()) < new Date().getTime()){

                    System.out.println("Filing has expired from the office action period");

                    // check if office action has been completed ..
                    for(Iterator<OfficeActions> iter3 = current.getOfficeActions().iterator(); iter3.hasNext(); ) {
                        OfficeActions current3 = iter3.next();

                        if(current3.isOfficeActionCompleted() == false){

                            // remove office action ..or set it to inactive
                            // so on the dashboard. we only show actions that are active
                            //
                            // do the same thing. create petition object and attach it to the filing
                            Petition petition = new Petition();
                            petition.setParentMarkImagePath(current.getTradeMark().getTrademarkImagePath());
                            petition.setStandardCharacterMark(current.isStandardTextMark());
                            petition.setStandardCharacterText(current.getTradeMark().getTrademarkStandardCharacterText());
                            petition.setParentMarkOwnerName(current.getPrimaryOwner().getOwnerDisplayname());
                            petition.setParentSerialNumber(current.getTrademarkName());

                            petition.setActionID(String.valueOf(current.getId()));

                            long dueDate = new Date().getTime()+current.getBlackOutPeriod()+current.getOfficeActionResponsePeriod()+current.getPetitionPeriod();
                            petition.setDueDate(new Date(dueDate));


                            current.setFilingStatus("Abandoned - Failure to Respond or Late Response");
                            petition.setOfficeActionCode("Abandoned - Failure to Respond or Late Response");

                            petition.setActivePetition(true);
                            current.addPetition(petition);
                            petition.setTrademarkApplication(current);
                            current3.setActiveAction(false);

                            FilingDocumentEvent filingDocumentEvent = new FilingDocumentEvent();
                            filingDocumentEvent.setEventDescription("Filing Abandoned");

                            filingDocumentEvent.setDocumentType("XML");
                            Date date = new Date();
                            filingDocumentEvent.setEventDate(date);

                            current.addFilingDocumentEvent(filingDocumentEvent);


                            // go back and set any active actions to in-active
                            //for(Iterator<OfficeActions> iter2 = current.getOfficeActions().iterator(); iter2.hasNext(); ) {
                            //    OfficeActions current2 = iter2.next();
                            //    current2.setActiveAction(false);

                            //}

                            baseTradeMarkApplicationService.save(current);


                        }



                    }



                    // only execute below code if office action is not completed...


                }
                else{
                    System.out.println("filing is still in respond to office action period or filing is in a different state");
                    if((current.getApplicationFilingDate().getTime() + current.getBlackOutPeriod() + current.getOfficeActionResponsePeriod() + current.getIssuranceOfAllowancePeriod()) < new Date().getTime()){
                        for(Iterator<OfficeActions> iter4 = current.getOfficeActions().iterator(); iter4.hasNext(); ) {
                            OfficeActions current4 = iter4.next();
                            if(current4.isOfficeActionCompleted() == true){
                                // skip examiner review period
                                // filing is accepted
                                // change filing status
                                // create filing document event

                                current.setFilingStatus("Accepted Filing");


                                current4.setActiveAction(false);

                                FilingDocumentEvent filingDocumentEvent = new FilingDocumentEvent();
                                filingDocumentEvent.setEventDescription("Filing Accepted");

                                filingDocumentEvent.setDocumentType("XML");
                                Date date = new Date();
                                filingDocumentEvent.setEventDate(date);

                                current.addFilingDocumentEvent(filingDocumentEvent);


                                // go back and set any active actions to in-active
                                //for(Iterator<OfficeActions> iter2 = current.getOfficeActions().iterator(); iter2.hasNext(); ) {
                                //    OfficeActions current2 = iter2.next();
                                //    current2.setActiveAction(false);

                                //}

                                baseTradeMarkApplicationService.save(current);


                            }

                        }
                    }
                    // check for accepted filings

                }
            }
            else{
                if(current.getFilingStatus().equals("Accepted Filing")){
                    // check if filing is 1b (not all in use)
                    // check for notice of allowance
                    if(current.isMarkInUseForAllGS() == false){
                        // 1b filing

                        // create notice of allowance
                        NoticeOfAllowance noa = new NoticeOfAllowance();
                        noa.setParentMarkImagePath(current.getTradeMark().getTrademarkImagePath());
                        noa.setStandardCharacterMark(current.isStandardTextMark());
                        noa.setStandardCharacterText(current.getTradeMark().getTrademarkStandardCharacterText());
                        noa.setParentMarkOwnerName(current.getPrimaryOwner().getOwnerDisplayname());
                        noa.setParentSerialNumber(current.getTrademarkName());
                        noa.setActiveAction(true);
                        long dueDate = new Date().getTime()+current.getBlackOutPeriod()+current.getOfficeActionResponsePeriod();
                        noa.setDueDate(new Date(dueDate));


                        // you have to provide at least one disclaimer
                        RequiredActions requiredActions = new RequiredActions();
                        requiredActions.setRequiredActionType("Statement of Use");

                        noa.addRequiredActions(requiredActions);


                        current.setFilingStatus("Issuance Of Allowance");
                        noa.setOfficeActionCode("Issuance Of Allowance");


                        // create  an default office action object and attach it to filing
                        current.addOfficeAction(noa);
                        noa.setTrademarkApplication(current);

                        FilingDocumentEvent filingDocumentEvent = new FilingDocumentEvent();
                        filingDocumentEvent.setEventDescription("Issuance Of Allowance");

                        filingDocumentEvent.setDocumentType("XML");
                        Date date = new Date();
                        filingDocumentEvent.setEventDate(date);

                        current.addFilingDocumentEvent(filingDocumentEvent);





                        // create document event

                        //  also need to update dashboard to display notice of allowances
                        //  the requires action here is mark in use declaration



                    }



                }
                else {
                    System.out.println("Filing is not Submitted or is still in black out period.");
                }
            }

        }

    }


    public void checkOfficeActionPeriod2(){
        System.out.println("checking Office Action period 2 status for filings : "+durationToReviveWithClaim);
    }


    public long getBlackOutPeriodDuration() {
        return blackOutPeriodDuration;
    }

    public void setBlackOutPeriodDuration(long blackOutPeriodDuration) {
        this.blackOutPeriodDuration = blackOutPeriodDuration;
    }

    public long getFirstOfficeActionDuration() {
        return firstOfficeActionDuration;
    }

    public void setFirstOfficeActionDuration(long firstOfficeActionDuration) {
        this.firstOfficeActionDuration = firstOfficeActionDuration;
    }

    public long getDurationToRevivieWithoutClaim() {
        return durationToRevivieWithoutClaim;
    }

    public void setDurationToRevivieWithoutClaim(long durationToRevivieWithoutClaim) {
        this.durationToRevivieWithoutClaim = durationToRevivieWithoutClaim;
    }

    public long getDurationToReviveWithClaim() {
        return durationToReviveWithClaim;
    }

    public void setDurationToReviveWithClaim(long durationToReviveWithClaim) {
        this.durationToReviveWithClaim = durationToReviveWithClaim;
    }
}
