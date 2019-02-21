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
public class AdditionalInformationService extends  BaseRESTapiService {

    public AdditionalInformationService(ServiceBeanFactory serviceBeanFactory, HostBean hostBean) {
        super(serviceBeanFactory, hostBean);
    }


    @CrossOrigin(origins = {"https://localhost","https://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/additionalInfo/update/uncommonType/{fieldName}/{fieldValue}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationRegistar(@PathVariable String fieldName , @PathVariable String fieldValue, @PathVariable String appInternalID){

        String appFieldReadable = "Register Type";
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);

        if(fieldName.equals("ai-supplemental-reg")){
            if(fieldValue.equals("yes")){
                baseTrademarkApplication.setSupplementalRegister(true);
                baseTrademarkApplication.setPrincipalRegister(false);
                baseTrademarkApplication.setRegisterTypeSet(true);
                baseTrademarkApplication.setBaseFee(275);
            }
        }


        if(fieldName.equals("ai-principal-reg")){
            if(fieldValue.equals("yes")){
                baseTrademarkApplication.setSupplementalRegister(false);
                baseTrademarkApplication.setPrincipalRegister(true);
                baseTrademarkApplication.setRegisterTypeSet(true);
                baseTrademarkApplication.setBaseFee(225);
            }
        }

        if(fieldName.equals("ai-register-type-set")){
            if(fieldValue.equals("yes")) {

                baseTrademarkApplication.setRegisterTypeSet(true);
            }
            else {
                baseTrademarkApplication.setRegisterTypeSet(false);
            }
        }

        if(fieldName.equals("ai-distinctivenss-claim")){
            if(fieldValue.equals("yes")) {

                baseTrademarkApplication.setClaimDistinctiveness(true);
            }
            else {
                baseTrademarkApplication.setClaimDistinctiveness(false);
            }

            appFieldReadable = "2f Claim ";
        }



        String responseMsg = "{{server-message:"+appFieldReadable+" has been saved}";

        // new return message structure

        //  return buildResponseEnity("200", "{image-url:" +filePath+"}");
        // {server-msg:xxxxx},{fee-display-html:xxxx},{total-class-html:xxxxx},{total-extra-class-html:xxxx},{extra-class-fee-info-html:xxxxxx},{extra-class-fee-calc-html},{basic-fee-calc-html:xxxxx},{fee-total-html}
        responseMsg+=",{fee-display-html:"+baseTrademarkApplication.getTotalFeeString()+"}"+",{total-class-html:"+baseTrademarkApplication.getTotalNumberOfclasses()+"}"+",{total-extra-class-html:"+baseTrademarkApplication.getNumberOfExtraClasses()+"}"+",{basic-fee-calc-html:"+baseTrademarkApplication.getBasicFeeCalculationString()+"}"+",{extra-class-fee-calc-html:"+baseTrademarkApplication.getExtraFeeCalculationString()+"}}";
        //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
        return buildResponseEnity("200", responseMsg);



    }


    @CrossOrigin(origins = {"https://localhost","https://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/additionalInfo/update/{fieldName}/{fieldValue}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationFields(@PathVariable String fieldName , @PathVariable String fieldValue, @PathVariable String appInternalID){


        if(verifyValidUserSession("xxx") == false){

            String responseMsg = fieldName+" has not been saved. invalid user session.";
            return buildResponseEnity("404", responseMsg );

        }

        // retrieve application using passed internal id
        //BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);
        String appFieldReadable = "";
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);


        if(fieldName.equals("ai-misc-info-opt")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

                baseTrademarkApplication.setProvideMiscInfo(true);
                baseTrademarkApplication.setProvideMiscInfoFlagSet(true);

            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setProvideMiscInfo(false);
                baseTrademarkApplication.setProvideMiscInfoFlagSet(true);

            }


            appFieldReadable = "Miscellaneous Info option";


        }



        if(fieldName.equals("ai-misc-statement")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.setMiscInformation(fieldValue);
            appFieldReadable = "Miscellaneous Statement";

        }

