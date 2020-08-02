package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.exception.GlobalException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    public Page<CompanyResponse> getCompanyInformationByPage(@RequestParam(name = "page", required = false) Integer page, @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        return companyService.getCompaniesByPage(page, pageSize);
    }

    @GetMapping
    public List<CompanyResponse> getCompanyInformation() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public CompanyResponse getCertainCompany(@PathVariable Integer id) throws GlobalException {
        return companyService.getCertainCompany(id);
    }

    @GetMapping(path = "/{id}/employees")
    public List<EmployeeResponse> getEmployeesInCompany(@PathVariable int id) throws GlobalException {
        return companyService.getEmployeesInCompany(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse addCompany(@RequestBody CompanyRequest company) {
        return companyService.addNewCompany(company);
    }

    @PutMapping("/{id}")
    public CompanyResponse updateCompanyInformation(@PathVariable Integer id, @RequestBody CompanyRequest company) throws GlobalException {
        return companyService.updateCompany(id, company);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployees(@PathVariable Integer id) throws GlobalException {
        return companyService.deleteCompany(id) ? "success" : "FAIL";
    }

}
