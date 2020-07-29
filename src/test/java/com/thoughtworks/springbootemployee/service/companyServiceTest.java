package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class companyServiceTest {
    private CompanyRepository companyRepository;
    private CompanyService companyService;

    @BeforeEach
    private void init() {
        companyRepository = Mockito.mock(CompanyRepository.class);
        when(companyRepository.getAll())
                .thenReturn(Arrays.asList(
                        new Company(1, 3,
                                Arrays.asList(
                                        new Employee(1, "xiaoming", "male"),
                                        new Employee(2, "jeany", "female"),
                                        new Employee(3, "woody", "male")
                                )),
                        new Company(2, 3,
                                Arrays.asList(
                                        new Employee(4, "xiaoming-02", "male"),
                                        new Employee(5, "jeany-02", "female"),
                                        new Employee(6, "woody-02", "male")
                                ))
                        )
                );
        when(companyRepository.addCompany(any(Company.class))).thenReturn(new Company());
        when(companyRepository.updateCompany(anyInt(),any(Company.class))).thenReturn(new Company(1,3,new ArrayList<>()));
        companyService = new CompanyService(companyRepository);
    }

    @Test
    public void should_return_companyList_when_get_all_companies() {
        //given
        List<Company> companyList;
        //when
        companyList = companyService.getAllCompanies();
        //then
        assertTrue(companyList.size() > 0);
    }

    @Test
    void should_return_certain_company_when_get_certain_company_given_company_id() {
        //given
        Integer companyId = 1;
        //when
        Company company = companyService.getCertainCompany(companyId);
        //then
        assertNotNull(company);
        assertEquals(companyId, company.getCompanyID());
    }

    @Test
    void should_return_employees_when_get_employees_in_company_given_company_id() {
        //given
        Integer companyId = 1;
        //when
        List<Employee> employees = companyService.getEmployeesInCompany(companyId);
        //then
        assertTrue(employees.size() > 0);

    }

    @Test
    void should_return_companies_by_page_when_get_companies_by_page_given_page_and_page_size() {
        //given
        Integer page = 1;
        Integer pageSize = 2;
        //when
        List<Company> companiesByPage = companyService.getCompaniesByPage(page, pageSize);
        //then
        assertEquals(pageSize, companiesByPage.size());

    }

    @Test
    void should_add_a_company_when_add_new_company_given_company() {
        //given
        Company company = new Company(3, 1, Collections.singletonList(new Employee(7, "henry", "male")));

        //when
        Company newCompany = companyService.addNewCompany(company);

        //then
        assertNotNull(newCompany);
    }

    @Test
    void should_update_a_company_when_update_company_given_company_and_company_id() {
        //given
        Integer companyId = 1;
        Company company = new Company(1,1000,new ArrayList<>());
        //when
        Company updatedCompany = companyService.updateCompany(companyId,company);
        //then
        assertEquals(companyId,updatedCompany.getCompanyID());
    }
}
