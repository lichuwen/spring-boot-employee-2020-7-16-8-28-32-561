package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Enum.ResultEnum;
import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.exception.GlobalException;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CompanyServiceTest {
    private CompanyRepository companyRepository;
    private CompanyService companyService;

    @BeforeEach
    private void init() {
        companyRepository = Mockito.mock(CompanyRepository.class);
        when(companyRepository.findAll())
                .thenReturn(Arrays.asList(
                        new Company(1, 3, "OOCL",
                                Arrays.asList(
                                        new Employee(1, "xiaoming", "male"),
                                        new Employee(2, "jeany", "female"),
                                        new Employee(3, "woody", "male")
                                )),
                        new Company(2, 3, "ThoughtWorks",
                                Arrays.asList(
                                        new Employee(4, "xiaoming-02", "male"),
                                        new Employee(5, "jeany-02", "female"),
                                        new Employee(6, "woody-02", "male")
                                ))
                        )
                );
        when(companyRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());
        when(companyRepository.findById(1)).thenReturn(Optional.of(new Company(1, 3, "OOCL", Collections.singletonList(new Employee(4, "xiaoming-02", "male")))));
        when(companyRepository.save(any(Company.class))).thenReturn(new Company(1, 3, "ThoughtWorks", Collections.singletonList(new Employee(4, "xiaoming-02", "male"))));
        CompanyMapper companyMapper = new CompanyMapper();
        EmployeeMapper employeeMapper = new EmployeeMapper();
        companyService = new CompanyService(companyRepository,companyMapper,employeeMapper);
    }

    @Test
    public void should_return_companyList_when_get_all_companies() {
        //given
        List<CompanyResponse> companyList;
        //when
        companyList = companyService.getAllCompanies();
        //then
        //todo
//        assertIterableEquals(companyList,);
        assertTrue(companyList.size() > 0);
    }

    @Test
    void should_return_certain_company_when_get_certain_company_given_company_id() throws GlobalException {
        //given
        Integer companyId = 1;
        //when
        CompanyResponse company = companyService.getCertainCompany(companyId);
        //then
        assertNotNull(company);
        assertEquals(companyId, company.getCompanyID());


    }

    @Test
    void should_return_employees_when_get_employees_in_company_given_company_id() throws GlobalException {
        //given
        Integer companyId = 1;
        //when
        List<EmployeeResponse> employees = companyService.getEmployeesInCompany(companyId);
        //then
        //todo
        assertTrue(employees.size() > 0);

    }

    @Test
    void should_return_companies_by_page_when_get_companies_by_page_given_page_and_page_size() {
        //given
        Integer page = 1;
        Integer pageSize = 2;

        //when
        Page<CompanyResponse> companiesByPage = companyService.getCompaniesByPage(page, pageSize);
        //then
        //todo
        assertNotNull(companiesByPage);

    }

    @Test
    void should_add_a_company_when_add_new_company_given_company() {
        //given
        CompanyRequest company = new CompanyRequest(3, 1, "OOCL", Collections.singletonList(new Employee(7, "henry", "male")));

        //when
        CompanyResponse newCompany = companyService.addNewCompany(company);

        //then
        //todo
        assertNotNull(newCompany);
    }

    @Test
    void should_update_a_company_when_update_company_given_company_and_company_id() throws GlobalException {
        //given
        Integer companyId = 1;
        CompanyRequest company = new CompanyRequest(1, 1000, "OOCL", new ArrayList<>());

        //when
        CompanyResponse updatedCompany = companyService.updateCompany(companyId, company);
        //then
        assertEquals(companyId, updatedCompany.getCompanyID());
    }

    @Test
    void should_throw_gloable_exception_when_update_company_given_company_id_which_not_exist() {
        Integer notExistCompanyId = 2;
        GlobalException globalException = assertThrows(GlobalException.class, () -> companyService.updateCompany(2, new CompanyRequest()));
        assertEquals(ResultEnum.DATA_NOT_FOUND.getMsg(), globalException.getMsg());
    }


    @Test
    void should_delete_company_and_employee_when_delete_company_given_company_id() throws GlobalException {
        //given
        Integer companyId = 1;

        //when
        Boolean result = companyService.deleteCompany(companyId);
        verify(companyRepository).deleteById(1);
        //then
        assertTrue(result);
    }




}
