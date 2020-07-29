package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")

public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<Company> getCompanyInformationByPage(@RequestParam(name = "page", required = false) Integer page, @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        return companyService.getCompaniesByPage(page , pageSize);
    }

    @GetMapping
    public List<Company> getCompanyInformation() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCertainCompany(@PathVariable Integer id) {
        return companyService.getCertainCompany(id);
    }

    @GetMapping(path = "/{id}/employees")
    public List<Employee> getEmployeesInCompany(@PathVariable int id) {
        return companyService.getEmployeesInCompany(id);
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        return companyService.addNewCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompanyInformation(@PathVariable Integer id,@RequestBody Company company) {
        return companyService.updateCompany(id, company);
    }

    //todo
    @DeleteMapping("/{id}")
    public Company deleteEmployees(@PathVariable Integer id) {
        return companyService.deleteCompany(id);
    }

}
