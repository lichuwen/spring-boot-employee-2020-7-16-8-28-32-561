package com.thoughtworks.springbootemployee.model;

public class Employee {
    private int employeeId;
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
