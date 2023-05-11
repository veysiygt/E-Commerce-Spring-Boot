package com.Softito.ecommerce.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    @NotBlank
    @NotNull
    @Size(min = 3, max = 255, message = "Username must be min 3 characters long")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "First Name must not contain numbers or special characters")
    public String username;
    @NotBlank
    @NotNull
    @Email
    @Size(message = "Email cannot exceed 320 characters.")
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20, message = "First Name must be min 5 characters long")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "First Name must not contain numbers or special characters")
    private String firstName;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20, message = "Last Name must be min 5 characters long")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Last Name must not contain numbers or special characters")
    private String lastName;
    @NotNull
    @NotBlank
    @Size(min = 6, max = 32, message = "Password must be a minimum of 6 characters, contain at least one letter and at least one number")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")
    private String password;
}
