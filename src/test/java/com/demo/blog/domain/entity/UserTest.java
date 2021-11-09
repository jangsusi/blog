package com.demo.blog.domain.entity;

import com.demo.blog.domain.model.UserResponseDto;
import com.demo.blog.util.ResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

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
}