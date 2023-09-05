package com.jdbk.medsync.model.form;

import com.jdbk.medsync.model.entity.User;
import lombok.Data;

@Data
public class UserLoginForm {
    private String email;
    private String password;

    public User toEntity() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
