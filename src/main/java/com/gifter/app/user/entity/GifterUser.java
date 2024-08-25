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

@Data
@Entity
public class GifterUser {
    @Id
    private Long id;
    private String username;
    private String password;
    private boolean active;
    private String firstName;
    private String lastName;
    private String email;
}
