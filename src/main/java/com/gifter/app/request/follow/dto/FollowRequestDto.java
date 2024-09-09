package com.gifter.app.request.follow.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FollowRequestDto {
    @NotNull
    private Long destination;
}
