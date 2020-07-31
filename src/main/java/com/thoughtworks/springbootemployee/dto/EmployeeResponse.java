package com.thoughtworks.springbootemployee.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeResponse implements Serializable {

    private Integer id;
    private String name;
    private String gender;

}
