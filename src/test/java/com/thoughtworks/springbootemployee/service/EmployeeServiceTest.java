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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    EmployeeService employeeService;

    private final List<Employee> employeeList = Arrays.asList(
            new Employee(1, "xiaoming", "male"),
            new Employee(4, "xiaoming-02", "male")
    );
    private final EmployeeMapper employeeMapper = new EmployeeMapper();

    @BeforeEach
    public void init() {
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employeeList.get(0)));
        when(employeeRepository.findAll(any(Pageable.class))).thenReturn((new PageImpl<>(Collections.singletonList(
                employeeList.get(0)))));
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(
                employeeList.get(0),
                employeeList.get(1)));
        when(employeeRepository.findByGender("male")).thenReturn(Arrays.asList(
                employeeList.get(0),
                employeeList.get(1)));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employeeList.get(0));
        this.employeeService = new EmployeeService(employeeRepository, employeeMapper);
    }


    @Test
    void should_return_employee_list_when_get_all_employees() {
        //when
        List<EmployeeResponse> employees = employeeService.getAllEmployees();
        //then
        assertIterableEquals(employeeList.stream().map(employeeMapper::toEmployeeDto).collect(Collectors.toList()), employees);
    }

    @Test
    void should_return_certain_employee_when_get_certain_employee_given_employee_id() throws GlobalException {
        //given
        Integer employeeId = 1;
        //when
        EmployeeResponse employee = employeeService.getCertainEmployee(employeeId);
        //then
        assertEquals(employeeMapper.toEmployeeDto(employeeList.get(0)), employee);
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
        Integer pageSize = 1;
        //when
        Page<EmployeeResponse> employeesByPage = employeeService.getEmployeesByPage(page, pageSize);
        //then
        assertEquals(employeesByPage.getContent().get(0), employeeMapper.toEmployeeDto(employeeList.get(0)));
    }

    @Test
    void should_return_female_employees_when_get_employees_by_gender_given_gender(){
        //given
        String gender =  "male";
        //when
        List<EmployeeResponse> employeesByGender = employeeService.getEmployeesByGender(gender);
        //then
        assertEquals(employeeList.stream().map(employeeMapper::toEmployeeDto).collect(Collectors.toList()), employeesByGender);
    }

    @Test
    void should_add_a_employee_when_add_new_employee_given_employee() {
        //given
        EmployeeRequest employee = new EmployeeRequest(1, "xiaoming", "male");

        //when
        EmployeeResponse newEmployee = employeeService.addNewEmployee(employee);

        //then
        EmployeeResponse oldEmployee = employeeMapper.toEmployeeDto(employeeMapper.toEmployee(employee));
        assertEquals(oldEmployee, newEmployee);
    }

    @Test
    void should_update_a_employee_when_update_employee_given_employee_and_employee_id() throws GlobalException {
        //given
        Integer employeeId = 1;
        EmployeeRequest employee = new EmployeeRequest(1, "henry", "male");
        EmployeeResponse oldEmployee = employeeMapper.toEmployeeDto(employeeMapper.toEmployee(employee));
        //when
        EmployeeResponse updatedEmployee = employeeService.updateEmployee(employeeId, employee);
        //then
        assertEquals(oldEmployee, updatedEmployee);
    }

    @Test
    void should_delete_employee_when_delete_employee_given_employee_id() {
        //given
        Integer employeeId = 1;

        //when
        assertDoesNotThrow(() -> employeeService.deleteEmployee(employeeId));

    }

}
