package com.demo.blog.service;

import com.demo.blog.domain.model.UserRequestDto;
import com.demo.blog.domain.model.UserResponseDto;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

public interface UserService {

    UserResponseDto getUser(String token);

    UserResponseDto updateUser(String token, UserRequestDto userRequestDto);

    UserResponseDto registUser(HttpServletResponse response, UserRequestDto userRequestDto);
}
