package com.demo.blog.controller;

import com.demo.blog.security.JWTToken;
import com.demo.blog.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
class UserControllerTest {

    public MockMvc mockMvc;

    @Autowired
    public UserService userService;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService))
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    void 유저_등록_api() throws Exception {
        //given
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{").append(" \"email\" :  \"email\", ")
                .append(" \"userName\" : \"userName\", ")
                .append(" \"password\" : \"pwd\" ")
                .append("}");

        //when
        ResultActions actions = mockMvc.perform(
                post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(stringBuilder.toString()));

        //then
        actions.andExpect(MockMvcResultMatchers.cookie().value("jwt", "sdf"));
    }
}