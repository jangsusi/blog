package com.demo.blog.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {
    private String email;
    private String token;
    private String userName;
    private String bio;
    private String image;
    private String password;

    public UserRequestDto(String userName, String email, String password){
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
