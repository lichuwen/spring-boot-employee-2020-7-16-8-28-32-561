package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.exception.GlobalException;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
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
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    EmployeeService employeeService;

    @BeforeEach
    public void init() {
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        when(employeeRepository.findById(1)).thenReturn(Optional.of(new Employee(1, "xiaoming", "male")));
        when(employeeRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(
                new Employee(1, "xiaoming", "male"),
                new Employee(4, "xiaoming-02", "male")));
        when(employeeRepository.findByGender("male")).thenReturn(Arrays.asList(
                new Employee(1, "xiaoming", "male"),
                new Employee(4, "xiaoming-02", "male")));
        when(employeeRepository.save(any(Employee.class))).thenReturn(new Employee(1, "xiaoming", "male"));
        EmployeeMapper employeeMapper = new EmployeeMapper();
        this.employeeService = new EmployeeService(employeeRepository, employeeMapper);
    }


    @Test
    void should_return_employee_list_when_get_all_employees() {
        //when
        List<EmployeeResponse> employees = employeeService.getAllEmployees();
        //then
        assertTrue(employees.size() > 0);
    }

    @Test
    void should_return_certain_employee_when_get_certain_employee_given_employee_id() throws GlobalException {
        //given
        Integer employeeId = 1;
        //when
        EmployeeResponse employee = employeeService.getCertainEmployee(employeeId);
        //then
        assertNotNull(employee);
        assertEquals(employeeId, employee.getId());
    }

    @Test
    void should_throw_exception_when_get_certain_employee_given_an_not_exist_employee_id() {
        //given
        Integer notExistEmployeeId = 2;
        //when then
        assertThrows(GlobalException.class, () -> employeeService.getCertainEmployee(notExistEmployeeId));

    }

    @Test
    void should_return_employees_by_page_when_get_employees_by_page_given_page_and_page_size() {
        //given
        Integer page = 1;
        Integer pageSize = 2;
        //when
        Page<EmployeeResponse> employeesByPage = employeeService.getEmployeesByPage(page, pageSize);
        //then
        assertNotNull(employeesByPage);
    }

    @Test
    void should_return_female_employees_when_get_employees_by_gender_given_gender(){
        //given
        String gender =  "male";
        //when
        List<EmployeeResponse> employeesByGender = employeeService.getEmployeesByGender(gender);
        //then
        assertTrue(employeesByGender.size()>0);
        assertEquals(gender,employeesByGender.get(0).getGender());
    }

    @Test
    void should_add_a_employee_when_add_new_employee_given_employee() {
        //given
        EmployeeRequest employee = new EmployeeRequest(7, "henry", "male");

        //when
        EmployeeResponse newEmployee = employeeService.addNewEmployee(employee);

        //then
        assertNotNull(newEmployee);
    }

    @Test
    void should_update_a_employee_when_update_employee_given_employee_and_employee_id() throws GlobalException {
        //given
        Integer employeeId = 1;
        Employee employee = new Employee(1, "henry", "male");
        //when
        Employee updatedEmployee = employeeService.updateEmployee(employeeId, employee);
        //then
        assertEquals(employeeId, updatedEmployee.getId());
    }

    @Test
    void should_delete_employee_when_delete_employee_given_employee_id() {
        //given
        Integer employeeId = 1;

        //when
        assertDoesNotThrow(() -> employeeService.deleteEmployee(employeeId));

    }

}
