package com.blog.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private int errorCode;
    private String errorMessage;

    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
