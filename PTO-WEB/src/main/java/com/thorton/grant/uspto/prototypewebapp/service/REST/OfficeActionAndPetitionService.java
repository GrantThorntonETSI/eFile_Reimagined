package com.thorton.grant.uspto.prototypewebapp.service.REST;

import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Service
public class OfficeActionAndPetitionService extends  BaseRESTapiService{

    public OfficeActionAndPetitionService(ServiceBeanFactory serviceBeanFactory, HostBean hostBean) {
        super(serviceBeanFactory, hostBean);
    }


    @CrossOrigin(origins = {"https://localhost","https://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/petition/update/{pField}/{pValue}/{actionID}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> savePetitionsFileds(@PathVariable String pField , @PathVariable String pValue, @PathVariable String actionID, @PathVariable String appInternalID){

        String appFieldReadable = "";
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);

        if(pField.equals("rec-office-action")){

            if(pValue.equals("yes")){
                baseTrademarkApplication.findPetitionById(actionID).setRecievedOfficeAction(true);
                baseTrademarkApplication.findPetitionById(actionID).setRecievedOfficeActionSet(true);
            }
            else {
                baseTrademarkApplication.findPetitionById(actionID).setRecievedOfficeAction(false);
                baseTrademarkApplication.findPetitionById(actionID).setRecievedOfficeActionSet(true);
            }

            appFieldReadable = "Office action received option";

        }



        if(pField.equals("claim-delay-unintended")){

            if(pValue.equals("yes")){
                baseTrademarkApplication.findPetitionById(actionID).setClaimDelayUnintentional(true);

            }
            else {
                baseTrademarkApplication.findPetitionById(actionID).setClaimDelayUnintentional(false);
            }

            appFieldReadable = "Delay claim option set";

        }



        if(pField.equals("pet-signature-method")){

            baseTrademarkApplication.findPetitionById(actionID).setPetitionSignatureMethod(pValue);


            appFieldReadable = "Petition signature method";
        }


        if(pField.equals("pet-signature-name")){

            baseTrademarkApplication.findPetitionById(actionID).setPetitionSignatoryName(pValue);


            appFieldReadable = "Petition signatory name";
        }


        if(pField.equals("pet-signature-phone")){

            baseTrademarkApplication.findPetitionById(actionID).setPetitionSignatoryPhone(pValue);


            appFieldReadable = "Petition signatory phone number";
        }

        if(pField.equals("pet-signature-position")){

            baseTrademarkApplication.findPetitionById(actionID).setPetitionSinatoryPosition(pValue);


            appFieldReadable = "Petition signatory position";
        }


        if(pField.equals("response-text")){

            baseTrademarkApplication.findPetitionById(actionID).setResponseText(pValue);


            appFieldReadable = "Response message";
        }


        if(pField.equals("response-signature-method")){

            baseTrademarkApplication.findPetitionById(actionID).setResponseSignatureMethod(pValue);


            appFieldReadable = "Response signature method";
        }

        if(pField.equals("response-signature-name")){

            baseTrademarkApplication.findPetitionById(actionID).setResponseSignatoryName(pValue);


            appFieldReadable = "Response signatory name";
        }

        if(pField.equals("response-signature-phone")){

            baseTrademarkApplication.findPetitionById(actionID).setResponseSignatoryPhone(pValue);


            appFieldReadable = "Response signatory phone number";
        }

        if(pField.equals("response-signature-position")){

            baseTrademarkApplication.findPetitionById(actionID).setResponseSignatoryPosition(pValue);


            appFieldReadable = "Response signatory position";
        }

        if(pField.equals("avoid-delay")){

            if(pValue.equals("yes")){

                baseTrademarkApplication.findPetitionById(actionID).setWantToFileResponse(true);
                baseTrademarkApplication.findPetitionById(actionID).setWantToFileResponseSet(true);
            }
            else{
                baseTrademarkApplication.findPetitionById(actionID).setWantToFileResponse(false);
                baseTrademarkApplication.findPetitionById(actionID).setWantToFileResponseSet(true);

            }



            appFieldReadable = "Avoid delay option";
        }


        baseTradeMarkApplicationService.save(baseTrademarkApplication);
        String responseMsg = appFieldReadable+" has been saved";

        return buildResponseEnity("200", responseMsg);
    }


    @CrossOrigin(origins = {"https://localhost","https://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/petition/update/{pField}/{pValue}/{dateDisplay}/{actionID}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> savePetitionSignatures(@PathVariable String pField , @PathVariable String pValue, @PathVariable String dateDisplay, @PathVariable String actionID, @PathVariable String appInternalID){
        String appFieldReadable = "";


        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);

        if(pField.equals("pet-signature-text")){

            baseTrademarkApplication.findPetitionById(actionID).setPetitionSignature(pValue);
            baseTrademarkApplication.findPetitionById(actionID).setPetitoinDateSignedDisplay(dateDisplay);

            appFieldReadable = "Petition signature";

        }

        if(pField.equals("response-signature-text")){

            baseTrademarkApplication.findPetitionById(actionID).setResponseSignature(pValue);
            baseTrademarkApplication.findPetitionById(actionID).setResponseDateSignedDisplay(dateDisplay);

            appFieldReadable = "Response signature";

        }



        String responseMsg = appFieldReadable+" has been saved";
        return buildResponseEnity("200", responseMsg);
    }




