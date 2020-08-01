package com.thoughtworks.springbootemployee.mapperTest;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import mapper.CompanyMapper;
import mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CompanyMapperTest {
    @Test
    void should_return_company_when_mapper_convert_to_company_given_companyRequest() {
        //given
        CompanyRequest companyRequest = new CompanyRequest(1, 1000, "oocl", new ArrayList<>());
        CompanyMapper mapper = new CompanyMapper();

        //when
        Company company = mapper.toCompany(companyRequest);

        //then
        assertEquals(company.getCompanyID(), companyRequest.getCompanyID());
        assertEquals(company.getCompanyName(), companyRequest.getCompanyName());
        assertEquals(company.getEmployeesNumber(), companyRequest.getEmployeesNumber());
        assertEquals(company.getEmployees(), companyRequest.getEmployees());

    }

    @Test
    void should_return_company_response_when_mapper_convert_to_company_dto_given_company() {
        //given
        Company company = new Company(1, 1000, "oocl", new ArrayList<>());
        CompanyMapper mapper = new CompanyMapper();

        //when
        CompanyResponse companyResponse = mapper.toCompanyDto(company);

        //then
        assertEquals(company.getCompanyID(), companyResponse.getCompanyID());
        assertEquals(company.getCompanyName(), companyResponse.getCompanyName());
        assertEquals(company.getEmployeesNumber(), companyResponse.getEmployeesNumber());
        assertEquals(company.getEmployees(), companyResponse.getEmployees());

    }
}
