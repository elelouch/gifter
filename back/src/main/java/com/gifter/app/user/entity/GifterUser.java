package com.gifter.app.user.entity;

import com.gifter.app.gift.entity.Gift;
import com.gifter.app.request.follow.entity.FollowRequest;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class GifterUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private String firstName;
    private String lastName;
    private String email;

    @ManyToMany(mappedBy = "followers")
    private Set<GifterUser> following;

    @ManyToMany
    @JoinTable(name = "following_followers", joinColumns = {@JoinColumn(name = "following_id")}, inverseJoinColumns = {@JoinColumn(name = "follower_id")})
    private Set<GifterUser> followers;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Gift> gifts;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }
}
