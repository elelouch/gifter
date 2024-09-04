package com.gifter.app.user.service;

import com.gifter.app.request.follow.FollowRequestDto;
import com.gifter.app.user.dto.GifterUserDto;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {
    List<GifterUserDto> getUsers();
    void createFollowRequest(FollowRequestDto followRequest);
    void removeFollowRequest(Long id);
}
