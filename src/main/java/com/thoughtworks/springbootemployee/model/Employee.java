package com.thoughtworks.springbootemployee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    private String name;
    private String gender;

    public Employee() {
    }

    public Employee(int employeeId, String name,String gender) {
        this.employeeId = employeeId;
        this.name = name;
        this.gender = gender;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }
}
