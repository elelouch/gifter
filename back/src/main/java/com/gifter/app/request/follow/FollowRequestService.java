package com.gifter.app.request.follow;

import com.gifter.app.request.follow.dto.FollowRequestDto;
import com.gifter.app.request.follow.dto.UserFollowRequestDto;
import jakarta.validation.Valid;

import java.util.List;

public interface FollowRequestService {
    void removeFollowRequest(Long id);

    List<UserFollowRequestDto> getUserFollowRequests();

    UserFollowRequestDto createFollowRequest(@Valid FollowRequestDto followRequest);

    UserFollowRequestDto getFollowRequest(Long destinationId);

    void useFollowRequest(Long id);
}
