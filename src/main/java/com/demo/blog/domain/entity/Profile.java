package com.demo.blog.domain.entity;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class Profile {

    private String userName;

    private String bio;

    private String image;

    private boolean following;
}
