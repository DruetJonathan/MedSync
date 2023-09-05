package com.jdbk.medsync.model.form;

import com.jdbk.medsync.model.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginForm {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public User toEntity() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
