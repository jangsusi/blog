package com.demo.blog.service.impl;

import com.demo.blog.domain.entity.User;
import com.demo.blog.domain.model.ProfileResponseDto;
import com.demo.blog.domain.model.UserRequestDto;
import com.demo.blog.domain.model.UserResponseDto;
import com.demo.blog.repository.UserRepository;
import com.demo.blog.security.JWTToken;
import com.demo.blog.service.UserService;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            throw new IllegalStateException("No User Email" + email);
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

    @Override
    @Transactional(readOnly = true)
    public List<ProfileResponseDto> getProfile(String searchedUserName, String searcherToken) {
        //Authentication is Optional
        Map<String, Object> claims = JWTToken.getClaims(searcherToken);
        String email = (String) claims.get("email");
        User searcher = userRepository.findByEmail(email).orElse(new User());

        List<User> searchedUsers = userRepository.findByUserName(searchedUserName);
        List<ProfileResponseDto> profiles = searchedUsers.stream()
                .map(searchedUser -> new ProfileResponseDto(searcher, searchedUser))
                .collect(Collectors.toList());
        return profiles;
    }

    @Override
    @Transactional
    public List<ProfileResponseDto> followUser(String username, String token) {
        validateUser(token);

        User follower = userRepository.findByToken(token).orElseThrow(() -> {
            throw new IllegalStateException("No User");
        });

        List<User> users = userRepository.findByUserName(username);
        users.forEach(user -> user.getFollowers().add(follower));
        List<ProfileResponseDto> profiles = users.stream().map(user -> new ProfileResponseDto(follower, user)).collect(Collectors.toList());
        return profiles;
    }

    @Override
    @Transactional
    public List<ProfileResponseDto> unfollowUser(String username, String token) {
        validateUser(token);

        User follower = userRepository.findByToken(token).orElseThrow(() -> {
            throw new IllegalStateException("No User");
        });

        List<User> users = userRepository.findByUserName(username);
        users.forEach(user -> user.getFollowers().remove(follower));
        List<ProfileResponseDto> profiles = users.stream().map(user -> new ProfileResponseDto(follower, user)).collect(Collectors.toList());
        return profiles;
    }


}
