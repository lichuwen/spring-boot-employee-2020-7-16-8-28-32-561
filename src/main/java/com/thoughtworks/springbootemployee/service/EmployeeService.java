package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Employee> getEmployeesByPage(Integer page, Integer pageSize) {
        return employeeRepository.getAllEmployees().subList((page-1)*pageSize,page*pageSize);
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.getAllEmployees().stream()
                .filter( employee-> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public Employee addNewEmployee(Employee employee) {
        return employeeRepository.addEmployee(employee);
    }
}
