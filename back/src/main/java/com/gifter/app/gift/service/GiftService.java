package com.gifter.app.gift.service;

import com.gifter.app.gift.entity.Gift;
import com.gifter.app.gift.dto.UpdateGiftsDto;

import java.util.List;

public interface GiftService {
    List<Gift> getCurrentUserGifts();

    void updateCurrentUserGifts(UpdateGiftsDto giftsDto);
}
