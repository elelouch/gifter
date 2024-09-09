package com.gifter.app.user.dto;

import com.gifter.app.gift.entity.Gift;
import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
public class GifterUserDto {
    @NotNull
    private Long id;
    @Size(max=255, min=3)
    @Pattern(regexp="^[a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*$")
    @NotNull
    private String username;
    @Size(max=255, min=3)
    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    @NotNull
    private String password;
    private boolean enabled;
    private String firstName;
    private String lastName;
    @Size(max=255, min=3)
    @Email
    @NotNull
    private String email;
    private Role role;

    public static GifterUserDto fromEntity(GifterUser entity) {
        GifterUserDto dto = new GifterUserDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setUsername(entity.getUsername());
        dto.setRole(entity.getRole());
        dto.setEnabled(entity.isEnabled());
        return dto;
    }

    public static List<GifterUserDto> fromEntity(Collection<GifterUser> collection) {
        ArrayList<GifterUserDto> dtoList = new ArrayList<>(collection.size());
        for (GifterUser user : collection) {
            dtoList.add(fromEntity(user));
        }
        return dtoList;
    }

}
