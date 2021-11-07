package com.demo.blog.domain.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AttributeOverride(name="id", column = @Column(name="article_id"))
public class Article extends BaseEntity{

    private String title;

    private String body;

    private String slug;

    private String description;

    private int favoritesCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @OneToMany(mappedBy = "article")
    private List<ArticleUser> favoriters = new ArrayList<>();
}
