package com.Application.JobTracker.service;

import com.Application.JobTracker.dto.UserRequestDTO;
import com.Application.JobTracker.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  {
    User registerUser(UserRequestDTO user);
}
