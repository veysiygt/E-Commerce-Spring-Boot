package com.Softito.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginRequestDTO {
    @NotBlank
    @NotNull
    @Size(min = 3, max = 255, message = "Username must be min 3 characters long")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "First Name must not contain numbers or special characters")
    public String username;
    @NotNull
    @NotBlank
    @Size(min = 6, max = 32, message = "Password must be a minimum of 6 characters, contain at least one letter and at least one number")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")
    private String password;

}
