package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Enum.ResultEnum;
import com.thoughtworks.springbootemployee.exception.GlobalException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getCertainEmployee(Integer employeeId) throws GlobalException {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new GlobalException(ResultEnum.DATA_NOT_FOUND.getMsg());
        }
    }

    public Page<Employee> getEmployeesByPage(Integer page, Integer pageSize) {
        return employeeRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public Employee addNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer employeeId, Employee employee) throws GlobalException {
        Optional<Employee> byId = employeeRepository.findById(employeeId);
        if (byId.isPresent()) {
            Employee oldEmployee = byId.get();
            BeanUtils.copyProperties(employee, oldEmployee);
            return employeeRepository.save(employee);
        } else {
            throw new GlobalException(ResultEnum.DATA_NOT_FOUND.getMsg());
        }
    }

    public void deleteEmployee(Integer employeeId) throws GlobalException {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            employeeRepository.deleteById(employeeId);
        } else {
            throw new GlobalException(ResultEnum.DATA_NOT_FOUND.getMsg());
        }
    }
}
