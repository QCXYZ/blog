package com.blog.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {
    private boolean success;
    private int code;
    private String message;
    private T data;

    public static <T> R<T> ok(T data) {
        return new R<>(true, 200, "操作成功", data);
    }

    public static <T> R<T> fail(int code, String message) {
        return new R<>(false, code, message, null);
    }
}
