package com.marico.scheduling.exception;

/**
 * Created by marico on 2023-03-27
 */

public class ApiException extends RuntimeException {
    private Integer errorCode;
    private String message;

    public ApiException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
