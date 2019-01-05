package com.thorton.grant.uspto.prototypewebapp.service.REST;

import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<String> updateGoods_Services(@PathVariable String gsField , @PathVariable String gsValue, @PathVariable String appInternalID){

       String appFieldReadable = "";


        String responseMsg = appFieldReadable+" has been saved.";

        //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
        return buildResponseEnity("200", responseMsg);
    }
}
