package com.jdbk.medsync.model.DTO;

import com.jdbk.medsync.model.Enum.Role;
import com.jdbk.medsync.model.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDTO {


    private String email;
    private String firstname;
    private String lastname;
    private LocalDateTime birthdate;
    private String numeroTelephone;
    private Role role;

    public static UserDTO toDTO(User user){

        if( user == null )
            return null;

        return UserDTO.builder()
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .birthdate(user.getBirthdate())
                .numeroTelephone(user.getNumeroTelephone())
                .role(user.getRole())
                .build();
    }
}
