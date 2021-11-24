package com.demo.blog.security;

import com.demo.blog.domain.model.UserRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JWTTokenTest {

    @Test
    void token_생성(){
        //given
        UserRequestDto userRequestDto = new UserRequestDto();
        String jwt = JWTToken.makeToken(userRequestDto);

        //when
        Map<String, Object> claims = JWTToken.getClaims(jwt);

        //then
        Assertions.assertNotEquals(null, claims.get("data"));
    }
}