package com.thoughtworks.springbootemployee.data;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;

import java.util.List;

public class CompanyData {

    public CompanyData(List<Company> companies, List<Employee> employees) {
        companies.add(new Company(1, 100, employees));
        companies.add(new Company(2, 200, employees));
        companies.add(new Company(3, 300, employees));
    }
}
