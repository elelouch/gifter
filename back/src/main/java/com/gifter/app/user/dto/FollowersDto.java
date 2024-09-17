package com.gifter.app.user.dto;

import lombok.Data;

import java.util.List;

@Data
public class FollowersDto {
    List<GifterUserDto> list;
}
