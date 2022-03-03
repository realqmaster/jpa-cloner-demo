package com.example.clonetester.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Child extends BaseClass{

    private String name;
}
