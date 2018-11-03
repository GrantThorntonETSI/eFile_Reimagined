package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants;


import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.UserPersonalData;

import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.Set;

public class Contact  extends UserPersonalData {

    private String companyName;
    private Date lastContactDate;
    private String preferredContactMethod;

    ////////////////////////////////////////////////////////////////////////////////////////
    // tradeMarkApplication is the owning object, Contact is the subordinate object here
    ////////////////////////////////////////////////////////////////////////////////////////
    @ManyToMany
    Set<BaseTrademarkApplication> applications;


}
