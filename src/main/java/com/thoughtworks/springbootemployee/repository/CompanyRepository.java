package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;

import java.util.List;

public class CompanyRepository {
    public List<Company> getAll() {
        return null;
    }

    public Company addCompany(Company company) {
        return company;
    }


    public Company updateCompany(Integer companyId, Company company) {
        return company;
    }

    public Company deleteCompany(Integer companyId) {
        return null;
    }
}
