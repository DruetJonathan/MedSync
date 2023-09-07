package com.jdbk.medsync.service.impl;

import com.jdbk.medsync.exception.AlreadyExistException;
import com.jdbk.medsync.exception.NotFoundException;
import com.jdbk.medsync.exception.NotTheGoodPasswordException;
import com.jdbk.medsync.model.entity.User;
import com.jdbk.medsync.repository.UserRepository;
import com.jdbk.medsync.service.notImpl.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository ;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User login(User user) {
        User existingUser = userRepository.getUserByEmail(user.getEmail()).orElseThrow(()-> new NotFoundException(user.getId(),UserServiceImpl.class.toString()));
        if(!existingUser.getPassword().equals(user.getPassword())){
            throw new NotTheGoodPasswordException(user.getId(), user.getEmail());
        }
        return existingUser;
    }

    @Override
    public User getOne(Long idUser) {
        return userRepository.findById(idUser).orElseThrow(()->new NotFoundException(idUser,UserServiceImpl.class.toString()));
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Long register(User user) {
        user.setId(null);
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new AlreadyExistException(user.getId(),UserServiceImpl.class.toString());
        }
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.getUserByEmail(email).orElseThrow();
    }
}
