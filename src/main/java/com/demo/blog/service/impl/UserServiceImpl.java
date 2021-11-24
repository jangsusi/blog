package com.demo.blog.service.impl;

import com.demo.blog.domain.entity.User;
import com.demo.blog.domain.model.UserRequestDto;
import com.demo.blog.domain.model.UserResponseDto;
import com.demo.blog.repository.UserRepository;
import com.demo.blog.security.JWTToken;
import com.demo.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUser(String token) {
        validateUser(token);
        User user = userRepository.findById(token).orElse(new User());
        UserResponseDto userResponseDto = new UserResponseDto(user);
        return userResponseDto;
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(String token, UserRequestDto userRequestDto) {
        validateUser(token);
        String email = userRequestDto.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            new IllegalStateException("No User Email" + email);
            return null;
        });

        user.update(userRequestDto);
        UserResponseDto userResponseDto = new UserResponseDto(user);
        return userResponseDto;
    }

    @Override
    @Transactional
    public UserResponseDto registUser(HttpServletResponse response, UserRequestDto userRequestDto) {
        String token = JWTToken.makeToken(userRequestDto);
        User user = new User(userRequestDto, token);
        userRepository.save(user);
        response.addCookie(new Cookie("token", token));
        UserResponseDto userResponseDto = new UserResponseDto(user);
        return userResponseDto;
    }

    private void validateUser(String token){
        boolean isValidated = JWTToken.validateToken(token);

        if(!isValidated){
            throw new RuntimeException("No Validated User");
        }
    }


}
