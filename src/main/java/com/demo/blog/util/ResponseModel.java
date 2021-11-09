package com.demo.blog.util;

import com.demo.blog.domain.entity.BaseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ResponseModel {

    public static String asJson(Object object) {
        String result = "";
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        try {
            result = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            result = "";
        }
        return result;
    }
}
