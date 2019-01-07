package com.thorton.grant.uspto.prototypewebapp.service.REST;

import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.asset.GoodsAndServicesService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.GoodAndService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Service
public class Goods_ServicesService  extends BaseRESTapiService{

    public Goods_ServicesService(ServiceBeanFactory serviceBeanFactory, HostBean hostBean) {
        super(serviceBeanFactory, hostBean);
    }



    @CrossOrigin(origins = {"https://localhost","https://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/GS/update/{gsField}/{gsValue}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateGoods_ServicesSelectOptions(@PathVariable String gsField , @PathVariable String gsValue, @PathVariable String appInternalID){

       String appFieldReadable = "";
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);

        if(gsField.equals("GS-select-Option")){
            // ptoUser.setState(param); // sets state code
            if(gsValue.equals("search")) {
                baseTrademarkApplication.setSearchExistingGSdatabase(true);
            }
            else {
                baseTrademarkApplication.setSearchExistingGSdatabase(false);

            }
            appFieldReadable = "Goods And Services search option";

        }

        baseTradeMarkApplicationService.save(baseTrademarkApplication);
        String responseMsg = appFieldReadable+" has been saved.";

        //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
        return buildResponseEnity("200", responseMsg);
    }



    @CrossOrigin(origins = {"https://localhost","https://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/GS/add/{classNumber}/{classDescription}/{gsID}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplictionGoodsServcis(@PathVariable String classNumber , @PathVariable String classDescription,  @PathVariable String gsID, @PathVariable String appInternalID){

        String appFieldReadable = "";
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);

        if(baseTrademarkApplication.findGSbyInternalID(gsID) != null){

            return buildResponseEnity("444", "Good and Service Already added to the Application.");

        }


        // check if classNumber exists ..  // if the classNumber/category does not exist // create it
        if(baseTrademarkApplication.getGoodsAndSevicesMap().containsKey(classNumber) == false){
            // new key/classNumber
            // we need to create a new category for this class
            HashSet<GoodAndService> goodAndServiceHashSet = new HashSet<>();
            baseTrademarkApplication.getGoodsAndSevicesMap().put(classNumber, goodAndServiceHashSet);

        }


        // create the good and service
        GoodAndService goodAndService = new GoodAndService();
        goodAndService.setClassNumber(classNumber);
        goodAndService.setClassDescription(classDescription);
        goodAndService.setInternalID(gsID);

        baseTrademarkApplication.getGoodsAndSevicesMap().get(classNumber).add(goodAndService);

        appFieldReadable = "Good and Service";

        baseTradeMarkApplicationService.save(baseTrademarkApplication);
        String responseMsg = appFieldReadable+" has been saved.";

        return buildResponseEnity("200", responseMsg);
    }



    @CrossOrigin(origins = {"https://localhost","https://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/GS/remove/{classNumber}/{classDescription}/{gsID}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplictionGoodsServcisRemove(@PathVariable String classNumber , @PathVariable String classDescription, @PathVariable String gsID, @PathVariable String appInternalID){

        String appFieldReadable = "Good and Service";
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);


        if(baseTrademarkApplication.findGSbyInternalID(gsID) == null){

            return buildResponseEnity("444", "Good and Service is not part of the Application.");

        }


        GoodAndService deleteThisGS = baseTrademarkApplication.findGSbyInternalID(gsID);
        baseTrademarkApplication.getGoodsAndSevicesMap().get(classNumber).remove(deleteThisGS);

        baseTradeMarkApplicationService.save(baseTrademarkApplication);



        GoodsAndServicesService goodsAndServicesService = getServiceBeanFactory().getGoodsAndServicesService();
        goodsAndServicesService.delete(deleteThisGS);

        String responseMsg = appFieldReadable+" has been removed.";

        //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
        return buildResponseEnity("200", responseMsg);
    }
}
