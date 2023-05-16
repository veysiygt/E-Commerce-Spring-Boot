package com.Softito.ecommerce.api.controller;

import com.Softito.ecommerce.dto.LoginRequestDTO;
import com.Softito.ecommerce.dto.LoginResponseDTO;
import com.Softito.ecommerce.dto.RegisterDTO;
import com.Softito.ecommerce.exception.RegisterException;
import com.Softito.ecommerce.model.User;
import com.Softito.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;
    private User user;


    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
        try {
            userService.registerUser(registerDTO);
            return ResponseEntity.ok().build();
        } catch (RegisterException existsException){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        String jwt = userService.loginUser(loginRequestDTO);
        if (jwt==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            LoginResponseDTO responseDTO = new LoginResponseDTO();
            responseDTO.setJwt(jwt);
            return ResponseEntity.ok(responseDTO);
        }
    }

    @GetMapping("/profile")
    public User getLoggedUserProfile(@AuthenticationPrincipal User user){
        return user;
    }

}
