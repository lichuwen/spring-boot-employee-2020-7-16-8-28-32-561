package com.thoughtworks.springbootemployee.model;

public class Employee {
    private int employeeID;

    public Employee(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
}
