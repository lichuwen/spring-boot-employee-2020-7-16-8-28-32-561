package com.thoughtworks.springbootemployee.model;

import java.util.List;

public class Company {

    private String companyName;
    private int employeesNumber;
    List<Employee> employees;

    public Company(String companyName, int employeesNumber, List<Employee> employees) {
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
        this.employees = employees;
    }
}
