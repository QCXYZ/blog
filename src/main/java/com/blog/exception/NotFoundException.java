package com.blog.exception;

import lombok.Getter;

// 自定义业务异常类
@Getter
public class NotFoundException extends RuntimeException {
    private final int code;

    public NotFoundException(String message) {
        super(message);
        this.code = 404; // HTTP 404 Not Found
    }

    public NotFoundException(int code, String message) {
        super(message);
        this.code = code;
    }
}
