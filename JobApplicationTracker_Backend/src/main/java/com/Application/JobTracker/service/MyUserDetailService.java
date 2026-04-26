package com.Application.JobTracker.service;

import com.Application.JobTracker.entity.User;
import com.Application.JobTracker.entity.UserPrincipal;
import com.Application.JobTracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByEmail(username);
        User userPresent;
        if(user.isEmpty()){
            throw new UsernameNotFoundException("user not found");
        }
        else{
            userPresent=user.get();
        }
        return new UserPrincipal(userPresent);
    }
}
