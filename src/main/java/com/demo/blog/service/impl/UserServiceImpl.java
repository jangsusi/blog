package com.demo.blog.service.impl;

import com.demo.blog.domain.entity.User;
import com.demo.blog.domain.model.UserRequestDto;
import com.demo.blog.domain.model.UserResponseDto;
import com.demo.blog.repository.UserRepository;
import com.demo.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.websocket.Session;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto getUser(String id) {
        User user = userRepository.findById(id).orElse(new User());
        return user.toDto();
    }

    @Override
    public void updateUser(String id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElse(new User());
        user.update(userRequestDto);
    }


}
