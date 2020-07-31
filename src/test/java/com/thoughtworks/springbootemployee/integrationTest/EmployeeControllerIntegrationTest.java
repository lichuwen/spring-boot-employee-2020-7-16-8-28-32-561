package com.thoughtworks.springbootemployee.integrationTest;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTest {

    private final List<Employee> employeeList = Arrays.asList(
            new Employee(1, "Karen", "female"),
            new Employee(2, "Jeany", "female"),
            new Employee(3, "Henry", "male"),
            new Employee(4, "Woody", "male")
    );

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void init() {
        employeeRepository.save(employeeList.get(0));
        employeeRepository.save(employeeList.get(1));
        employeeRepository.save(employeeList.get(2));
        employeeRepository.save(employeeList.get(3));
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }


    @Test
    void should_get_employees_list_when_hit_get_employee_endpoint_given_nothing() throws Exception {
        //when
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0].name").value("Karen"))
                .andExpect(jsonPath("$[0].gender").value("female"))
                .andExpect(jsonPath("$[1].name").value("Jeany"))
                .andExpect(jsonPath("$[1].gender").value("female"));
    }

    @Test
    void should_add_employee_when_hit_post_employee_endpoint_given_employee() throws Exception {
        //given
        String employeeInfo = "{\n" +
                "            \"name\": \"lcw\",\n" +
                "            \"gender\": \"male\"\n" +
                "        },";

        //when
        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(employeeInfo))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("lcw"))
                .andExpect(jsonPath("$.gender").value("male"));
        List<Employee> employees = employeeRepository.findAll();
        assertEquals(5, employees.size());
        assertEquals("lcw", employees.get(4).getName());
        assertEquals("male", employees.get(4).getGender());
    }

    @Test
    void should_get_employees_list_when_hit_get_employee_endpoint_given_gender() throws Exception {
        //given
        String gender = "male";

        //when
        mockMvc.perform(get("/employees").param("gender", gender))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Henry"))
                .andExpect(jsonPath("$[1].name").value("Woody"));
    }

    @Test
    void should_return_employees_list_when_hit_get_employee_endpoint_given_page_and_page_size() throws Exception {
        //given
        int page = 1;
        int pageSize = 3;

        //when
        mockMvc.perform(get("/employees?page=" + page + "&pageSize=" + pageSize))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(3))
                .andExpect(jsonPath("$.content[0].name").value("Karen"))
                .andExpect(jsonPath("$.content[1].name").value("Jeany"));
    }


    @Test
    void should_return_employee_when_hit_get_employee_endpoint_given_employee_id() throws Exception {
        //given
        Employee employee = employeeRepository.save(employeeList.get(0));

        //when
        mockMvc.perform(get("/employees/" + employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employee.getId()));
    }

    @Test
    void should_return_employee_when_hit_update_employee_endpoint_given_employee_id_and_employee() throws Exception {
        //given
        Employee employee = employeeRepository.save(employeeList.get(0));
        String employeeInfo = "{\n" +
                "            \"id\": " + employee.getId() + ",\n" +
                "            \"name\": \"lcw\",\n" +
                "            \"gender\": \"male\"\n" +
                "        },";

        //when
        mockMvc.perform(put("/employees/" + employee.getId()).contentType(MediaType.APPLICATION_JSON).content(employeeInfo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employee.getId()))
                .andExpect(jsonPath("$.name").value("lcw"))
                .andExpect(jsonPath("$.gender").value("male"));
    }

    @Test
    void should_return_null_when_hit_delete_endpoint_given_employee_id() throws Exception {
        //given
        Employee employee = employeeRepository.save(employeeList.get(0));

        //when
        mockMvc.perform(delete("/employees/" + employee.getId()))
                .andExpect(status().isOk());

    }

}
