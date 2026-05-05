package com.Application.JobTracker.service.impl;

import com.Application.JobTracker.dto.UserRequestDTO;
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

    public User registerUser(UserRequestDTO user) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
        User userMain=new User();
        userMain.setPassword(encoder.encode(user.getPassword()));
        userMain.setUsername(user.getUsername());
        userMain.setEmail(user.getEmail());

       return userRepository.save(userMain);
    }

}
