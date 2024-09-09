package com.gifter.app.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginDto {
    @Email
    @Length(max = 255, min = 3)
    @NotNull
    private String email;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    @Length(max = 255, min = 3)
    @NotNull
    private String password;
}
