package com.jdbk.medsync.service;

import com.jdbk.medsync.model.User;
import com.jdbk.medsync.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository ;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User login(User user) {
        User existingUser = userRepository.getUserByEmail(user.getEmail()).orElseThrow();
        if(!existingUser.getPassword().equals(user.getPassword())){
            throw new RuntimeException();
        }
        return existingUser;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.getUserByEmail(email).orElseThrow();
    }
}
