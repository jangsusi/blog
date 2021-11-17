package com.demo.blog.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class JWTTokenTest {

    @Test
    void token_생성(){
        //given
        String jwt = JWTToken.makeToken();

        //when
        Map<String, Object> claims = JWTToken.getClaims(jwt);

        //then
        Assertions.assertNotEquals(null, claims.get("data"));
    }

}