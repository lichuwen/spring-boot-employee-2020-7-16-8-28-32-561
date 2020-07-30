package com.thoughtworks.springbootemployee.integrationTest;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTest {

    private final List<Employee> employeeList = Arrays.asList(new Employee(1, "Karen", "female"),
            new Employee(2, "Jeany", "female"),
            new Employee(3, "Henry", "male"),
            new Employee(4, "Woody", "male")
    );
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    MockMvc mockMvc;


    @Test
    void should_get_employees_list_when_hit_get_employee_endpoint_given_nothing() throws Exception {
        employeeRepository.save(employeeList.get(0));
        employeeRepository.save(employeeList.get(1));
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Karen"))
                .andExpect(jsonPath("$[0].gender").value("female"))
                .andExpect(jsonPath("$[1].name").value("Jeany"))
                .andExpect(jsonPath("$[1].gender").value("female"));
    }

    @Test
    void should_add_employee_when_hit_post_employee_endpoint_given_employee() throws Exception {
        String employeeInfo = "{\n" +
                "            \"employeeId\": 1,\n" +
                "            \"name\": \"lcw\",\n" +
                "            \"gender\": \"male\"\n" +
                "        },";
        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(employeeInfo))
                .andExpect(jsonPath("$.name").value("lcw"))
                .andExpect(jsonPath("$.gender").value("male"));
        List<Employee> all = employeeRepository.findAll();
        assertEquals(1, all.size());
        assertEquals("lcw", all.get(0).getName());
        assertEquals("male", all.get(0).getGender());
    }

    @Test
    void should_get_employees_list_when_hit_get_employee_endpoint_given_gender() throws Exception {
        employeeRepository.save(employeeList.get(0));
        employeeRepository.save(employeeList.get(1));
        employeeRepository.save(employeeList.get(2));
        employeeRepository.save(employeeList.get(3));
        String gender = "male";
        mockMvc.perform(get("/employees").param("gender", gender))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Henry"))
                .andExpect(jsonPath("$[1].name").value("Woody"));
    }

    @Test
    void should_return_employees_list_when_hit_get_employee_endpoint_given_page_and_page_size() throws Exception {
        //given
        employeeRepository.save(employeeList.get(0));
        employeeRepository.save(employeeList.get(1));
        employeeRepository.save(employeeList.get(2));
        employeeRepository.save(employeeList.get(3));
        int page = 1;
        int pageSize = 3;

        mockMvc.perform(get("/employees?page=" + page + "&pageSize=" + pageSize))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(3))
                .andExpect(jsonPath("$.content[0].name").value("Karen"))
                .andExpect(jsonPath("$.content[1].name").value("Jeany"));
    }


    @Test
    void should_return_employee_when_hit_get_employee_endpoint_given_employee_id() throws Exception {
        //given
        employeeRepository.save(employeeList.get(0));
        Integer id = 1;

        mockMvc.perform(get("/employees/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId").value(id));
    }

}
