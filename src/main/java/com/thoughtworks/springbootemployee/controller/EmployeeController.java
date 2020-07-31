package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.exception.GloableException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


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
    public Employee getCertainEmployee(@PathVariable Integer id) throws GloableException {
        return employeeService.getCertainEmployee(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addNewEmployee(employee);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee updateEmployeeInformation(@PathVariable Integer id, @RequestBody Employee employee) throws GloableException {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteEmployee(@PathVariable Integer id) throws GloableException {
        employeeService.deleteEmployee(id);
        return "成功";
    }
}
