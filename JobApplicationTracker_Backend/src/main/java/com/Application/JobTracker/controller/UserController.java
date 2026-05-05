package com.Application.JobTracker.controller;

import com.Application.JobTracker.dto.ApiResponse;
import com.Application.JobTracker.dto.UserRequestDTO;
import com.Application.JobTracker.entity.User;
import com.Application.JobTracker.service.UserService;
import com.Application.JobTracker.service.impl.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ApiResponse<User> registerUser(@RequestBody UserRequestDTO user){
        return ApiResponse.<User>builder()
                .Success(true)
                .message("user successfully registered")
                .data(userService.registerUser(user))
                .build();
    }

    @GetMapping("/login")
    public String login(@RequestBody UserRequestDTO user){
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        if(authentication.isAuthenticated()){
           return jwtService.generateToken(user.getEmail());
        }
        else
            return "invalid credentials";
    }
}
