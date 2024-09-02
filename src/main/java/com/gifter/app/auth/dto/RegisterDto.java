package com.gifter.app.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import jakarta.validation.constraints.Size;

@Data
public class RegisterDto {
    @Size(max=255, min=3)
    @Pattern(regexp="^[a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*$")
    @NotNull
    private String username;
    @Size(max=255, min=3)
    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    @NotNull
    private String password;
    @Size(max=255, min=3)
    @Email
    @NotNull
    private String email;
    @Pattern(regexp = "([A-Z])\\w+")
    @Size(max = 255, min = 3)
    private String firstName;
    @Pattern(regexp = "([A-Z])\\w+")
    @Size(max = 255, min = 3)
    private String lastName;
}
