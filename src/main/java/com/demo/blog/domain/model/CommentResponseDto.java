package com.demo.blog.domain.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonRootName(value = "comment")
public class CommentResponseDto {

    private long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String body;
    private ProfileResponseDto author;
}
