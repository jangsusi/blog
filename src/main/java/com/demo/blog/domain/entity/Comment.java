package com.demo.blog.domain.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class Comment extends BaseEntity{

    private String body;

    @Embedded
    private Profile author;
}
