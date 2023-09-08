package com.jdbk.medsync.controller;

import com.jdbk.medsync.model.DTO.UserDTO;
import com.jdbk.medsync.model.DTO.UserTokenDTO;
import com.jdbk.medsync.model.entity.User;
import com.jdbk.medsync.model.form.UserLoginForm;
import com.jdbk.medsync.model.form.UserRegisterForm;
import com.jdbk.medsync.service.notImpl.UserService;
import com.jdbk.medsync.utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class UserController {
    private UserService userService;
    private JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenDTO> login(@RequestBody @Valid UserLoginForm userLoginForm) {
        User user = userService.login(userLoginForm.toEntity());
        UserTokenDTO dto = UserTokenDTO.fromEntity(user);
        dto.setToken(jwtUtil.generateToken(user));
        return ResponseEntity.status(HttpStatus.OK).body(dto);

    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody @Valid UserRegisterForm userRegisterForm) {
        User user = userRegisterForm.toEntity();
        Long register = userService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user.getId());

    }

    @GetMapping("/user/{id:[0-9]+}")
    @PreAuthorize("hasAnyAuthority('MEDECIN','ADMINISTRATIF')")
    public ResponseEntity<UserDTO> getOne( @RequestBody @PathVariable Long id) {
        User user = userService.getOne(id);
        UserDTO userDTO = UserDTO.toDTO(user);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }


}
