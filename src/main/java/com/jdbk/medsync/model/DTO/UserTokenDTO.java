package com.jdbk.medsync.model.DTO;

import com.jdbk.medsync.model.Enum.Role;
import com.jdbk.medsync.model.User;
import lombok.Data;

@Data
public class UserTokenDTO {
    private Long id;
    private String email;
    private Role role;
    private String token;

    public UserTokenDTO(Long id, String email, Role role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public static UserTokenDTO fromEntity(User user) {
        return new UserTokenDTO(user.getId(), user.getEmail(), user.getRole());
    }

    public void setToken(String s) {
        this.token = s;
    }
}
