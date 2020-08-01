package com.thoughtworks.springbootemployee.mapperTest;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.model.Employee;
import mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class EmployeeMapperTest {
    @Test
    void should_return_employee_when_mapper_convert_to_employee_given_employeeRequest() {
        //given
        EmployeeRequest employeeRequest = new EmployeeRequest(1, "karen", "female");
        EmployeeMapper mapper = new EmployeeMapper();

        //when
        Employee employee = mapper.toEmployee(employeeRequest);

        //then
        assertEquals(employee.getId(), employeeRequest.getId());
        assertEquals(employee.getName(), employeeRequest.getName());
        assertEquals(employee.getGender(), employeeRequest.getGender());

    }

    @Test
    void should_return_employee_response_when_mapper_convert_to_employee_dto_given_employee() {
        //given
        Employee employee = new Employee(1, "karen", "female");
        EmployeeMapper mapper = new EmployeeMapper();

        //when
        EmployeeResponse employeeResponse = mapper.toEmployeeDto(employee);

        //then
        assertEquals(employee.getId(), employeeResponse.getId());
        assertEquals(employee.getName(), employeeResponse.getName());
        assertEquals(employee.getGender(), employeeResponse.getGender());

    }
}
