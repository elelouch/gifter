package com.gifter.app.request.follow.service;

import com.gifter.app.request.follow.dto.FollowRequestDto;
import com.gifter.app.request.follow.dto.PendingFollowRequestDto;
import com.gifter.app.request.follow.dto.UserFollowRequestDto;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface FollowRequestService {
    void removeFollowRequest(Long id);

    PendingFollowRequestDto getUserFollowRequests();

    UserFollowRequestDto createFollowRequest(@Valid FollowRequestDto followRequest);

    Optional<UserFollowRequestDto> getFollowRequest(Long destinationId);

    void useFollowRequest(Long id);
}
