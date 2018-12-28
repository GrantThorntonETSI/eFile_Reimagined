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

        if(markField.equals("mark-fw-translation")){
            // ptoUser.setState(param); // sets state code

            if(markValue.equals("yes")){
                baseTrademarkApplication.getTradeMark().setForeignLanguageTranslationWording(true);

            }
            if(markValue.equals("no")){
                baseTrademarkApplication.getTradeMark().setForeignLanguageTranslationWording(false);

            }

            appFieldReadable = "Mark Translation Foreign Wording";

        }

        if(markField.equals("mark-fw-languageType")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setForeignLanguageType_translation(markValue);

            appFieldReadable = "Mark Foreign Language Type - Translation";

        }

        if(markField.equals("mark-fw-fwText")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setForeignLanguageTranslationOriginalText(markValue);

            appFieldReadable = "Mark Foreign Language Text - Translation ";

        }
        if(markField.equals("mark-fw-engText")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setForeignLanguageTranslationUSText(markValue);

            appFieldReadable = "Mark Foreign Language English Text - Translation";

        }

        if(markField.equals("mark-fw-transliteration")){
            // ptoUser.setState(param); // sets state code

            if(markValue.equals("yes")){
                baseTrademarkApplication.getTradeMark().setForeignLanguateTransliterationWording(true);

            }
            if(markValue.equals("no")){
                baseTrademarkApplication.getTradeMark().setForeignLanguateTransliterationWording(false);

            }

            appFieldReadable = "Mark Transliteration Foreign Wording";

        }



        if(markField.equals("mark-fw-translit-languageType")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setForeignLanguageType_transliteration(markValue);

            appFieldReadable = "Mark Foreign Language Type - Transliteration";

        }

        if(markField.equals("mark-fw-translit-fwText")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setForeignLanguateTransliterationOriginalText(markValue);

            appFieldReadable = "Mark Foreign Language Text - Transliteration";

        }
        if(markField.equals("mark-fw-translit-engText")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setForeignLanguateTransliterationUSText(markValue);

            appFieldReadable = "Mark Foreign Language English Text - Transliteration";

        }



        if(markField.equals("mark-active-disclaimer")){
            // ptoUser.setState(param); // sets state code

            if(markValue.equals("yes")){
                baseTrademarkApplication.getTradeMark().setActvieDisclaimer(true);

            }
            if(markValue.equals("no")){
                baseTrademarkApplication.getTradeMark().setActvieDisclaimer(false);

            }

            appFieldReadable = "Mark Disclaimer";

        }

        if(markField.equals("mark-disclaimer-declaration")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setDisclaimerDeclaration(markValue);

            appFieldReadable = "Mark Disclaimer Declaration";

        }



        if(markField.equals("mark-prior-registration")){
            // ptoUser.setState(param); // sets state code

            if(markValue.equals("yes")){
                baseTrademarkApplication.getTradeMark().setPriorRegistratoin(true);

            }
            if(markValue.equals("no")){
                baseTrademarkApplication.getTradeMark().setPriorRegistratoin(false);

            }

            appFieldReadable = "Mark Prior U.S Registration";

        }

        if(markField.equals("\"mark-prior-reg-number")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setPriorRegistrationNumber(markValue);

            appFieldReadable = "Mark Prior U.S Registration Number";

        }






        baseTradeMarkApplicationService.save(baseTrademarkApplication);

        String responseMsg = appFieldReadable+" has been saved.";

        //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
        return buildResponseEnity("200", responseMsg);
    }




}
