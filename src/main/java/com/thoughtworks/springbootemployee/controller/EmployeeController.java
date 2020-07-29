package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    List<Employee> employees = new ArrayList<>();
//    EmployeeData employeeData = new EmployeeData(employees);
    @GetMapping
    public List<Employee> getEmployeeInformation(@RequestParam(name = "page", required = false) Integer page,
                                                 @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                 @RequestParam(name = "gender", required = false) String gender) {
        List<Employee> tempEmployees = this.employees;
        if (gender != null) {
            tempEmployees = employees.stream()
                    .filter(singleEmployee -> singleEmployee.getGender().equals(gender))
                    .collect(Collectors.toList());
        }
        if(page != null && pageSize != null){
            int pageStart = (page - 1) * pageSize;
            int pageEnd = pageStart + pageSize;
            tempEmployees = tempEmployees.subList(pageStart, pageEnd);
        }
        return tempEmployees;
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
