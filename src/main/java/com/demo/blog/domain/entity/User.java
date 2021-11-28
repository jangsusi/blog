package com.demo.blog.domain.entity;

import com.demo.blog.domain.model.UserRequestDto;
import com.demo.blog.domain.model.UserResponseDto;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User extends BaseEntity {

    private String userName;

    private String email;

    private String bio;

    private String image;

    private String token;

    private String password;

    @ManyToMany(mappedBy = "followers")
    private List<User> followings = new ArrayList<>();

//    @OneToMany(mappedBy = "follower")
//    private List<FollowerFollowing> followers = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ArticleUser> articles = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="user_relations",
        joinColumns = @JoinColumn(name = "followed_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private List<User> followers = new ArrayList<>();

    public User(UserRequestDto userRequestDto, String token) {
        this.userName = userRequestDto.getUserName();
        this.email = userRequestDto.getEmail();
        this.password = userRequestDto.getPassword();
        this.token = token;
    }

    public void update(UserRequestDto userRequestDto) {
        if(userRequestDto.getUserName() != null){
            this.userName = userRequestDto.getUserName();
        }
        if(userRequestDto.getEmail() != null){
            this.email = userRequestDto.getEmail();
        }
        if(userRequestDto.getBio() != null){
            this.bio = userRequestDto.getBio();
        }
        if(userRequestDto.getImage() != null){
            this.image = userRequestDto.getImage();
        }
        if(userRequestDto.getBio() != null){
            this.bio = userRequestDto.getBio();
        }
    }

    public static class Builder{
        private String email;
        private String token;
        private String userName;
        private String bio;
        private String image;
        private String password;

        public Builder(String userName){
            this.userName = userName;
        }

        public Builder setEmail(String email){
            this.email = email;
            return this;
        }

        public Builder setToken(String token){
            this.token = token;
            return this;
        }

        public Builder setBio(String bio){
            this.bio = bio;
            return this;
        }

        public Builder setImage(String image){
            this.image = image;
            return this;
        }

        public Builder setPassword(String password){
            this.password = password;
            return this;
        }

        public User build(){
            return new User(userName, email, bio, image, token, password,  new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }
    }
}
