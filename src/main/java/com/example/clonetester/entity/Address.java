package com.example.clonetester.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
public class Address extends BaseClass {

    private String street;
    private String country;
    private String city;
}