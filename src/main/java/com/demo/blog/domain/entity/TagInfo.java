package com.demo.blog.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class TagInfo extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;
}
