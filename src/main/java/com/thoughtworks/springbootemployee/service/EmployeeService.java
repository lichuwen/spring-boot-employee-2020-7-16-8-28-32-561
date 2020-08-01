package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Enum.ResultEnum;
import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.exception.GlobalException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        for (Employee employee : employees) {
            employeeResponses.add(mapper.toEmployeeDto(employee));
        }
        return employeeResponses;
    }

    public EmployeeResponse getCertainEmployee(Integer employeeId) throws GlobalException {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            return mapper.toEmployeeDto(employee.get());
        } else {
            throw new GlobalException(ResultEnum.DATA_NOT_FOUND.getMsg());
        }
    }

    public Page<EmployeeResponse> getEmployeesByPage(Integer page, Integer pageSize) {
        Page<Employee> employees = employeeRepository.findAll(PageRequest.of(page - 1, pageSize));
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        for (Employee employee : employees) {
            employeeResponses.add(mapper.toEmployeeDto(employee));
        }
        return new PageImpl<>(employeeResponses);
    }

    public List<EmployeeResponse> getEmployeesByGender(String gender) {
        List<Employee> employees = employeeRepository.findByGender(gender);
        return employees.stream().map(employee -> mapper.toEmployeeDto(employee)).collect(Collectors.toList());
    }

    public EmployeeResponse addNewEmployee(EmployeeRequest employeeRequest) {
        Employee employee = mapper.toEmployee(employeeRequest);
        return mapper.toEmployeeDto(employeeRepository.save(employee));
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
