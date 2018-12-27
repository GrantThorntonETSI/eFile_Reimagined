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
public class TradeMarkService extends BaseRESTapiService {

    public TradeMarkService(ServiceBeanFactory serviceBeanFactory, HostBean hostBean) {
        super(serviceBeanFactory, hostBean);
    }

    @CrossOrigin(origins = {"https://localhost","https://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/mark/update/{markField}/{markValue}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationFields(@PathVariable String markField , @PathVariable String markValue, @PathVariable String appInternalID){


        if(verifyValidUserSession("xxx") == false){

            String responseMsg = markField+" has not been saved. invalid user session.";
            return buildResponseEnity("404", responseMsg );

        }

        // retrieve application using passed internal id
        //BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);
        String appFieldReadable = "";
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);

        if(markField.equals("mark-literal")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setMarkLiteral(markValue);
            appFieldReadable = "Mark Literal";

        }

        if(markField.equals("mark-color-claim")){
            // ptoUser.setState(param); // sets state code

            if(markValue.equals("yes")){

                baseTrademarkApplication.getTradeMark().setMarkColorClaim(true);
                baseTrademarkApplication.getTradeMark().setMarkColorClaimBW(false);


            }
            if(markValue.equals("no")){

                baseTrademarkApplication.getTradeMark().setMarkColorClaim(false);

            }

            appFieldReadable = "Color Claim";

        }

        if(markField.equals("mark-accept-BW")){
            // ptoUser.setState(param); // sets state code

           baseTrademarkApplication.getTradeMark().setMarkColorClaimBW(true);
            appFieldReadable = "Accept BW Drawing ";

        }


        if(markField.equals("mark-description")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setMarkDescription(markValue);
            appFieldReadable = "Mark Description";

        }

        if(markField.equals("mark-translation-wording-foreignText")){
            // ptoUser.setState(param); // sets state code

            if(markValue.equals("yes")){
                baseTrademarkApplication.getTradeMark().setForeignLanguageTranslationWording(true);

            }
            if(markValue.equals("no")){
                baseTrademarkApplication.getTradeMark().setForeignLanguageTranslationWording(false);

            }

            appFieldReadable = "Mark Translation Foreign Wording";

        }

        if(markField.equals("mark-BW-description")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setMarkBWDescription(markValue);
            appFieldReadable = "Mark BW Description";

        }

        if(markField.equals("mark-color-list")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setMarkColors(markValue);
            appFieldReadable = "Mark Colors ";

        }

        if(markField.equals("mark-color-description")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setMarkColorDescription(markValue);
            appFieldReadable = "Mark Colors Description";

        }





        baseTradeMarkApplicationService.save(baseTrademarkApplication);

        String responseMsg = appFieldReadable+" has been saved.";

        //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
        return buildResponseEnity("200", responseMsg);
    }




}
