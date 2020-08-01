package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.exception.GlobalException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
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
    public List<EmployeeResponse> getEmployeeInformation() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(params = {"gender"})
    public List<EmployeeResponse> getEmployeesByGender(@RequestParam(name = "gender", required = false) String gender) {
        return employeeService.getEmployeesByGender(gender);
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<EmployeeResponse> getEmployeesByPage(@RequestParam(name = "page", required = false) Integer page,
                                                     @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        return employeeService.getEmployeesByPage(page, pageSize);
    }


    @GetMapping("/{id}")
    public EmployeeResponse getCertainEmployee(@PathVariable Integer id) throws GlobalException {
        return employeeService.getCertainEmployee(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addNewEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployeeInformation(@PathVariable Integer id, @RequestBody Employee employee) throws GlobalException {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) throws GlobalException {
        employeeService.deleteEmployee(id);
        return "success";
    }
}
