package com.thoughtworks.springbootemployee.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getCertainEmployee(Integer employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public Page<Employee> getEmployeesByPage(Integer page, Integer pageSize) {
        return employeeRepository.findAll(PageRequest.of(page,pageSize));
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public Employee addNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer employeeId, Employee employee) {
        Optional<Employee> byId = employeeRepository.findById(employeeId);
        if(byId.isPresent()){
            Employee oldEmployee = byId.get();
            BeanUtils.copyProperties(employee,oldEmployee);
            return employeeRepository.save(employee);
        }else {
            return null;
        }
    }

    public Employee deleteEmployee(Integer employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        employee.ifPresent(employeeRepository::delete);
        return employee.orElse(null);
    }
}
