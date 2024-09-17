package com.gifter.app.request.follow.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PendingFollowRequestDto {
    List<UserFollowRequestDto> pendingList;
}
