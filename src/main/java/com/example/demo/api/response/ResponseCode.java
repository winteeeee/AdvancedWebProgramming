package com.example.demo.api.response;

import lombok.Getter;

@Getter
public enum ResponseCode {
    OK(200000, "OK"),
    Created(201000, "Created");
    private final Integer code;
    private final String message;
    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;

    }
}

