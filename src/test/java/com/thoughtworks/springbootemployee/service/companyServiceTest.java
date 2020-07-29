package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class companyServiceTest {
    @Test
    public void should_return_companyList_when_get_all_companies() {
        //given
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        when(companyRepository.getAll()).thenReturn(Arrays.asList(new Company(1, 2, Arrays.asList(new Employee(1, "xiaoming", "male")))));
        CompanyService companyService = new CompanyService(companyRepository);
        List<Company> companyList;

        //when
        companyList = companyService.getAllCompanies();

        //then
        assertTrue(companyList.size() > 0);
    }

}
