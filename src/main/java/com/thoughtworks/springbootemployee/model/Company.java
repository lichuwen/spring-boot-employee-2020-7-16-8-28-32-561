package com.thoughtworks.springbootemployee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyID;
    private Integer employeesNumber;
    private String companyName;

    @OneToMany(cascade = CascadeType.ALL, fetch = EAGER)
    List<Employee> employees;

    public Company() {
    }

    public Company(Integer companyID, Integer employeesNumber, String companyName, List<Employee> employees) {
        this.companyID = companyID;
        this.employeesNumber = employeesNumber;
        this.companyName = companyName;
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public Integer getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public void setEmployeesNumber(Integer employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
