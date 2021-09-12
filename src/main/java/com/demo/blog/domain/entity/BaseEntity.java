package com.demo.blog.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
