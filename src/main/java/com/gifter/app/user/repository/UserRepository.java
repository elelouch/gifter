package com.gifter.app.user.repository;

import com.gifter.app.user.entity.GifterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<GifterUser, Long> {
    List<GifterUser> findByEmailOrUsername(String email, String username);

    @Query("select u from GifterUser u where u.email = ?1")
    GifterUser findByEmail(String email);
}
