package com.thorton.grant.uspto.prototypewebapp.config.bootstrap;

import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.OfficeActions;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.Petition;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.RequiredActions;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
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
    private long firstOfficeActionDuration = 5*60*1000;  // 5 mins


    @Value("${uspto.officeaction2b}")
    private long durationToRevivieWithoutClaim = 1*60*1000; //1 minsh

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

            if(current.getApplicationFilingDate() != null && current.getFilingStatus().equals("New Application")){
                // check that date + duration against current time
              if((current.getApplicationFilingDate().getTime() + blackOutPeriodDuration) < new Date().getTime()){

                  System.out.println("Filing has expired from the black out period");

                  //baseTradeMarkApplicationService.save(current);


                  // set relevant office actions
                  OfficeActions officeActions = new OfficeActions();
                  officeActions.setParentMarkImagePath(current.getTradeMark().getTrademarkImagePath());
                  officeActions.setStandardCharacterMark(current.isStandardTextMark());
                  officeActions.setStandardCharacterText(current.getTradeMark().getTrademarkStandardCharacterText());
                  officeActions.setParentMarkOwnerName(current.getPrimaryOwner().getOwnerDisplayname());
                  officeActions.setParentSerialNumber(current.getTrademarkName());
                  officeActions.setActiveAction(true);
                  //officeActions.setOfficeActionCode("Missing transliteration");

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


                  if(current.getTradeMark().getDisclaimerDeclarationList().size() == 0){
                      // you have to provide at least one disclaimer
                      RequiredActions requiredActions = new RequiredActions();
                      requiredActions.setRequiredActionType("Disclaimer Required");

                      officeActions.addRequiredActions(requiredActions);

                  }


                  current.setFilingStatus("Non-Final Action Mailed");
                  officeActions.setOfficeActionCode("Non-Final Action Mailed");


                  // create  an default office action object and attach it to filing


                  current.addOfficeAction(officeActions);
                  officeActions.setTrademarkApplication(current);
                  baseTradeMarkApplicationService.save(current);


              }
              else{
                  System.out.println("filing is still in the black out period");
                  // check for possible office actions

                  // 1. failed to provide declarations and signature



                  // 2. missing transliteration for standard character and design with text


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
                if((current.getApplicationFilingDate().getTime() + blackOutPeriodDuration +firstOfficeActionDuration) < new Date().getTime()){

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


                            current.setFilingStatus("Abandoned - Failure to Respond or Late Response");
                            petition.setOfficeActionCode("Abandoned - Failure to Respond or Late Response");

                            petition.setActivePetition(true);
                            current.addPetition(petition);
                            petition.setTrademarkApplication(current);


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
                    System.out.println("filing is still in respond to office action period");
                }
            }
            else{
                System.out.println("Filing is not Submitted or is still in black out period.");
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
