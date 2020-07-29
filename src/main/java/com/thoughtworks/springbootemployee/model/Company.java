package com.thoughtworks.springbootemployee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyID;
    private Integer employeesNumber;
    @OneToMany
    @JsonIgnore
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
}
