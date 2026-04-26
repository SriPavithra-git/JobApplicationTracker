package com.Application.JobTracker.controller;

import com.Application.JobTracker.dto.ApiResponse;
import com.Application.JobTracker.entity.User;
import com.Application.JobTracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ApiResponse<User> registerUser(@RequestBody User user){
        return ApiResponse.<User>builder()
                .Success(true)
                .message("user successfully registered")
                .data(userService.registerUser(user))
                .build();
    }
}
