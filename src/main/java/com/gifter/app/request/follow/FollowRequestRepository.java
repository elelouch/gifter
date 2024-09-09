package com.gifter.app.request.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRequestRepository extends JpaRepository<FollowRequest, Long> {
    // Which requests came to me
    @Query("select fr from FollowRequest fr where fr.destination.id = ?1")
    List<FollowRequest> findUserFollowRequests(Long userId);
}
