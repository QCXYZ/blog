package com.blog.exception;

import com.blog.util.EE;
import com.blog.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public R<EE> handleException(Exception e, HttpServletRequest request) {
        log.error("请求地址：{}", request.getRequestURI());
        log.error("异常信息：", e);
        if (e instanceof HttpMediaTypeNotSupportedException) {
            return R.fail(EE.不支持的媒体类型);
        } else if (e instanceof AccessDeniedException) {
            return R.fail(EE.访问被拒绝);
        } else if (e instanceof IllegalArgumentException) {
            return R.fail(400, e.getMessage());
        } else if (e instanceof ArithmeticException) {
            return R.fail(500, "数学运算异常");
        } else {
            return R.fail(EE.服务器内部错误);
        }
    }
}
