package com.gifter.app.user.repository;

import com.gifter.app.user.entity.GifterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<GifterUser, Long> {
    Optional<GifterUser> findByEmailOrUsername(String email, String username);
    Optional<GifterUser> findByEmail(String email);
    Optional<GifterUser> findByUsername(String username);
    @Query("select gf from GifterUser gf where gf.username like %:username% and gf.role <> 'admin'")
    List<GifterUser> getByLikeUsername(@Param("username") String username);
}
