package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    List<Employee> employees = new ArrayList<>();
    private static final String DEFAULT_PAGE_START = "1";
    private static final String DEFAULT_PAGE_END = "2";

    public EmployeeController() {
        employees.add(new Employee(1, "karen","male"));
        employees.add(new Employee(2, "jeany","female"));
        employees.add(new Employee(3, "woody","male"));
    }
    @GetMapping
    public List<Employee> getEmployeeInformation(@RequestParam(name = "page", required = false, defaultValue = DEFAULT_PAGE_START) Integer page,
                                                 @RequestParam(name = "pageSize", required = false, defaultValue = DEFAULT_PAGE_END) Integer pageSize,
                                                 @RequestParam(name = "gender", required = false) String gender) {
        List<Employee> tempEmployees = this.employees;
        if (gender != null) {
            tempEmployees = employees.stream()
                    .filter(singleEmployee -> singleEmployee.getGender().equals(gender))
                    .collect(Collectors.toList());
        }
        int pageStart = (page - 1) * pageSize;
        int pageEnd = pageStart + pageSize;
        return tempEmployees.subList(pageStart, pageEnd);
    }

    @GetMapping("/{id}")
    public Employee getCertainEmployee(@PathVariable Integer id) {
        for (Employee singleEmployee : employees) {
            if (singleEmployee.getEmployeeId() == id) {
                return singleEmployee;
            }
        }
        return new Employee();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        employees.add(employee);
        return employee;
    }

    @PutMapping("/{id}")
    public Employee updateEmployeeInformation(@PathVariable Integer id,@RequestBody Employee employee) {
        for (int index = 0; index < employees.size(); index++) {
            if (employees.get(index).getEmployeeId() == id){
                employees.set(index, employee);
                break;
            }
        }
        return employee;
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        for (int index = 0; index < employees.size(); index++) {
            if (employees.get(index).getEmployeeId() == id){
                employees.remove(index);
                break;
            }
        }
        return "delete success";
    }
}
