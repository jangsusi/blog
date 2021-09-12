package com.demo.blog.domain.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class User extends BaseEntity {

    private String userName;

    private String email;

    private String bio;

    private String image;

    private String token;

    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    private User following;
}
