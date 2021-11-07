package com.demo.blog.service;

import com.demo.blog.domain.model.UserRequestDto;
import com.demo.blog.domain.model.UserResponseDto;

import javax.websocket.Session;

public interface UserService {

    UserResponseDto getUser(String id);

    void updateUser(String id, UserRequestDto userRequestDto);
}
