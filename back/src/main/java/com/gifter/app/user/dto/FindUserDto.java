package com.gifter.app.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FindUserDto {
    @Size(max=255, min=3)
    @NotNull
    private String username;
}
