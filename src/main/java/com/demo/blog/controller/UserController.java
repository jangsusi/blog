package com.demo.blog.controller;


import com.demo.blog.domain.model.ProfileResponseDto;
import com.demo.blog.domain.model.UserRequestDto;
import com.demo.blog.domain.model.UserResponseDto;
import com.demo.blog.security.JWTToken;
import com.demo.blog.service.UserService;
import com.demo.blog.util.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path ="/api/user")
    @Transactional(readOnly = true)
    public String getCurrentUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        userService.getUser(cookies[0].getValue());
//        UserResponseDto response = userService.getUser(session.getId());
        return "/error/404";
//        return ResponseModel.asJson(response);
    }

    @PostMapping(path ="/user/login")
    @Transactional
    public void updateUser(HttpServletRequest request, UserRequestDto userRequestDto) {
        userService.updateUser(request.getCookies()[0].getValue(), userRequestDto);
    }

    @PostMapping("/api/users")
    public UserResponseDto registUser(@RequestBody UserRequestDto userRequestDto, HttpServletResponse response){
        UserResponseDto userResponseDto = userService.registUser(response, userRequestDto);
        return userResponseDto;
    }

    @GetMapping(path = "/api/profiles/{username}")
    public List<ProfileResponseDto> getUserInfo(@PathVariable String username, HttpServletRequest request){
        String searcherToken = request.getCookies()[0].getValue();
        List<ProfileResponseDto> responseDto = userService.getProfile(username, searcherToken);
        return responseDto;
    }

    @PostMapping(path = "/api/profiles/{username}/follow")
    public List<ProfileResponseDto> followUser(@PathVariable String username, HttpServletRequest request){
        String token = request.getCookies()[0].getValue();
        List<ProfileResponseDto> responseDto = userService.followUser(username, token);
        return responseDto;
    }

    @DeleteMapping(path = "/api/profiles/{username}/follow")
    public List<ProfileResponseDto> unfollowUser(@PathVariable String username, HttpServletRequest request){
        String token = request.getCookies()[0].getValue();
        List<ProfileResponseDto> responseDto = userService.unfollowUser(username, token);
        return responseDto;
    }
}
