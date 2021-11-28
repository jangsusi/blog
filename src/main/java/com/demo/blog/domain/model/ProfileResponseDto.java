package com.demo.blog.domain.model;

import com.demo.blog.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonRootName(value = "profile")
public class ProfileResponseDto {

    private String userName;
    private String bio;
    private String image;
    private boolean following;

    public ProfileResponseDto(User user, User targetUser) {
        List<User> followers = targetUser.getFollowers();
        this.following = followers.stream()
                .anyMatch(follower -> {
                    return follower.getEmail().equals(user.getEmail());
                });
        this.userName = targetUser.getUserName();
        this.bio = targetUser.getBio();
        this.image = targetUser.getImage();


////        List<FollowerFollowing> followInfos = user.getFollowings();
//        followers.stream()
//                .anyMatch(follower -> {
//                    return info.getFollowing();
//                });

    }
}
