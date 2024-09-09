package com.gifter.app.request.follow;

import com.gifter.app.request.follow.dto.FollowRequestDto;
import com.gifter.app.request.follow.dto.UserFollowRequestDto;
import jakarta.validation.Valid;

import java.util.List;

public interface FollowRequestService {
    void removeFollowRequest(Long id);

    List<UserFollowRequestDto> getUserFollowRequests();

    void createFollowRequest(@Valid FollowRequestDto followRequest);

    void useFollowRequest(Long id);
}
