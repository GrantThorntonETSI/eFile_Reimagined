package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.document_events;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "filing_document_events")
public class FilingDocumentEvent extends BaseEntity {


    // need possible reference to event ....this will be generica and it will return the proper link reference for the corresponding event
    @ManyToOne
    private BaseTrademarkApplication trademarkApplication;


    // need a date field
    private Date eventDate;
    // description
    private String eventDescription;


    private String documentType;



    // and a downloadable link
    private String eventDocumentDownloadLink;
    // display link
    private String eventDocumentDisplayLink;



    // this will just a a database object that we set and do get on



    // this object has a many to one relation ship with the filing object

    // i.e each filing will have a list of filing document events


    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDocumentDownloadLink() {
        return eventDocumentDownloadLink;
    }

    public void setEventDocumentDownloadLink(String eventDocumentDownloadLink) {
        this.eventDocumentDownloadLink = eventDocumentDownloadLink;
    }

    public String getEventDocumentDisplayLink() {
        return eventDocumentDisplayLink;
    }

    public void setEventDocumentDisplayLink(String eventDocumentDisplayLink) {
        this.eventDocumentDisplayLink = eventDocumentDisplayLink;
    }

    public String getEventDateDisplay(){

        return eventDate.toString().substring(0, 10);
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
}
