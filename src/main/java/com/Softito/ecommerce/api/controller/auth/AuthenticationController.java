package com.Softito.ecommerce.api.controller.auth;

import com.Softito.ecommerce.api.dto.RegisterDTO;
import com.Softito.ecommerce.exception.RegisterException;
import com.Softito.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
        try {
            userService.registerUser(registerDTO);
            return ResponseEntity.ok().build();
        } catch (RegisterException existsException){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
