package com.demo.blog.controller;


import com.demo.blog.domain.model.UserRequestDto;
import com.demo.blog.domain.model.UserResponseDto;
import com.demo.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.websocket.Session;
import javax.xml.ws.ResponseWrapper;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path ="/user")
    @Transactional(readOnly = true)
    public String getCurrentUser(Session session) {
        String result = "";
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        try {
            result = mapper.writeValueAsString(userService.getUser(session.getId()));
        } catch (JsonProcessingException e) {
            result = "";
        }
        return result;
    }

    @PostMapping(path ="/user")
    @Transactional
    public void updateUser(Session session, UserRequestDto userRequestDto) {
        userService.updateUser(session.getId(), userRequestDto);
    }
}
