package com.demo.blog.service.impl;

import com.demo.blog.domain.entity.User;
import com.demo.blog.domain.model.UserRequestDto;
import com.demo.blog.domain.model.UserResponseDto;
import com.demo.blog.repository.UserRepository;
import com.demo.blog.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    public UserService userService;

    @Autowired
    public UserRepository userRepository;

    @Test
    void 사용자_등록_테스트(){
        //given
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setUserName("userName");
        userRequestDto.setEmail("email");
        userRequestDto.setPassword("pwd");
        HttpServletResponse httpServletResponse = null;
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();

        //when
        UserResponseDto userResponseDto = userService.registUser(mockHttpServletResponse, userRequestDto);
        User user = userRepository.findByEmail("email").orElseGet(null);

        //then
        Assertions.assertEquals(userResponseDto.getToken(), user.getToken());
    }


}