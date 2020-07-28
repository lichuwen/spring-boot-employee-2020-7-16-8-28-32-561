package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    List<Company> companies = new ArrayList<>();
    List<Employee> employees = new ArrayList<>();

    @GetMapping
    public List<Company> getCompanyInformation(@RequestParam(name = "page", required = false) Integer page, @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        employees.add(new Employee(1, "karen"));
        companies.add(new Company(1, 100, employees));
        companies.add(new Company(2, 100, employees));
        companies.add(new Company(3, 100, employees));
        if (page == null)
            return companies;
        return companies.subList(page - 1, pageSize - 1);
    }

    @GetMapping("/{id}")
    public Company getCertainCompany(@PathVariable Integer id) {
        for (Company singleCompany : companies) {
            if (singleCompany.getCompanyID() == id) {
                return singleCompany;
            }
        }
        return new Company();
    }

    @GetMapping(path = "/{id}/employees")
    public List<Employee> getEmployeesInCompany(@PathVariable int id) {
        for (Company company : companies) {
            if (company.getCompanyID() == id) {
                return company.getEmployees();
            }
        }
        return null;
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        companies.add(company);
        return company;
    }

    @PutMapping("/{id}")
    public Company updateCompanyInformation(@PathVariable Integer id,@RequestBody Company company) {
        companies.set(id, company);
        return company;
    }

    @DeleteMapping("/{id}")
    public String deleteEmployees(@PathVariable Integer id) {
        for (int index = 0; index < companies.size(); index++) {
            if (companies.get(index).getCompanyID() == id) {
                companies.get(index).setEmployeesNumber(0);
                companies.get(index).setEmployees(new ArrayList<>());
            }
        }
        return "delete success";
    }

}
