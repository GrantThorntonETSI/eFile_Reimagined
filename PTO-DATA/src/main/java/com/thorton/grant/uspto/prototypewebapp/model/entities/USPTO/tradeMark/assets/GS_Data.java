package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets;

public class GS_Data {


    private boolean activeGS;

    private String gsDisplayText;


    public boolean isActiveGS() {
        return activeGS;
    }

    public void setActiveGS(boolean activeGS) {
        this.activeGS = activeGS;
    }

    public String getGsDisplayText() {
        return gsDisplayText;
    }

    public void setGsDisplayText(String gsDisplayText) {
        this.gsDisplayText = gsDisplayText;
    }
}
