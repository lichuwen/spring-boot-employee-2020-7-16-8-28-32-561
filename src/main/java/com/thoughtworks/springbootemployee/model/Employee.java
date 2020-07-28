package com.thoughtworks.springbootemployee.model;

public class Employee {
    private int employeeId;
    private String name;

    public Employee() {
    }

    public Employee(int employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }
}
