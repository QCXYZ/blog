package com.blog.exception;

import com.blog.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 打印堆栈信息至日志
    private static void log(Exception e, HttpServletRequest request) {
        log.error("请求地址：{}", request.getRequestURI());
        log.error("异常信息：", e);
    }

    // 处理通用异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R<String> handleException(Exception e, HttpServletRequest request) {
        log(e, request);
        // 返回通用异常结果
        return R.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器内部错误");
    }

    // 处理请求方式不支持异常
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log(e, request);
        return R.fail(HttpStatus.BAD_REQUEST.value(), "请求方式不支持");
    }

    // 可以添加更多的异常处理器...

    // 处理自定义业务异常
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<String> handleBusinessException(NotFoundException e, HttpServletRequest request) {
        log(e, request);
        return R.fail(e.getCode(), e.getMessage());
    }
}
