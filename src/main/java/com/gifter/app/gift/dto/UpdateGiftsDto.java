package com.gifter.app.gift.dto;

import com.gifter.app.gift.entity.Gift;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class UpdateGiftsDto {
    @NotNull
    private List<Gift> list;
}
