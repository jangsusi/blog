package com.demo.blog.controller;


import com.demo.blog.domain.model.UserRequestDto;
import com.demo.blog.domain.model.UserResponseDto;
import com.demo.blog.service.UserService;
import com.demo.blog.util.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.websocket.Session;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path ="/user")
    @Transactional(readOnly = true)
    public String getCurrentUser() {
//        UserResponseDto response = userService.getUser(session.getId());
        return "/error/404";
//        return ResponseModel.asJson(response);
    }

    @PostMapping(path ="/user/login")
    @Transactional
    public void updateUser(Session session, UserRequestDto userRequestDto) {
        userService.updateUser(session.getId(), userRequestDto);
    }
}