    // Office Action REST API service


    @CrossOrigin(origins = {"https://localhost","https://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/OfficeAction/response/update/{pField}/{pValue}/{OfficeActionID}/{requiredActionID}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> saveOAResponse(@PathVariable String pField , @PathVariable String pValue, @PathVariable String OfficeActionID, @PathVariable String requiredActionID,@PathVariable String appInternalID){
        String appFieldReadable = "";


        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);

        if(pField.equals("OA-translation-languageType")){

            baseTrademarkApplication.findOfficeActionById(OfficeActionID).findRequiredActionById(requiredActionID).setTranslationTextLanguage(pValue);
            baseTrademarkApplication.getTradeMark().setForeignLanguageType_translation(pValue);

            // check if translation required action is completed
            if(baseTrademarkApplication.getTradeMark().getForeignLanguageTranslationUSText() != null && baseTrademarkApplication.getTradeMark().getForeignLanguageTranslationOriginalText() != null && baseTrademarkApplication.getTradeMark().getForeignLanguageType_translation() != null){
                baseTrademarkApplication.findOfficeActionById(OfficeActionID).findRequiredActionById(requiredActionID).setRequiredActionCompleted(true);
            }


            appFieldReadable = "Language Type";

        }

        if(pField.equals("OA-translation-foreign-text")){

            baseTrademarkApplication.findOfficeActionById(OfficeActionID).findRequiredActionById(requiredActionID).setTranslationTextForeign(pValue);
            baseTrademarkApplication.getTradeMark().setForeignLanguageTranslationOriginalText(pValue);
            // check if translation required action is completed
            if(baseTrademarkApplication.getTradeMark().getForeignLanguageTranslationUSText() != null && baseTrademarkApplication.getTradeMark().getForeignLanguageTranslationOriginalText() != null && baseTrademarkApplication.getTradeMark().getForeignLanguageType_translation() != null){
                baseTrademarkApplication.findOfficeActionById(OfficeActionID).findRequiredActionById(requiredActionID).setRequiredActionCompleted(true);
            }

            appFieldReadable = "Foreign Text";

        }

        if(pField.equals("OA-translation-us-text")){

            baseTrademarkApplication.findOfficeActionById(OfficeActionID).findRequiredActionById(requiredActionID).setTranslationTextEnglish(pValue);
            baseTrademarkApplication.getTradeMark().setForeignLanguageTranslationUSText(pValue);
            // check if translation required action is completed
            if(baseTrademarkApplication.getTradeMark().getForeignLanguageTranslationUSText() != null && baseTrademarkApplication.getTradeMark().getForeignLanguageTranslationOriginalText() != null && baseTrademarkApplication.getTradeMark().getForeignLanguageType_translation() != null){
                baseTrademarkApplication.findOfficeActionById(OfficeActionID).findRequiredActionById(requiredActionID).setRequiredActionCompleted(true);
            }

            appFieldReadable = "English Text";

        }



        if(pField.equals("OA-mark-disclaimer")){

            baseTrademarkApplication.findOfficeActionById(OfficeActionID).findRequiredActionById(requiredActionID).setDisclaimerText(pValue);
            baseTrademarkApplication.getTradeMark().setDisclaimerDeclaration(pValue);

            appFieldReadable = "Mark Disclaimer";


            //since this is the only field we can mark this complete
            baseTrademarkApplication.findOfficeActionById(OfficeActionID).findRequiredActionById(requiredActionID).setRequiredActionCompleted(true);

        }





        if(pField.equals("OA-representation-type")){

            if(pValue.equals("unrepresented")){
                baseTrademarkApplication.findOfficeActionById(OfficeActionID).setUnrepresentedOwner(true);
                baseTrademarkApplication.findOfficeActionById(OfficeActionID).setAuthorizedUSattorney(false);
                baseTrademarkApplication.findOfficeActionById(OfficeActionID).setAuthorizedCAattorney(false);
                baseTrademarkApplication.findOfficeActionById(OfficeActionID).setAutorizationComplete(true);

            }
            if(pValue.equals("us")){
                baseTrademarkApplication.findOfficeActionById(OfficeActionID).setUnrepresentedOwner(false);
                baseTrademarkApplication.findOfficeActionById(OfficeActionID).setAuthorizedUSattorney(true);
                baseTrademarkApplication.findOfficeActionById(OfficeActionID).setAuthorizedCAattorney(false);
                baseTrademarkApplication.findOfficeActionById(OfficeActionID).setAutorizationComplete(true);

            }
            if(pValue.equals("ca")){
                baseTrademarkApplication.findOfficeActionById(OfficeActionID).setUnrepresentedOwner(false);
                baseTrademarkApplication.findOfficeActionById(OfficeActionID).setAuthorizedUSattorney(false);
                baseTrademarkApplication.findOfficeActionById(OfficeActionID).setAuthorizedCAattorney(true);
                baseTrademarkApplication.findOfficeActionById(OfficeActionID).setAutorizationComplete(true);

            }

            appFieldReadable = "Office Action signature declaration";


        }

        if(pField.equals("OA-signature-type")){


            if(pValue.equals("direct")){
                baseTrademarkApplication.findOfficeActionById(OfficeActionID).setOfficeActionSignatureType(pValue);
                baseTrademarkApplication.findOfficeActionById(OfficeActionID).setOfficeActionSignDirect(true);

            }
            appFieldReadable = "Office Action signature type";
        }

        if(pField.equals("OA-signatory-name")){


            baseTrademarkApplication.findOfficeActionById(OfficeActionID).setOfficeActionSignatoryName(pValue);
            appFieldReadable = "Office Action signatory name";
        }


        if(pField.equals("OA-signatory-phone")){


            baseTrademarkApplication.findOfficeActionById(OfficeActionID).setOfficeActionSignatoryPhone(pValue);
            appFieldReadable = "Office Action signatory phone number";
        }

        if(pField.equals("OA-signatory-position")){


            baseTrademarkApplication.findOfficeActionById(OfficeActionID).setOfficeActionSignatoryPosition(pValue);
            appFieldReadable = "Office Action signatory position";
        }







        baseTradeMarkApplicationService.save(baseTrademarkApplication);



        String responseMsg = appFieldReadable+" has been saved";

        String code ="";

        if(baseTrademarkApplication.findOfficeActionById(OfficeActionID).isRequiredActionsCompleted() == true){
            code = "555";
        }
        else {
            code = "200";

        }







        return buildResponseEnity(code, responseMsg);
    }



    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/OfficeAction/signDirect/{OfficeActionID}/{signature}/{date}/{dateDisplay}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> saveOASignature(@PathVariable String OfficeActionID, @PathVariable String signature ,@PathVariable String date, @PathVariable String dateDisplay, @PathVariable String appInternalID){

        //////////////////////////////////////////////////////////
        // retrieve application using passed internal id
        //////////////////////////////////////////////////////////
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);


