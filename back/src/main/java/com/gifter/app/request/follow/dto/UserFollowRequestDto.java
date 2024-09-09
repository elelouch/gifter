package com.gifter.app.request.follow.dto;

import com.gifter.app.request.follow.entity.FollowRequest;
import com.gifter.app.user.dto.GifterUserDto;
import com.gifter.app.user.entity.GifterUser;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserFollowRequestDto {
    private GifterUserDto requester;
    private Long id;

    public static UserFollowRequestDto fromEntity(FollowRequest followRequest) {
        GifterUser user = followRequest.getUserOrigin();
        UserFollowRequestDto dto = new UserFollowRequestDto();
        dto.setRequester(GifterUserDto.fromEntity(user));
        dto.setId(followRequest.getId());
        return dto;
    }

    public static List<UserFollowRequestDto> fromEntity(List<FollowRequest> followRequests) {
        List<UserFollowRequestDto> userFollowRequestDtos = new ArrayList<>(followRequests.size());
        for(FollowRequest fr : followRequests) {
            userFollowRequestDtos.add(fromEntity(fr));
        }
        return userFollowRequestDtos;
    }


}
