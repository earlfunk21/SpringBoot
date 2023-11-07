package com.practice.security.controller;

import com.practice.security.dto.request.SignInRequest;
import com.practice.security.dto.request.SignUpRequest;
import com.practice.security.dto.response.ApiResponse;
import com.practice.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody SignUpRequest request) {
        try {
            return ResponseEntity.ok(ApiResponse.success("Successfully Generated Users",
                    authenticationService.signup(request)));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error("An error occurred", e.getMessage()));
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse> signin(@RequestBody SignInRequest request) {
        try {
            String token = authenticationService.signin(request);
            return ResponseEntity.ok(ApiResponse.success("Successfully Generate Token", token));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error("Authentication failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("An error occurred", e.getMessage()));
        }
    }

}