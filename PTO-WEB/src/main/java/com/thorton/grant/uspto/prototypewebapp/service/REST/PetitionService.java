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
    ResponseEntity<String> updateFilingBasisForGoodsServcices(@PathVariable String pField , @PathVariable String pValue, @PathVariable String actionID, @PathVariable String appInternalID){

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









        baseTradeMarkApplicationService.save(baseTrademarkApplication);
        String responseMsg = appFieldReadable+" has been saved";

        return buildResponseEnity("200", responseMsg);
    }

}
