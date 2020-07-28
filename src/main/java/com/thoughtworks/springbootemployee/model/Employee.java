package com.thoughtworks.springbootemployee.model;

public class Employee {
    private int EmployeeId;
    private String name;

    public Employee() {
    }

    public Employee(int EmployeeId, String name) {
        this.EmployeeId = EmployeeId;
        this.name = name;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public String getName() {
        return name;
    }
}
