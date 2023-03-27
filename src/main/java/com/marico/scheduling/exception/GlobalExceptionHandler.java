package com.marico.scheduling.exception;

import com.marico.scheduling.model.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by marico on 2023-03-27
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public RespBean handle(ApiException e) {
        if (e.getErrorCode() != null) {
            return RespBean.error(e.getErrorCode(), e.getMessage(), null);
        }
        return RespBean.error(e.getMessage());
    }
}
