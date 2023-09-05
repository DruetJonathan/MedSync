package com.jdbk.medsync.service;

import com.jdbk.medsync.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User login(User user);

    User getOne(Long idUser);

    List<User> getAllUser();

    Long register(User user);

}
