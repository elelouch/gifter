package com.gifter.app.gift.dto;

import com.gifter.app.gift.entity.Gift;
import lombok.Data;

import java.util.List;

@Data
public class UpdateGiftsDto {
    private List<Gift> list;
}
