package com.demo.blog.service.impl;

import com.demo.blog.domain.entity.User;
import com.demo.blog.domain.model.ProfileResponseDto;
import com.demo.blog.domain.model.UserRequestDto;
import com.demo.blog.domain.model.UserResponseDto;
import com.demo.blog.repository.UserRepository;
import com.demo.blog.security.JWTToken;
import com.demo.blog.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    public UserService userService;

    @Autowired
    public UserRepository userRepository;

    @Test
    void 사용자_등록_테스트(){
        //given
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setUserName("userName");
        userRequestDto.setEmail("email");
        userRequestDto.setPassword("pwd");
        HttpServletResponse httpServletResponse = null;
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();

        //when
        UserResponseDto userResponseDto = userService.registUser(mockHttpServletResponse, userRequestDto);
        User user = userRepository.findByEmail("email").orElseGet(null);

        //then
        Assertions.assertEquals(userResponseDto.getToken(), user.getToken());
    }

    @Test
    @Transactional
    void 팔로우_하지_않는_사용자_getProfile(){
        //given
        UserRequestDto searcherDto = new UserRequestDto("soobin", "soobinEmail", "password");
        String searcherToken = JWTToken.makeToken(searcherDto);
        User searcher = new User(searcherDto, searcherToken);
        userRepository.save(searcher);

        String searchedUserName = "hyobin";
        UserRequestDto searchedUserDto = new UserRequestDto(searchedUserName, "hyobinEmail", "password");
        String searchedToken = JWTToken.makeToken(searchedUserDto);
        User searchedUser = new User(searchedUserDto, searchedToken);
        userRepository.save(searchedUser);


        //when
        List<ProfileResponseDto> profiles= userService.getProfile(searchedUserName, searcherToken);

        //then
        Assertions.assertEquals(profiles.get(0).getUserName(), searchedUserName);
    }

    @Test
    @Transactional
    void 팔로우_하는_사용자_getProfile(){
        //given
        UserRequestDto searcherDto = new UserRequestDto("soobin", "soobinEmail", "password");
        String searcherToken = JWTToken.makeToken(searcherDto);
        User searcher = new User(searcherDto, searcherToken);
        userRepository.save(searcher);

        String searchedUserName = "hyobin";
        UserRequestDto searchedUserDto = new UserRequestDto(searchedUserName, "hyobinEmail", "password");
        String searchedToken = JWTToken.makeToken(searchedUserDto);
        User searchedUser = new User(searchedUserDto, searchedToken);
        searchedUser.getFollowers().add(searcher);
        User savedUser = userRepository.save(searchedUser);

        //when
        List<ProfileResponseDto> profiles= userService.getProfile(searchedUserName, searcherToken);

        //then
        Assertions.assertEquals(profiles.get(0).getUserName(), searchedUserName);
        Assertions.assertEquals(savedUser.getFollowers().get(0).getUserName(), "soobin");
        User user1 = userRepository.findByUserName("hyobin").get(0);
        user1.getFollowers().get(0).getUserName();
    }

    @Test
    @Transactional
    void 특정_사용자_follow(){
        //given
        UserRequestDto followerDto = new UserRequestDto("soobin", "soobinEmail", "password");
        String followerToken = JWTToken.makeToken(followerDto);
        User follower = new User(followerDto, followerToken);
        userRepository.save(follower);

        String followingUserName = "hyobin";
        UserRequestDto searchedUserDto = new UserRequestDto(followingUserName, "hyobinEmail", "password");
        String followingToken = JWTToken.makeToken(searchedUserDto);
        User followingUser = new User(searchedUserDto, followingToken);
        User savedUser = userRepository.save(followingUser);

        //when
        List<ProfileResponseDto> profiles = userService.followUser(followingUserName, followerToken);

        //then
        Assertions.assertEquals(profiles.get(0).getUserName(), followingUserName);
        Assertions.assertEquals(profiles.get(0).isFollowing(), true);
    }

    @Test
    @Transactional
    void 특정_사용자_unfollow(){
        //given
        UserRequestDto followerDto = new UserRequestDto("soobin", "soobinEmail", "password");
        String followerToken = JWTToken.makeToken(followerDto);
        User follower = new User(followerDto, followerToken);
        userRepository.save(follower);

        String followingUserName = "hyobin";
        UserRequestDto searchedUserDto = new UserRequestDto(followingUserName, "hyobinEmail", "password");
        String followingToken = JWTToken.makeToken(searchedUserDto);
        User followingUser = new User(searchedUserDto, followingToken);
        User savedUser = userRepository.save(followingUser);

        //when
        List<ProfileResponseDto> profiles = userService.unfollowUser(followingUserName, followerToken);

        //then
        Assertions.assertEquals(profiles.get(0).getUserName(), followingUserName);
        Assertions.assertEquals(profiles.get(0).isFollowing(), false);
    }
}