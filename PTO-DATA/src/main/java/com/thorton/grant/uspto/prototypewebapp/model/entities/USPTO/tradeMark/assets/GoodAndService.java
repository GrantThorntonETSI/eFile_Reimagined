package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class GoodAndService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String classNumber;

    private String classDescription;

    private String internalID;

    //filing basis fields
    private boolean markInUse;
    private boolean markInUseSet;

    private Date firstGSDate;
    private Date firstCommerceDate;


    private String sampleImagePath;

    private String sampleDescription;

    private boolean GSPendingForignApplicatoin;

    public String getpfaID(){
        return internalID+"pfa";
    }
    private boolean GSForeignApplication;
    public String getpfrID(){
        return internalID+"pfr";
    }
    private boolean GSNotApplicable;
    public String getpnaID(){
        return internalID+"pna";
    }





    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }

    public String getSampleImagePath() {
        return sampleImagePath;
    }

    public void setSampleImagePath(String sampleImagePath) {
        this.sampleImagePath = sampleImagePath;
    }

    public String getSampleDescription() {
        return sampleDescription;
    }

    public void setSampleDescription(String sampleDescription) {
        this.sampleDescription = sampleDescription;
    }

    public String getInternalID() {
        return internalID;
    }

    public void setInternalID(String internalID) {
        this.internalID = internalID;
    }


    public boolean isMarkInUse() {
        return markInUse;
    }

    public void setMarkInUse(boolean markInUse) {
        this.markInUse = markInUse;
    }

    public Date getFirstGSDate() {
        return firstGSDate;
    }

    public void setFirstGSDate(Date firstGSDate) {
        this.firstGSDate = firstGSDate;
    }

    public Date getFirstCommerceDate() {
        return firstCommerceDate;
    }

    public void setFirstCommerceDate(Date firstCommerceDate) {
        this.firstCommerceDate = firstCommerceDate;
    }

    public boolean isGSPendingForignApplicatoin() {
        return GSPendingForignApplicatoin;
    }

    public void setGSPendingForignApplicatoin(boolean GSPendingForignApplicatoin) {
        this.GSPendingForignApplicatoin = GSPendingForignApplicatoin;
    }

    public boolean isGSForeignApplication() {
        return GSForeignApplication;
    }

    public void setGSForeignApplication(boolean GSForeignApplication) {
        this.GSForeignApplication = GSForeignApplication;
    }

    public boolean isGSNotApplicable() {
        return GSNotApplicable;
    }

    public void setGSNotApplicable(boolean GSNotApplicable) {
        this.GSNotApplicable = GSNotApplicable;
    }

    public boolean isMarkInUseSet() {
        return markInUseSet;
    }

    public void setMarkInUseSet(boolean markInUseSet) {
        this.markInUseSet = markInUseSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodAndService that = (GoodAndService) o;
        return id.equals(that.id) &&
                classNumber.equals(that.classNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classNumber);
    }

    @Override
    public String toString() {
        return "GoodAndService{" +
                "id=" + id +
                ", classNumber='" + classNumber + '\'' +
                ", classDescription='" + classDescription + '\'' +
                ", sampleImagePath='" + sampleImagePath + '\'' +
                ", sampleDescription='" + sampleDescription + '\'' +
                '}';
    }
}
