package com.jdbk.medsync.service;

import com.jdbk.medsync.exception.AlreadyExistException;
import com.jdbk.medsync.exception.NotFoundException;
import com.jdbk.medsync.exception.NotTheGoodPasswordException;
import com.jdbk.medsync.model.entity.User;
import com.jdbk.medsync.repository.UserRepository;
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
        User existingUser = userRepository.getUserByEmail(user.getEmail()).orElseThrow(()-> new NotFoundException("user with email not found"));
        if(!existingUser.getPassword().equals(user.getPassword())){
            throw new NotTheGoodPasswordException("User enter the wrong password");
        }
        return existingUser;
    }

    @Override
    public User getOne(Long idUser) {
        return userRepository.findById(idUser).orElseThrow(()->new NotFoundException("User not found"));
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Long register(User user) {
        user.setId(null);
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new AlreadyExistException("User with email already exist");
        }
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.getUserByEmail(email).orElseThrow();
    }
}
