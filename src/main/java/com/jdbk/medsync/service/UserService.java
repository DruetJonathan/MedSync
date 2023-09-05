package com.jdbk.medsync.service;

import com.jdbk.medsync.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User login(User user);
}
