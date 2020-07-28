package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @GetMapping
    public List<Company> getAll(@RequestParam(name = "page" , required = false) Integer page, @RequestParam(name = "pageSize",required = false) Integer pageSize) {
        List<Company> companies = new ArrayList<>();
        List<Company> certainCompanies = new ArrayList<>();
        companies.add(new Company(1,null));
        companies.add(new Company(2,null));
        companies.add(new Company(3,null));
        companies.add(new Company(4,null));
        companies.add(new Company(5,null));
        if(page != null && pageSize != null) {
            for(int index = 0; index < pageSize; index++) {
                certainCompanies.add(companies.get(index));
            }
            return certainCompanies;
        }
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
