package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @GetMapping
    public List<Company> getCompanyInformation(@RequestParam(name = "page" , required = false) Integer page, @RequestParam(name = "pageSize",required = false) Integer pageSize) {
        List<Company> companies = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"karen"));
        companies.add(new Company(1,100,employees));
        companies.add(new Company(2,100,employees));
        companies.add(new Company(3,100,employees));
        return companies.subList(page-1,pageSize-1);
    }
//    @GetMapping()
//    public List<Company> getCertainCompany() {
//        List<Company> companies = new ArrayList<>();
//        companies.add(new Company(9,null));
//        companies.add(new Company(2,null));
//        return companies;
//    }
//
//    @GetMapping(path = "/{id}")
//    public Company getCertainCompany(@PathVariable int id) {
//        List<Company> companies = new ArrayList<>();
//        companies.add(new Company(1,null));
//        companies.add(new Company(2,null));
//        for (Company company: companies) {
//            if(company.getCompanyID() == id) {
//                return company;
//            }
//        }
//        return null;
//    }
//
//    @GetMapping(path = "/{id}/employees")
//    public List<Employee> getEmployeesInCompany(@PathVariable int id) {
//        List<Company> companies = new ArrayList<>();
//        List<Employee> employees = new ArrayList<>();
//        companies.add(new Company(1,employees));
//        companies.add(new Company(2,employees));
//        for (Company company: companies) {
//            if(company.getCompanyID() == id) {
//                return company.getEmployees();
//            }
//        }
//        return null;
//    }
}
