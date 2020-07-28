package com.thoughtworks.springbootemployee.model;

import java.util.List;

public class Company {

    private int companyID;
    private int employeesNumber;
    List<Employee> employees;

    public Company(int companyID, int employeesNumber, List<Employee> employees) {
        this.companyID = companyID;
        this.employeesNumber = employeesNumber;
        this.employees = employees;
    }
}
