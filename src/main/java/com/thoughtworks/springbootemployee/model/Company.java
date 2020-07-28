package com.thoughtworks.springbootemployee.model;

import java.util.List;

public class Company {

    private int companyID;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    private List<Employee> employees;

    public Company(int companyID,List<Employee> employees) {
        this.companyID = companyID;
        this.employees = employees;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

}
