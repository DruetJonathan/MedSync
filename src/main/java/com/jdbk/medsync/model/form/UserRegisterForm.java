package com.jdbk.medsync.model.form;

import com.jdbk.medsync.model.Enum.Role;
import com.jdbk.medsync.model.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.aspectj.lang.annotation.Before;

import java.util.Date;

@Data
public class UserRegisterForm {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String password;
    @NotNull
    @Past
    private Date birthdate;
    @NotNull
    private Role role;
    @NotNull
    private String numeroTelephone;

    public User toEntity(){
        User user = new User();
        user.setEmail(this.email);
        user.setFirstname(this.firstname);
        user.setLastname(this.lastname);
        user.setPassword(this.password);
        user.setBirthdate(this.birthdate);
        user.setRole(this.role);
        user.setNumeroTelephone(this.numeroTelephone);
        return user;
    }
}
