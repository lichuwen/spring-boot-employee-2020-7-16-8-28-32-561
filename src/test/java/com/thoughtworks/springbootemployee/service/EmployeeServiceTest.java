package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    EmployeeService employeeService;

    @BeforeEach
    public void init() {
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        when(employeeRepository.getAllEmployees())
                .thenReturn(Arrays.asList(
                        new Employee(1, "xiaoming", "male"),
                        new Employee(2, "jeany", "female"),
                        new Employee(3, "woody", "male"),
                        new Employee(4, "xiaoming-02", "male"),
                        new Employee(5, "jeany-02", "female"),
                        new Employee(6, "woody-02", "male")
                        )
                );
        this.employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    void should_return_employee_list_when_get_all_employees() {
        //when
        List<Employee> employees = employeeService.getAllEmployees();
        //then
        assertTrue(employees.size() > 0);
    }

    @Test
    void should_return_certain_employee_when_get_certain_employee_given_employee_id() {
        //given
        Integer employeeId = 1;
        //when
        Employee employee= employeeService.getCertainEmployee(employeeId);
        //then
        assertNotNull(employee);
        assertEquals(employeeId, employee.getEmployeeId());
    }

    @Test
    void should_return_employees_by_page_when_get_employees_by_page_given_page_and_page_size() {
        //given
        Integer page = 1;
        Integer pageSize = 2;
        //when
        List<Employee> employeeByPage = employeeService.getEmployeesByPage(page, pageSize);
        //then
        assertEquals(pageSize, employeeByPage.size());
    }

    @Test
    void should_return_female_employees_when_get_employees_by_gender_given_gender(){
        //given
        String gender =  "female";
        //when
        List<Employee> employeesByGender = employeeService.getEmployeesByGender(gender);
        //then
        assertTrue(employeesByGender.size()>0);
        assertEquals(gender,employeesByGender.get(0).getGender());
    }



}
