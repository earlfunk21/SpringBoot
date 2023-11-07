package com.practice.security.controller;

import java.util.List;

import com.practice.security.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.practice.security.entity.User;
import com.practice.security.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<ApiResponse> getUsers() {
        try{
            return ResponseEntity.ok(ApiResponse.success("Successfully Generated Users", userService.getUsers()));
        } catch (Exception e){
            return ResponseEntity.ok(ApiResponse.error("An error occurred", e.getMessage()));
        }
    }
}