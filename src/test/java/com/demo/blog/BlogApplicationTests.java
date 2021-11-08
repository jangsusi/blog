package com.demo.blog;

import com.demo.blog.domain.entity.User;
import com.demo.blog.domain.model.UserResponseDto;
import com.demo.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    public UserService userService;

    @Test
	void user_api() {
        //given
        User user  = new User.Builder("sl")
                            .setBio("bio")
                            .setEmail("email")
                            .setToken("token")
                            .setImage("image")
                            .setPassword("password")
                            .build();

        //when
        String result = "";
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        try {
            result = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            result = "";
        }

        //then
        Assertions.assertNotEquals(result, "");
	}

}
