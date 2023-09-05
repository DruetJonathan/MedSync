package com.jdbk.medsync.repository;

import com.jdbk.medsync.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> getUserByEmail(String email);


    boolean existsByEmail(String s);
}

