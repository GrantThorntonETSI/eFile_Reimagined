package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user;



import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ManagedContact extends UserPersonalData {

@ManyToOne
private PTOUser contactOwner;


private String contactType;


private String entityName;  // organization name/ owner name /i .e none human name


    public PTOUser getContactOwner() {
        return contactOwner;
    }

    public void setContactOwner(PTOUser contactOwner) {
        this.contactOwner = contactOwner;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }


}
