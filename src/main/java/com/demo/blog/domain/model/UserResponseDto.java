package com.demo.blog.domain.model;

import lombok.Setter;

@Setter
public class UserResponseDto {

    private String email;
    private String token;
    private String userName;
    private String bio;
    private String image;

}
