package com.jdbk.medsync.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jdbk.medsync.model.Enum.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter@Setter
@Table(name = "Users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private LocalDateTime birthdate;
    private String numeroTelephone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "demandeur")
    @JsonBackReference

    private Set<Demande> demandes;


    @OneToMany(mappedBy = "creePar", cascade = CascadeType.ALL)
    private Set<RendezVous> rendezVous;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
