package com.gifter.app.gift.service;

import com.gifter.app.gift.dto.UpdateGiftsDto;
import com.gifter.app.gift.entity.Gift;

public interface GiftService {
    UpdateGiftsDto getCurrentUserGifts();

    UpdateGiftsDto updateCurrentUserGifts(UpdateGiftsDto giftsDto);

    void removeGift(Long id);

    Gift updateGift(Gift gift);
}
