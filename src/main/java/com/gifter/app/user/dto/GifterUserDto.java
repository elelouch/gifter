package com.gifter.app.user.dto;

import com.gifter.app.gift.entity.Gift;
import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.entity.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
public class GifterUserDto {
    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private String firstName;
    private String lastName;
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
