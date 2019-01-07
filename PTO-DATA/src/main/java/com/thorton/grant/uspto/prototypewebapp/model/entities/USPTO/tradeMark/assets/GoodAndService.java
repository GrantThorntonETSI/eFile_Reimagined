package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class GoodAndService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String classNumber;

    private String classDescription;


    private String sampleImagePath;

    private String sampleDescription;


    private String internalID;


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
