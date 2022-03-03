package com.example.clonetester.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Company extends BaseClass{

    private String name;
    private String code;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private List<Employee> employees;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "legal_address_id")
    private Address legalAddress;
}
