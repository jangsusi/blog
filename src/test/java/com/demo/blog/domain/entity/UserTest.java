package com.demo.blog.domain.entity;

import com.demo.blog.controller.UserController;
import com.demo.blog.domain.model.UserRequestDto;
import com.demo.blog.domain.model.UserResponseDto;
import com.demo.blog.service.UserService;
import com.demo.blog.util.ResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.h2.engine.Procedure;
import org.h2.engine.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Autowired
    public UserController userController;

    @Autowired
    public UserService userService;

    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    void setUp(){
        System.out.println("df");
//        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void response_model_wrapping(){
        //given
        UserResponseDto user  = new UserResponseDto();
        user.setBio("bio");
        user.setEmail("email");
        user.setImage("image");
        user.setToken("token");
        user.setUserName("username");

        //when
        String result = ResponseModel.asJson(user);

        //then
        Assertions.assertNotEquals(result, "");
    }

    @Test
    void api_test() {
        try{
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();
                });
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}