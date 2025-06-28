package com.jetstream.learningmanagementservice.controller;

import com.jetstream.learningmanagementservice.request.UserCreateRequest;
import com.jetstream.learningmanagementservice.response.UserResponse;
import com.jetstream.learningmanagementservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lms/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserCreateRequest request) {
        UserResponse createdUser = userService.registerUser(request);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}