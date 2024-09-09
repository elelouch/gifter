package com.gifter.app.gift.controller;

import com.gifter.app.gift.entity.Gift;
import com.gifter.app.gift.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("gift")
public class GiftController {
    @Autowired
    private GiftService giftService;

    @GetMapping
    public List<Gift> getUserGifts() {
        return giftService.getCurrentUserGifts();
    }

    public void updateUserGifts(List<Gift> giftList) {
        giftService.updateCurrentUserGifts(giftList);
    }

}
