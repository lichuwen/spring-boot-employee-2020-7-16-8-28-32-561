package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.model.Employee;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CompanyRequest implements Serializable {

    private Integer id;
    private String companyName;
    private Integer employeesNumber;
    List<Employee> employees;

}
