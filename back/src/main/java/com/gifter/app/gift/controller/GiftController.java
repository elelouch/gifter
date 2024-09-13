package com.gifter.app.gift.controller;

import com.gifter.app.gift.dto.UpdateGiftsDto;
import com.gifter.app.gift.service.GiftService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("gift")
@CrossOrigin
public class GiftController {
    Logger logger = LoggerFactory.getLogger(GiftController.class);

    @Autowired
    private GiftService giftService;

    @GetMapping
    public UpdateGiftsDto getUserGifts() {
        logger.info("get user gifts request");
        return giftService.getCurrentUserGifts();
    }

    @PostMapping
    public UpdateGiftsDto updateUserGifts(@Valid @RequestBody UpdateGiftsDto updateDto) {
        logger.info("update user gifts request");
        return giftService.updateCurrentUserGifts(updateDto);
    }

}
