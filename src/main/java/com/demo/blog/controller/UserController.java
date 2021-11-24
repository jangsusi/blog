package com.demo.blog.controller;


import com.demo.blog.domain.model.UserRequestDto;
import com.demo.blog.domain.model.UserResponseDto;
import com.demo.blog.security.JWTToken;
import com.demo.blog.service.UserService;
import com.demo.blog.util.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

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
}
