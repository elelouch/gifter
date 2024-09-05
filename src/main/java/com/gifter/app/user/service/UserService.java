package com.gifter.app.user.service;

import com.gifter.app.gift.entity.Gift;
import com.gifter.app.request.follow.FollowRequestDto;
import com.gifter.app.user.dto.GifterUserDto;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<GifterUserDto> getUsers();

    void createFollowRequest(FollowRequestDto followRequest);

    void removeFollowRequest(Long id);

    void updateGifts(UpdateGiftsDto updateGiftsDto);

    Set<Gift> getUserGifts(Long id);
}
