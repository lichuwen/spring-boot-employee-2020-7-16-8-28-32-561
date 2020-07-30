package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.GloableException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    EmployeeService employeeService;

    @BeforeEach
    public void init() {
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(new Employee(1, "xiaoming", "male")));
        when(employeeRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(
                new Employee(1, "xiaoming", "male"),
                new Employee(4, "xiaoming-02", "male")));
        when(employeeRepository.findByGender("male")).thenReturn(Arrays.asList(
                new Employee(1, "xiaoming", "male"),
                new Employee(4, "xiaoming-02", "male")));
        when(employeeRepository.save(any(Employee.class))).thenReturn(new Employee(1,"xiaoming","male"));
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
    void should_return_certain_employee_when_get_certain_employee_given_employee_id() throws GloableException {
        //given
        Integer employeeId = 1;
        //when
        Employee employee = employeeService.getCertainEmployee(employeeId);
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
        Page<Employee> employeesByPage = employeeService.getEmployeesByPage(page, pageSize);
        //then
        assertNotNull(employeesByPage);
    }

    @Test
    void should_return_female_employees_when_get_employees_by_gender_given_gender(){
        //given
        String gender =  "male";
        //when
        List<Employee> employeesByGender = employeeService.getEmployeesByGender(gender);
        //then
        assertTrue(employeesByGender.size()>0);
        assertEquals(gender,employeesByGender.get(0).getGender());
    }

    @Test
    void should_add_a_employee_when_add_new_employee_given_employee() {
        //given
        Employee employee = new Employee(7, "henry", "male");

        //when
        Employee newEmployee = employeeService.addNewEmployee(employee);

        //then
        assertNotNull(newEmployee);
    }

    @Test
    void should_update_a_employee_when_update_employee_given_employee_and_employee_id() throws GloableException {
        //given
        Integer employeeId = 1;
        Employee employee = new Employee(1, "henry", "male");
        //when
        Employee updatedEmployee = employeeService.updateEmployee(employeeId, employee);
        //then
        assertEquals(employeeId, updatedEmployee.getEmployeeId());
    }

    @Test
    void should_delete_employee_when_delete_employee_given_employee_id() {
        //given
        Integer employeeId = 1;

        //when
        assertDoesNotThrow(() -> employeeService.deleteEmployee(employeeId));

    }

}
