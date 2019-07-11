package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions;

public class BreadCrumb {


    private String description;
    private boolean activeBreadCrumb;
    private String  url;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActiveBreadCrumb() {
        return activeBreadCrumb;
    }

    public void setActiveBreadCrumb(boolean activeBreadCrumb) {
        this.activeBreadCrumb = activeBreadCrumb;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
