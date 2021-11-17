package com.demo.blog.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JWTToken {
    private static String key = "soobin";

    public static String makeToken(){

        //Header
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        //payload
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("data", "First");

        Long expiredTime = 1000 * 60L * 60L * 2L; //2hours

        Date ext = new Date();
        ext.setTime(ext.getTime() + expiredTime);

        //token builder
        String jwt = Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setSubject("user") //token용도
                .setExpiration(ext)
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .compact();

        return jwt;
    }

    public static boolean validateToken(String jwt){
        Map<String, Object> claimsMap = null;

        claimsMap = getClaims(jwt);

        if(Objects.isNull(claimsMap)){
            return false;
        }

        return true;
    }

    public static Map<String, Object> getClaims(String jwt){
        Map<String, Object> claimsMap = null;

        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(key.getBytes("UTF-8"))
                    .parseClaimsJws(jwt)
                    .getBody();

            claimsMap = claims;
            return claimsMap;

        } catch(ExpiredJwtException e){
            System.out.println("expired");
            return null;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
