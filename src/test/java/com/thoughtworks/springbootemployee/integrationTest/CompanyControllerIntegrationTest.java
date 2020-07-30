package com.thoughtworks.springbootemployee.integrationTest;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @ProjectName: spring-boot-employee
 * @Package: com.thoughtworks.springbootemployee.integrationTest
 * @ClassName: CompanyControllerIntegrationTest
 * @Author: carrymaniac
 * @Description:
 * @Date: 2020/7/30 10:45 下午
 * @Version:
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    CompanyRepository companyRepository;
    @Test
    void should_get_company_page_when_hit_get_company_given_page_and_pageSize() throws Exception {
        Company company1 = new Company(1, 3, "OOCL",
                Arrays.asList(
                        new Employee(1, "xiaoming", "male"),
                        new Employee(2, "jeany", "female"),
                        new Employee(3, "woody", "male")
                ));
        Company company2 = new Company(2, 3, "ThoughtWorks",
                Arrays.asList(
                        new Employee(4, "xiaoming-02", "male"),
                        new Employee(5, "jeany-02", "female"),
                        new Employee(6, "woody-02", "male")
                ));
        companyRepository.save(company1);
        companyRepository.save(company2);
        mockMvc.perform(get("/companies")
                .param("page","1")
                .param("pageSize","2"))
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].companyID").value(company1.getCompanyID()))
                .andExpect(jsonPath("$.content[0].employeesNumber").value(company1.getEmployeesNumber()))
                .andExpect(jsonPath("$.content[0].companyName").value(company1.getCompanyName()))
                .andExpect(jsonPath("$.content[0].employees.length()").value(company1.getEmployees().size()));
    }

}
