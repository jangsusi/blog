package com.demo.blog.domain.model;

import com.demo.blog.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@JsonRootName(value = "user")
@NoArgsConstructor
public class UserResponseDto {

    private String email;
    private String token;
    private String userName;
    private String bio;
    private String image;

    public UserResponseDto(User savedUser) {
        this.email = savedUser.getEmail();
        this.token = savedUser.getToken();
        this.userName = savedUser.getUserName();
    }
}
