package com.thoughtworks.springbootemployee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyID;
    private Integer employeesNumber;
    private String companyName;

    @OneToMany(cascade = CascadeType.ALL, fetch = EAGER)
    List<Employee> employees;
}
