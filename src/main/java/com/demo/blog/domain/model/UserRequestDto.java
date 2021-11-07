package com.demo.blog.domain.model;

import lombok.Getter;

@Getter
public class UserRequestDto {
    private String email;
    private String token;
    private String userName;
    private String bio;
    private String image;


}
