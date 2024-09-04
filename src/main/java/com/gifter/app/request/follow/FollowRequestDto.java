package com.gifter.app.request.follow;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FollowRequestDto {
    @NotNull
    private Long userId;
    @NotNull
    private Long destinationId;
}
