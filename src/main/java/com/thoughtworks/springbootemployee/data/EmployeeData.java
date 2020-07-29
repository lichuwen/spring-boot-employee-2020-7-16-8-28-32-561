package com.thoughtworks.springbootemployee.data;

import com.thoughtworks.springbootemployee.model.Employee;

import java.util.List;

public class EmployeeData {
    public EmployeeData(List<Employee> employees) {
        employees.add(new Employee(1, "karen", "male"));
        employees.add(new Employee(2, "jeany", "female"));
        employees.add(new Employee(3, "woody", "male"));
    }
}
