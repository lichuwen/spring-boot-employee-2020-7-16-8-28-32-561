package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;


    @GetMapping
    public List<Employee> getEmployeeInformation() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getEmployeesByGender(@RequestParam(name = "gender", required = false) String gender) {
        return employeeService.getEmployeesByGender(gender);
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<Employee> getEmployeesByPage(@RequestParam(name = "page", required = false) Integer page,
                                             @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        return employeeService.getEmployeesByPage(page, pageSize);
    }


    @GetMapping("/{id}")
    public Employee getCertainEmployee(@PathVariable Integer id) {
        return employeeService.getCertainEmployee(id);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addNewEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployeeInformation(@PathVariable Integer id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public Employee deleteEmployee(@PathVariable Integer id) {
        return employeeService.deleteEmployee(id);
    }
}
