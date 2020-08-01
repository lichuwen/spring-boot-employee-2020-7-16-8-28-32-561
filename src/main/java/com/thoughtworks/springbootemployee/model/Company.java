package com.thoughtworks.springbootemployee.model;

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
    @JoinTable(name = "company_employees", joinColumns = {
            @JoinColumn(name = "company_companyid")
    }, inverseJoinColumns = {
            @JoinColumn(name = "employees_id")
    })
    List<Employee> employees;
}
