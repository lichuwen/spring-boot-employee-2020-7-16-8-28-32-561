package com.thoughtworks.springbootemployee.integrationTest;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    CompanyRepository companyRepository;

    private final List<Company> companyList = Arrays.asList(new Company(1, 3, "OOCL",
            Arrays.asList(
                    new Employee(1, "woody", "male"),
                    new Employee(2, "karen", "female"),
                    new Employee(3, "jeany", "female")
            )), new Company(2, 3, "ThoughtWorks",
            Arrays.asList(
                    new Employee(4, "woody-02", "male"),
                    new Employee(5, "karen-02", "female"),
                    new Employee(6, "jeany-02", "female")
            )));

    @Test
    void should_get_company_page_when_hit_get_company_given_page_and_pageSize() throws Exception {
        Company company1 = companyRepository.save(companyList.get(0));
        Company company2 = companyRepository.save(companyList.get(1));
        mockMvc.perform(get("/companies")
                .param("page", "1")
                .param("pageSize", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].companyID").value(company1.getCompanyID()))
                .andExpect(jsonPath("$.content[0].employeesNumber").value(company1.getEmployeesNumber()))
                .andExpect(jsonPath("$.content[0].companyName").value(company1.getCompanyName()))
                .andExpect(jsonPath("$.content[0].employees.length()").value(company1.getEmployees().size()))
                .andExpect(jsonPath("$.content[1].companyID").value(company2.getCompanyID()))
                .andExpect(jsonPath("$.content[1].employeesNumber").value(company2.getEmployeesNumber()))
                .andExpect(jsonPath("$.content[1].companyName").value(company2.getCompanyName()))
                .andExpect(jsonPath("$.content[1].employees.length()").value(company2.getEmployees().size()));
    }


    @Test
    void should_get_company_list_when_hit_get_company_given_nothing() throws Exception {
        Company company1 = companyRepository.save(companyList.get(0));
        Company company2 = companyRepository.save(companyList.get(1));
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].companyID").value(company1.getCompanyID()))
                .andExpect(jsonPath("$[0].employeesNumber").value(company1.getEmployeesNumber()))
                .andExpect(jsonPath("$[0].companyName").value(company1.getCompanyName()))
                .andExpect(jsonPath("$[0].employees.length()").value(company2.getEmployees().size()))
                .andExpect(jsonPath("$[1].companyID").value(company2.getCompanyID()))
                .andExpect(jsonPath("$[1].employeesNumber").value(company2.getEmployeesNumber()))
                .andExpect(jsonPath("$[1].companyName").value(company2.getCompanyName()))
                .andExpect(jsonPath("$[1].employees.length()").value(company2.getEmployees().size()));
    }

    @Test
    void should_get_company_when_hit_get_company_given_id() throws Exception {
        Company company = companyRepository.save(companyList.get(0));
        mockMvc.perform(get("/companies/" + company.getCompanyID()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyID").value(company.getCompanyID()))
                .andExpect(jsonPath("$.employeesNumber").value(company.getEmployeesNumber()))
                .andExpect(jsonPath("$.companyName").value(company.getCompanyName()))
                .andExpect(jsonPath("$.employees.length()").value(company.getEmployees().size()));
    }

    @Test
    void should_get_employees_when_hit_get_company_given_id() throws Exception {
        Company company = companyRepository.save(companyList.get(0));
        mockMvc.perform(get("/companies/" + company.getCompanyID() + "/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(company.getEmployees().size()))
                .andExpect(jsonPath("$[0].id").value(company.getEmployees().get(0).getId()))
                .andExpect(jsonPath("$[0].name").value(company.getEmployees().get(0).getName()))
                .andExpect(jsonPath("$[0].gender").value(company.getEmployees().get(0).getGender()))
                .andExpect(jsonPath("$[1].id").value(company.getEmployees().get(1).getId()))
                .andExpect(jsonPath("$[1].name").value(company.getEmployees().get(1).getName()))
                .andExpect(jsonPath("$[1].gender").value(company.getEmployees().get(1).getGender()));
    }

    @Test
    void should_add_company_when_hit_post_company_endpoint_given_company() throws Exception {
        String requestBody = "{\"companyName\":\"OOCL\",\"companyID\":1,\"employeesNumber\":2,\"employees\":[{\"id\":1,\"name\":\"woody\",\"gender\":\"male\"},{\"id\":2,\"name\":\"karen\",\"gender\":\"female\"}]}";
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.companyID").value(1))
                .andExpect(jsonPath("$.employeesNumber").value(2))
                .andExpect(jsonPath("$.companyName").value("OOCL"))
                .andExpect(jsonPath("$.employees.length()").value(2));
        List<Company> all = companyRepository.findAll();
        assertEquals(1, all.size());
        assertEquals("OOCL", all.get(0).getCompanyName());
        assertEquals(1, all.get(0).getCompanyID());
        assertEquals(2, all.get(0).getEmployeesNumber());
    }

    @Test
    void should_update_company_when_hit_put_company_endpoint_given_company() throws Exception {
        Company company = companyRepository.save(companyList.get(0));
        String requestBody = "{\"companyName\":\"OOCL-update\",\"companyID\":1,\"employeesNumber\":2,\"employees\":[{\"id\":1,\"name\":\"woody\",\"gender\":\"male\"},{\"id\":2,\"name\":\"karen\",\"gender\":\"female\"}]}";
        mockMvc.perform(put("/companies/"+company.getCompanyID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("OOCL-update"))
                .andExpect(jsonPath("$.employeesNumber").value(2))
                .andExpect(jsonPath("$.employees.length()").value(2));

    }
}
