package com.demo.blog.domain.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonRootName(value = "user")
public class UserResponseDto {

    private String email;
    private String token;
    private String userName;
    private String bio;
    private String image;

}
