package com.thoughtworks.springbootemployee.service;


import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.getAll();
    }

    public Company getCertainCompany(Integer companyId) {
        List<Company> companyArraysList =  companyRepository.getAll();
        return companyArraysList.stream().filter(company -> company.getCompanyID() == companyId).findFirst().orElse(null);
    }

    public List<Employee> getEmployeesInCompany(Integer companyId) {
         return companyRepository.getAll().stream()
                 .filter(company -> company.getCompanyID() == companyId)
                 .map(Company::getEmployees)
                 .findFirst().orElse(new ArrayList<>());
    }

    public List<Company> getCompaniesByPage(Integer page, Integer pageSize) {
        List<Company> companyArraysList =  companyRepository.getAll();
        int startPage = (page - 1)  * pageSize;
        int endPage = startPage + pageSize;
        return companyArraysList.subList(startPage,endPage
        );
    }


    public Company addNewCompany(Company company) {
        return companyRepository.addCompany(company);
    }

    public Company updateCompany(Integer companyId, Company company) {
        return companyRepository.updateCompany(companyId, company);
    }
}
