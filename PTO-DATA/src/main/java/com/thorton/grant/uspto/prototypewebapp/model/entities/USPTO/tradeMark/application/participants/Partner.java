package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Partner {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  private String partnerName;
  private String partnerEntityType;

  private String partnerFirstName;

  private String partnerLastName;
  private String partnerSuffix;

  private String partnerCitizenship;

  private String organizationCountry;

  private String getOrganizationState;


    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerEntityType() {
        return partnerEntityType;
    }

    public void setPartnerEntityType(String partnerEntityType) {
        this.partnerEntityType = partnerEntityType;
    }

    public String getPartnerFirstName() {
        return partnerFirstName;
    }

    public void setPartnerFirstName(String partnerFirstName) {
        this.partnerFirstName = partnerFirstName;
    }

    public String getPartnerLastName() {
        return partnerLastName;
    }

    public void setPartnerLastName(String partnerLastName) {
        this.partnerLastName = partnerLastName;
    }

    public String getPartnerSuffix() {
        return partnerSuffix;
    }

    public void setPartnerSuffix(String partnerSuffix) {
        this.partnerSuffix = partnerSuffix;
    }

    public String getPartnerCitizenship() {
        return partnerCitizenship;
    }

    public void setPartnerCitizenship(String partnerCitizenship) {
        this.partnerCitizenship = partnerCitizenship;
    }

    public String getOrganizationCountry() {
        return organizationCountry;
    }

    public void setOrganizationCountry(String organizationCountry) {
        this.organizationCountry = organizationCountry;
    }

    public String getGetOrganizationState() {
        return getOrganizationState;
    }

    public void setGetOrganizationState(String getOrganizationState) {
        this.getOrganizationState = getOrganizationState;
    }



}
