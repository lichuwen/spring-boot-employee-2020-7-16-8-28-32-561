package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    List<Employee> employees = new ArrayList<>();

    public EmployeeController() {
        employees.add(new Employee(1, "karen","male"));
        employees.add(new Employee(2, "jeany","female"));
        employees.add(new Employee(3, "woody","male"));
    }
    @GetMapping
    public List<Employee> getEmployeeInformation(@RequestParam(name = "page", required = false) Integer page, @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        if (page == null)
            return employees;
        int pageStart = (page - 1) * pageSize;
        int pageEnd = pageStart + pageSize;
        return employees.subList(pageStart, pageEnd);
    }
}
