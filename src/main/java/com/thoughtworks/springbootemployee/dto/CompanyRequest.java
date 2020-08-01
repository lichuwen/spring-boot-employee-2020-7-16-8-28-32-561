package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest implements Serializable {

    private Integer id;
    private String companyName;
    private Integer employeesNumber;
    List<Employee> employees;

}
