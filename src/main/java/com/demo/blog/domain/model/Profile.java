package com.demo.blog.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profile {

    private String userName;

    private String bio;

    private String image;

    private boolean following;
}
