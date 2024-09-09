package com.gifter.app.request.follow;

import jakarta.validation.Valid;

import java.util.List;

public interface FollowRequestService {
    void removeFollowRequest(Long id);

    List<UserFollowRequestDto> getUserFollowRequests();

    void createFollowRequest(@Valid FollowRequestDto followRequest);

    void useFollowRequest(Long id);
}
