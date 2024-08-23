package com.gifter.app.user.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class GifterUser extends User {
    public GifterUser() {
        super("user", "password", new ArrayList<>());
    }

    public GifterUser(String username, String password, List<GrantedAuthority> authorities) {
        super("elias", "rojas", authorities);
    }

    private String firstName;
    private String lastName;
    private String email;
}