        baseTrademarkApplication.findOfficeActionById(OfficeActionID).setOfficeActionSignature("/"+signature+"/");
        Long dateInMilli = Long.valueOf(date);

        //DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        Date result = new Date(dateInMilli);

        baseTrademarkApplication.findOfficeActionById(OfficeActionID).setOfficeActionDateSigned(result);
        baseTrademarkApplication.findOfficeActionById(OfficeActionID).setOfficeActionDateSignedDisplay(dateDisplay);




        baseTradeMarkApplicationService.save(baseTrademarkApplication);

        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = "Direct Sign Signature has been saved for the Office Action";
        return buildResponseEnity("200", responseMsg);

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @CrossOrigin(origins = {"https://localhost","https://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/OfficeAction/optional/selected/update/{pField}/{pValue}/{OfficeActionID}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> saveOptionalActionSelection(@PathVariable String pField , @PathVariable String pValue, @PathVariable String OfficeActionID,@PathVariable String appInternalID){



        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);


       // pField will always be  "add" or "remove"
       // pValue will always be the bread crumb value to add to selected list



            if(pField.equals("add")){
                baseTrademarkApplication.findOfficeActionById(OfficeActionID).addOptionalActionSelectedList(pValue);
                if(pValue.equals("attorney")){
                    baseTrademarkApplication.findOfficeActionById(OfficeActionID).setAttorneyOptional(true);
                }

                if(pValue.equals("owner")){
                    baseTrademarkApplication.findOfficeActionById(OfficeActionID).setOwnerOptional(true);
                }
                if(pValue.equals("mark")){
                   baseTrademarkApplication.findOfficeActionById(OfficeActionID).setMarkOptional(true);
                }

                if(pValue.equals("gs")){
                    baseTrademarkApplication.findOfficeActionById(OfficeActionID).setFbOptional(true);
                }
                if(pValue.equals("additional")){
                    baseTrademarkApplication.findOfficeActionById(OfficeActionID).setAdditionalOptional(true);
                }
            }
            else {

                baseTrademarkApplication.findOfficeActionById(OfficeActionID).removeOptionalActionSelectedList(pValue);

                if(pValue.equals("attorney")){
                    baseTrademarkApplication.findOfficeActionById(OfficeActionID).setAttorneyOptional(false);
                }

                if(pValue.equals("owner")){
                    baseTrademarkApplication.findOfficeActionById(OfficeActionID).setOwnerOptional(false);
                }
                if(pValue.equals("mark")){
                    baseTrademarkApplication.findOfficeActionById(OfficeActionID).setMarkOptional(false);
                }

                if(pValue.equals("gs")){
                    baseTrademarkApplication.findOfficeActionById(OfficeActionID).setFbOptional(false);
                }
                if(pValue.equals("additional")){
                    baseTrademarkApplication.findOfficeActionById(OfficeActionID).setAdditionalOptional(false);
                }

            }


            baseTradeMarkApplicationService.save(baseTrademarkApplication);

        String responseMsg = pValue+" was "+pField+"ed from optional actions.";


        return buildResponseEnity("200", responseMsg);
    }



















}
