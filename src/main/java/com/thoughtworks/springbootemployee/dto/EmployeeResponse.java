package com.thoughtworks.springbootemployee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse implements Serializable {

    private Integer id;
    private String name;
    private String gender;

}
