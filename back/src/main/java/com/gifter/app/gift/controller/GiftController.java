package com.gifter.app.gift.controller;

import com.gifter.app.gift.dto.UpdateGiftsDto;
import com.gifter.app.gift.entity.Gift;
import com.gifter.app.gift.service.GiftService;
import jakarta.validation.Valid;
import org.hibernate.sql.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gift")
@CrossOrigin
public class GiftController {
    Logger logger = LoggerFactory.getLogger(GiftController.class);

    @Autowired
    private GiftService giftService;

    @GetMapping("list")
    public UpdateGiftsDto getUserGifts() {
        logger.info("get user gifts request");
        UpdateGiftsDto gifts = giftService.getCurrentUserGifts();
        logger.info("List size: "+gifts.getList().size());
        return gifts;
    }

    @PostMapping("list")
    public UpdateGiftsDto updateUserGifts(@Valid @RequestBody UpdateGiftsDto updateDto) {
        logger.info("update user gifts request");
        return giftService.updateCurrentUserGifts(updateDto);
    }

    @DeleteMapping("{id}")
    public void removeGift(@PathVariable Long id) {
        giftService.removeGift(id);
    }

    @PostMapping
    public Gift updateGift(@Valid @RequestBody Gift gift) {
        logger.info("add/update gift request");
        return giftService.updateGift(gift);
    }

}
