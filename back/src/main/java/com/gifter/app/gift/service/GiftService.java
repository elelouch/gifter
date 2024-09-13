package com.gifter.app.gift.service;

import com.gifter.app.gift.dto.UpdateGiftsDto;

public interface GiftService {
    UpdateGiftsDto getCurrentUserGifts();

    UpdateGiftsDto updateCurrentUserGifts(UpdateGiftsDto giftsDto);
}
