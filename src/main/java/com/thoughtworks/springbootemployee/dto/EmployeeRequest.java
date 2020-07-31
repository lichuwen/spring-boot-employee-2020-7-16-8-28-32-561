package com.thoughtworks.springbootemployee.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeRequest implements Serializable {

    private Integer id;
    private String name;
    private String gender;

}