        if(fieldName.equals("ai-concurrent-use")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

                baseTrademarkApplication.setConcurrentUse(true);
                baseTrademarkApplication.setDeclarationConcurrentUser(true);
            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setConcurrentUse(false);
                baseTrademarkApplication.setDeclarationConcurrentUser(false);

            }
            baseTrademarkApplication.setDeclarationConcurrentUserSet(true);

            appFieldReadable = "Concurrent Use Declaration";


        }



        if(fieldName.equals("ai-concurrent-type-court")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

                baseTrademarkApplication.setConcurrentTypeCourtDecree(true);
                baseTrademarkApplication.setConcurrentTypeSet(true);
            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setConcurrentTypeCourtDecree(false);
                if(baseTrademarkApplication.isConcurrentTypeEarlierFirstUse() == false && baseTrademarkApplication.isConcurrentTypePriorDecision() == false && baseTrademarkApplication.isConcurrentTypeWrittenConsent() == false){
                    baseTrademarkApplication.setConcurrentTypeSet(false);
                }


            }

            appFieldReadable = "Concurrent Use Type ";


        }

        if(fieldName.equals("ai-concurrent-type-prior")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

                baseTrademarkApplication.setConcurrentTypePriorDecision(true);
                baseTrademarkApplication.setConcurrentTypeSet(true);
            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setConcurrentTypePriorDecision(false);
                if(baseTrademarkApplication.isConcurrentTypeEarlierFirstUse() == false && baseTrademarkApplication.isConcurrentTypeCourtDecree() == false && baseTrademarkApplication.isConcurrentTypeWrittenConsent() == false){
                    baseTrademarkApplication.setConcurrentTypeSet(false);
                }


            }

            appFieldReadable = "Concurrent Use Type ";


        }

        if(fieldName.equals("ai-concurrent-type-written")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

                baseTrademarkApplication.setConcurrentTypeWrittenConsent(true);
                baseTrademarkApplication.setConcurrentTypeSet(true);
            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setConcurrentTypeWrittenConsent(false);
                if(baseTrademarkApplication.isConcurrentTypeEarlierFirstUse() == false && baseTrademarkApplication.isConcurrentTypePriorDecision() == false && baseTrademarkApplication.isConcurrentTypeCourtDecree() == false){
                    baseTrademarkApplication.setConcurrentTypeSet(false);
                }


            }

            appFieldReadable = "Concurrent Use Type ";


        }
        if(fieldName.equals("ai-concurrent-type-earlier")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

                baseTrademarkApplication.setConcurrentTypeEarlierFirstUse(true);
                baseTrademarkApplication.setConcurrentTypeSet(true);
            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setConcurrentTypeEarlierFirstUse(false);
                if(baseTrademarkApplication.isConcurrentTypeCourtDecree() == false && baseTrademarkApplication.isConcurrentTypePriorDecision() == false && baseTrademarkApplication.isConcurrentTypeWrittenConsent() == false){
                    baseTrademarkApplication.setConcurrentTypeSet(false);
                }


            }

            appFieldReadable = "Concurrent Use Type ";


        }


        if(fieldName.equals("ai-concurrent-image-desc")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setConcurentEvidenceDescription(fieldValue);
            appFieldReadable = "Concurrent Use Evidence Description ";

        }


        if(fieldName.equals("ai-concurrent-mode")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setModeOfUse(fieldValue);
            appFieldReadable = "Concurrent Use Mode of use";

        }
        if(fieldName.equals("ai-concurrent-geo-commerce")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setGeoAreaMarkInCommerce(fieldValue);
            appFieldReadable = "Concurrent Use Geographic Area in Commerce";

        }


        if(fieldName.equals("ai-concurrent-ttab-num")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setTtabProceedingNumber(fieldValue);
            appFieldReadable = "Concurrent Use TTAB proceeding number";

        }
        if(fieldName.equals("ai-concurrent-reg-num")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setConcurrentUserRegistrationNumber(fieldValue);
            appFieldReadable = "Concurrent User Registration number";

        }

        String responseMsg = appFieldReadable+" has been saved";

        baseTradeMarkApplicationService.save(baseTrademarkApplication);

        //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
        return buildResponseEnity("200", responseMsg);
    }

}
