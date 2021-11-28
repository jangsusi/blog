package com.demo.blog.service;

import com.demo.blog.domain.model.ProfileResponseDto;
import com.demo.blog.domain.model.UserRequestDto;
import com.demo.blog.domain.model.UserResponseDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    UserResponseDto getUser(String token);

    UserResponseDto updateUser(String token, UserRequestDto userRequestDto);

    UserResponseDto registUser(HttpServletResponse response, UserRequestDto userRequestDto);

    List<ProfileResponseDto> getProfile(String searchedUserName, String searcherToken);

    List<ProfileResponseDto> followUser(String username, String token);

    List<ProfileResponseDto> unfollowUser(String username, String token);
}
