package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class companyServiceTest {
    private CompanyRepository companyRepository;
    private CompanyService companyService;

    @BeforeEach
    private void init() {
        companyRepository = Mockito.mock(CompanyRepository.class);
        when(companyRepository.getAll())
                .thenReturn(Collections.singletonList(
                        new Company(1, 2,
                                Arrays.asList(
                                        new Employee(1, "xiaoming", "male"),
                                        new Employee(2, "jeany", "female"),
                                        new Employee(3, "woody", "male")
                                ))));
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


}
