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
                baseTrademarkApplication.setConcurrentTypeSet(false);

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
                baseTrademarkApplication.setConcurrentTypeSet(false);

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
                baseTrademarkApplication.setConcurrentTypeSet(false);

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
                baseTrademarkApplication.setConcurrentTypeSet(false);

            }

            appFieldReadable = "Concurrent Use Type ";


        }
        String responseMsg = appFieldReadable+" has been saved";

        baseTradeMarkApplicationService.save(baseTrademarkApplication);

        //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
        return buildResponseEnity("200", responseMsg);
    }

}
