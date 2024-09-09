package com.gifter.app.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUserDto {
    private String firstName;
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String username;
    @NotNull
    private String password;

}
