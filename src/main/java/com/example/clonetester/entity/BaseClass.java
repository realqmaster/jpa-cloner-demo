package com.example.clonetester.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String createdBy;
    private LocalDateTime createdAt;
}
