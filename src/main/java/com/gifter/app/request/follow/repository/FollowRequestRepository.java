package com.gifter.app.request.follow.repository;

import com.gifter.app.request.follow.entity.FollowRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRequestRepository extends JpaRepository<FollowRequest, Long> {
    // Which requests came to me
    @Query("select fr from FollowRequest fr where fr.userDestination.id = :userId")
    List<FollowRequest> findUserFollowRequests(@Param("userId")Long userId);
}
