package com.thoughtworks.springbootemployee.model;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    //    @JoinColumn(name = "companyID")
//    private Integer companyId;
    private String name;
    private String gender;

    public Employee() {
    }

    public Employee(int employeeId, String name,String gender) {
        this.employeeId = employeeId;
        this.name = name;
        this.gender = gender;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
