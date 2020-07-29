package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Employee getCertainEmployee(Integer employeeId) {
        return employeeRepository.getAllEmployees().stream().filter(employee -> employee.getEmployeeId() == employeeId).findFirst().orElse(null);
    }
}
