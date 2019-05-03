package com.thorton.grant.uspto.prototypewebapp.service.REST;

import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Service
public class PetitionService  extends  BaseRESTapiService{

    public PetitionService(ServiceBeanFactory serviceBeanFactory, HostBean hostBean) {
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
                baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setRecievedOfficeAction(true);
                baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setRecievedOfficeActionSet(true);
            }
            else {
                baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setRecievedOfficeAction(false);
                baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setRecievedOfficeActionSet(true);
            }

            appFieldReadable = "Office action received option";

        }



        if(pField.equals("claim-delay-unintended")){

            if(pValue.equals("yes")){
                baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setClaimDelayUnintentional(true);

            }
            else {
                baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setClaimDelayUnintentional(false);
            }

            appFieldReadable = "Delay claim option set";

        }



        if(pField.equals("pet-signature-method")){

            baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setPetitionSignatureMethod(pValue);


            appFieldReadable = "Petition signature method";
        }


        if(pField.equals("pet-signature-name")){

            baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setPetitionSignatoryName(pValue);


            appFieldReadable = "Petition signatory name";
        }


        if(pField.equals("pet-signature-phone")){

            baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setPetitionSignatoryPhone(pValue);


            appFieldReadable = "Petition signatory phone number";
        }

        if(pField.equals("pet-signature-position")){

            baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setPetitionSinatoryPosition(pValue);


            appFieldReadable = "Petition signatory position";
        }


        if(pField.equals("response-text")){

            baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setResponseText(pValue);


            appFieldReadable = "Response message";
        }


        if(pField.equals("response-signature-method")){

            baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setResponseSignatureMethod(pValue);


            appFieldReadable = "Response signature method";
        }

        if(pField.equals("response-signature-name")){

            baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setResponseSignatoryName(pValue);


            appFieldReadable = "Response signatory name";
        }

        if(pField.equals("response-signature-phone")){

            baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setResponseSignatoryPhone(pValue);


            appFieldReadable = "Response signatory phone number";
        }

        if(pField.equals("response-signature-position")){

            baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setResponseSignatoryPosition(pValue);


            appFieldReadable = "Response signatory position";
        }

        if(pField.equals("avoid-delay")){

            if(pValue.equals("yes")){

                baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setWantToFileResponse(true);
                baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setWantToFileResponseSet(true);
            }
            else{
                baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setWantToFileResponse(false);
                baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setWantToFileResponseSet(true);

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

            baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setPetitionSignature(pValue);
            baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setPetitoinDateSignedDisplay(dateDisplay);

            appFieldReadable = "Petition signature";

        }

        if(pField.equals("response-signature-text")){

            baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setResponseSignature(pValue);
            baseTrademarkApplication.findOfficeActionById(actionID).getPetition().setResponseDateSignedDisplay(dateDisplay);

            appFieldReadable = "Response signature";

        }



        String responseMsg = appFieldReadable+" has been saved";
        return buildResponseEnity("200", responseMsg);
    }

}
