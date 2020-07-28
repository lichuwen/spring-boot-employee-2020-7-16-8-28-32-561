package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @GetMapping
    public List<Company> getAll() {
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1,null));
        companies.add(new Company(2,null));
        return companies;
    }

    @GetMapping(path = "/{id}")
    public Company getCertainCompany(@PathVariable int id) {
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1,null));
        companies.add(new Company(2,null));
        for (Company company: companies) {
            if(company.getCompanyID() == id) {
                return company;
            }
        }
        return null;
    }

    @GetMapping(path = "/{id}/employees")
    public List<Employee> getEmployeesInCompany(@PathVariable int id) {
        List<Company> companies = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        companies.add(new Company(1,employees));
        companies.add(new Company(2,employees));
        for (Company company: companies) {
            if(company.getCompanyID() == id) {
                return company.getEmployees();
            }
        }
        return null;
    }
}
