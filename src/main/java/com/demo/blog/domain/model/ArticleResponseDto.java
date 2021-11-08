package com.demo.blog.domain.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonRootName(value = "article")
public class ArticleResponseDto {

    private String slug;
    private String title;
    private String description;
    private String body;
    private List<String> tagList = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean favorited;
    private long favoritesCount;
    private ProfileResponseDto author;
}
