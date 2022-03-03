package com.example.clonetester.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
public class Employee extends BaseClass {

  private String name;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "home_id")
  private Address homeAddress;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "parent_id")
  private Set<Child> children;
}
