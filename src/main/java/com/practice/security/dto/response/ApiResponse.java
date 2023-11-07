package com.practice.security.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse {
    private int status;
    private String message;
    private Object data;

    public ApiResponse(int status, String message){
        this.status = status;
        this.message = message;
    }

    public ApiResponse(int status, String message, Object data){
        this.status = status;
        this.message = message;
        this.data = data;
    }


    public ApiResponse(Object data){
        this.data = data;
    }

    public static ApiResponse success(String message, Object data) {
        return new ApiResponse(HttpStatus.OK.value(), message, data);
    }

    public static ApiResponse error(String message, Object data) {
        return new ApiResponse(HttpStatus.BAD_REQUEST.value(), message, data);
    }

    public static ApiResponse error(int status, String message, Object data) {
        return new ApiResponse(status, message, data);
    }
}