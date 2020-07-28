package com.thoughtworks.springbootemployee.model;

public class Company {
    public Company(int companyID) {
        this.companyID = companyID;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    private int companyID;
}
