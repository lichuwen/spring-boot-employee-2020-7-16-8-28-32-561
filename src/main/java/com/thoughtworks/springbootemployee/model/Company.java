package com.thoughtworks.springbootemployee.model;

import java.util.List;

public class Company {

    private int companyID;
    private int employeesNumber;
    List<Employee> employees;

    public Company() {
    }

    public Company(int companyID, int employeesNumber, List<Employee> employees) {
        this.companyID = companyID;
        this.employeesNumber = employees.size();
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public int getCompanyID() {
        return companyID;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
