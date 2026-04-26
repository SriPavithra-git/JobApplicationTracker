package com.Application.JobTracker.service.impl;

import com.Application.JobTracker.entity.User;
import com.Application.JobTracker.repository.UserRepository;
import com.Application.JobTracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User registerUser(User user) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
        user.setPassword(encoder.encode(user.getPassword()));
       return userRepository.save(user);
    }
}
