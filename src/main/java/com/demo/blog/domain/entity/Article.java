package com.demo.blog.domain.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Article extends BaseEntity{

    private String title;

    private String body;

    private String slug;

    private String description;

    private int favoritesCount;

    @Embedded
    private Profile author;

    @ManyToOne(fetch = FetchType.LAZY)
    private User favoriter;
}
