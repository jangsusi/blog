package com.demo.blog.domain.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonRootName(value = "profile")
public class ProfileResponseDto {

    private String userName;
    private String bio;
    private String image;
    private boolean following;
}
