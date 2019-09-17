package com.thorton.grant.uspto.prototypewebapp.config.bootstrap;

import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
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
import com.thorton.grant.uspto.prototypewebapp.service.mail.gmail.GmailJavaMailSenderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
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


    private  final HostBean hostBean;
    private final ApplicationContext appContext;


    private final GmailJavaMailSenderService mailSender;




    private static long counter = 9000000;

    public FilingStatusUpdateTask(ServiceBeanFactory serviceBeanFactory, ApplicationContext appContext, GmailJavaMailSenderService mailSender) {
        this.serviceBeanFactory = serviceBeanFactory;
        this.appContext = appContext;
        this.mailSender = mailSender;

        this.hostBean = (HostBean) appContext.getBean(HostBean.class);
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

        checkAcceptedFilings();

        checkNOAPeriod();
        checkFilingExtensions();



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
                      officeActions.setParentRegistrationNumber(current.getRegistrationID());
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

                        if(current3.isOfficeActionCompleted() == false && current.getFilingStatus().contains("Abandoned") == false){

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
                            petition.setParentRegistrationNumber(current.getRegistrationID());

                            petition.setActionID(String.valueOf(current3.getInternalID()));

                            long dueDate = new Date().getTime()+current.getBlackOutPeriod()+current.getOfficeActionResponsePeriod()+current.getPetitionPeriod();
                            petition.setDueDate(new Date(dueDate));


                            current.setFilingStatus("Abandoned - Failure to Respond or Late Response");
                            petition.setOfficeActionCode("Abandoned - Failure to Respond or Late Response");
                            petition.setPetitionTitle("Failure to Respond Timely to Office Action");

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


                }
            }
            else{
                if(current.getFilingStatus().equals("TEAS RF New Application") || current.getFilingStatus().equals("New Application") ){
                    if((current.getApplicationFilingDate().getTime() + current.getBlackOutPeriod() + current.getOfficeActionResponsePeriod() ) < new Date().getTime()){
                         boolean acceptedFiling = true;

                        for(Iterator<OfficeActions> iter4 = current.getOfficeActions().iterator(); iter4.hasNext(); ) {
                            OfficeActions current4 = iter4.next();
                            if(current4.isActiveAction() && current4.getOfficeActionCode().equals("global default action") == false){
                                if(current4.isOfficeActionCompleted() == true){
                                    // skip examiner review period
                                    // filing is accepted
                                    // change filing status
                                    // create filing document event

                                    current4.setActiveAction(false);



                                }
                                else {
                                    acceptedFiling = false;

                                    System.out.println("office action is not completed");
                                }

                            }


                        }

                        if(acceptedFiling == true){
                            current.setFilingStatus("Accepted Filing");
                            current.setApplicationAcceptedDate(new Date());

                            // create accepted filing date




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
                        // if there are no office actions ..it is also compelete

                    }
                    // check for accepted filings
                }












            }

        }

    }


    public void checkAcceptedFilings(){

        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();


        for(Iterator<BaseTrademarkApplication> iter = baseTradeMarkApplicationService.findAll().iterator(); iter.hasNext(); ) {

            BaseTrademarkApplication current = iter.next();

            if(current.getApplicationAcceptedDate() != null){
                if(current.getApplicationAcceptedDate().getTime()+current.getBlackOutPeriod() < new Date().getTime()){
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
                            noa.setParentRegistrationNumber(current.getRegistrationID());
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


                            FilingDocumentEvent filingDocumentEvent = new FilingDocumentEvent();
                            filingDocumentEvent.setEventDescription("Issuance Of Allowance");

                            filingDocumentEvent.setDocumentType("XML");
                            Date date = new Date();
                            filingDocumentEvent.setEventDate(date);

                            current.addFilingDocumentEvent(filingDocumentEvent);

                            noa.setActionDate(date);

                            current.addOfficeAction(noa);
                            noa.setTrademarkApplication(current);


                            // create document event

                            //  also need to update dashboard to display notice of allowances
                            //  the requires action here is mark in use declaration
                            baseTradeMarkApplicationService.save(current);


                        }
                        else{
                          // filing can now be registered
                          // create registration number for filing
                          // start this number at 9000000
                            current.setFilingStatus("Registered");
                            current.setInternalFilingStatus("Registered");
                            counter++;
                            current.setRegistrationID(String.valueOf(counter));

                            FilingDocumentEvent filingDocumentEvent = new FilingDocumentEvent();
                            filingDocumentEvent.setEventDescription("Registered Tradmark");

                            filingDocumentEvent.setDocumentType("XML");
                            Date date = new Date();
                            filingDocumentEvent.setEventDate(date);

                            current.addFilingDocumentEvent(filingDocumentEvent);

                            current.setApplicationRegisteredDate(date);
                            current.setApplicationRegistrationRenewDate(date);

                            baseTradeMarkApplicationService.save(current);



                        }



                    }
                }

            }


        }

    }

    public void checkNOAPeriod(){
        System.out.println("checking Office Action period 2 status for filings : "+durationToReviveWithClaim);

        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();


        for(Iterator<BaseTrademarkApplication> iter = baseTradeMarkApplicationService.findAll().iterator(); iter.hasNext(); ) {

            BaseTrademarkApplication current = iter.next();

            if( current.getApplicationAcceptedDate() != null && current.getFilingStatus().equals("Issuance Of Allowance")){
                // check if allowance has expired ...use accepted date + issuance of allowance period

                if((current.getApplicationAcceptedDate().getTime() + current.getBlackOutPeriod()+ current.getIssuranceOfAllowancePeriod()) < new Date().getTime()){
                   // issuance of allowance has expired
                   // set filing to abandoment
                   // create document record
                   // create petition to revive
                    for(Iterator<OfficeActions> iter3 = current.getOfficeActions().iterator(); iter3.hasNext(); ) {
                        OfficeActions current3 = iter3.next();

                        if (current3.isOfficeActionCompleted() == false && current.getFilingStatus().contains("Abandoned") == false) {
                            // this should flag the NOA which is also an office action with required action of SOU

                            Petition petition = new Petition();
                            petition.setParentMarkImagePath(current.getTradeMark().getTrademarkImagePath());
                            petition.setStandardCharacterMark(current.isStandardTextMark());
                            petition.setStandardCharacterText(current.getTradeMark().getTrademarkStandardCharacterText());
                            petition.setParentMarkOwnerName(current.getPrimaryOwner().getOwnerDisplayname());
                            petition.setParentSerialNumber(current.getTrademarkName());
                            petition.setParentRegistrationNumber(current.getRegistrationID());

                            petition.setActionID(String.valueOf(current3.getInternalID()));

                            long dueDate = new Date().getTime()+current.getBlackOutPeriod()+current.getPetitionPeriod();
                            petition.setDueDate(new Date(dueDate));
                            petition.setPetitionTitle("Failure to File Timely Statement of Use or Extension Request");

                            petition.setActionDate(current3.getActionDate());



                            current.setFilingStatus("Abandoned - Failure to File timely SOU or Extension Request");
                            petition.setOfficeActionCode("Abandoned - Failure to File timely SOU or Extension Request");
                            petition.setType("noa");

                            petition.setActivePetition(true);
                            current.addPetition(petition);
                            petition.setTrademarkApplication(current);
                            current3.setActiveAction(false);

                            FilingDocumentEvent filingDocumentEvent = new FilingDocumentEvent();
                            filingDocumentEvent.setEventDescription("Abandoned - Failure to File timely SOU or Extension Request");

                            filingDocumentEvent.setDocumentType("XML");
                            Date date = new Date();
                            filingDocumentEvent.setEventDate(date);

                            current.addFilingDocumentEvent(filingDocumentEvent);


                            baseTradeMarkApplicationService.save(current);

                        }
                    }


                }
            }
        }
    }


    public void checkFilingExtensions(){
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();


        for(Iterator<BaseTrademarkApplication> iter = baseTradeMarkApplicationService.findAll().iterator(); iter.hasNext(); ) {

            BaseTrademarkApplication current = iter.next();

            if( current.getApplicationRegisteredDate() != null && current.getInternalFilingStatus().equals("Registered")){
                // check if allowance has expired ...use accepted date + issuance of allowance period

                if((current.getApplicationRegistrationRenewDate().getTime() + current.getRecurringFilingExtensionInterval()) < new Date().getTime()){
                    // issuance of allowance has expired
                    // set filing to abandoment
                    // create document record
                    // create petition to revive
                    // create notice of allowance
                    NoticeOfAllowance noa = new NoticeOfAllowance();
                    noa.setParentMarkImagePath(current.getTradeMark().getTrademarkImagePath());
                    noa.setStandardCharacterMark(current.isStandardTextMark());
                    noa.setStandardCharacterText(current.getTradeMark().getTrademarkStandardCharacterText());
                    noa.setParentMarkOwnerName(current.getPrimaryOwner().getOwnerDisplayname());
                    noa.setParentSerialNumber(current.getTrademarkName());
                    noa.setParentRegistrationNumber(current.getRegistrationID());
                    noa.setActiveAction(true);
                    long dueDate = new Date().getTime()+current.getBlackOutPeriod()+current.getOfficeActionResponsePeriod();
                    noa.setDueDate(new Date(dueDate));
                    noa.setActionType("section8");


                    // you have to provide at least one disclaimer
                    RequiredActions requiredActions = new RequiredActions();
                    requiredActions.setRequiredActionType("Use Statement Renew");

                    noa.addRequiredActions(requiredActions);


                    current.setInternalFilingStatus("File Section 8 Declaration");
                    noa.setOfficeActionCode("Registered");


                    // create  an default office action object and attach it to filing


                    FilingDocumentEvent filingDocumentEvent = new FilingDocumentEvent();
                    filingDocumentEvent.setEventDescription("Use Statement Renew");

                    filingDocumentEvent.setDocumentType("XML");
                    Date date = new Date();
                    filingDocumentEvent.setEventDate(date);

                    current.addFilingDocumentEvent(filingDocumentEvent);

                    noa.setActionDate(date);
                    current.addOfficeAction(noa);
                    noa.setTrademarkApplication(current);


                    // set all gs that are active and class in use to and excused none use to false

                    for(Iterator<GoodAndService> iterGS = current.getGoodAndServices().iterator(); iterGS.hasNext(); ) {
                        GoodAndService currentGS = iterGS.next();

                       if(currentGS.isActiveGS()){
                           currentGS.setMarkInUse(false);
                           currentGS.setMarkInUseSet(false);
                           currentGS.setExcusedNoneUse(false);
                           currentGS.setMarkInUseCC(false);
                           currentGS.setMarkInUseAllGSinClass(false);

                       }
                    }

                    // all of the in use status will need to be redeclared on renew



                    baseTradeMarkApplicationService.save(current);

                    String recipientAddress = "";
                    if(current.isAttorneyFiling()){
                       recipientAddress = current.getPrimaryLawyer().getEmail();
                    }
                    else {
                        recipientAddress = current.getPrimaryOwner().getEmail();
                    }

                    mailSender.sendOfficeActionNotice(hostBean.getHost(),recipientAddress);

                    // create document record and associated office action









                }
            }
        }
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
