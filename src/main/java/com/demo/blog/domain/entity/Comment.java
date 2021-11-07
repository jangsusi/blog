package com.demo.blog.domain.entity;

import javax.persistence.*;

@Entity
public class Comment extends BaseEntity{

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;
}
