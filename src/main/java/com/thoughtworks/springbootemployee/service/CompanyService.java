package com.thoughtworks.springbootemployee.service;


import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCertainCompany(Integer companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public List<Employee> getEmployeesInCompany(Integer companyId) {
        Optional<Company> byId = companyRepository.findById(companyId);
        if(byId.isPresent()){
            return byId.get().getEmployees();
        }else {
            return new ArrayList<>();
        }
    }

    public Page<Company> getCompaniesByPage(Integer page, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(page-1, pageSize));
    }


    public Company addNewCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Integer companyId, Company company) {
        Optional<Company> byId = companyRepository.findById(companyId);
        if(byId.isPresent()){
            Company oldCompany = byId.get();
            BeanUtils.copyProperties(company, oldCompany);
            return companyRepository.save(oldCompany);
        }else {
            return null;
        }
    }

    public Boolean deleteCompany(Integer companyId) {
        Optional<Company> optional = companyRepository.findById(companyId);
        if (optional.isPresent()) {
            companyRepository.deleteById(companyId);
            return true;
        }
        return false;
    }
}
