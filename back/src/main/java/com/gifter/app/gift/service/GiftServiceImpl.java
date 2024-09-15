package com.gifter.app.gift.service;

import com.gifter.app.gift.dto.UpdateGiftsDto;
import com.gifter.app.gift.entity.Gift;
import com.gifter.app.gift.repository.GiftRepository;
import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class GiftServiceImpl implements GiftService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GiftRepository giftRepository;

    @Override
    public UpdateGiftsDto updateCurrentUserGifts(UpdateGiftsDto dto) {
        UpdateGiftsDto updatedGifts = new UpdateGiftsDto();
        GifterUser user = getCurrentUser();
        Set<Gift> giftsSet = new HashSet<>(dto.getList()); // remove duplicates
        Set<Gift> updatedSet = new HashSet<>(giftRepository.saveAll(giftsSet));
        user.setGifts(updatedSet);
        userRepository.save(user);
        updatedGifts.setList(new ArrayList<>(updatedSet));
        return updatedGifts;
    }

    @Override
    public UpdateGiftsDto getCurrentUserGifts() {
        UpdateGiftsDto giftsDto = new UpdateGiftsDto();
        GifterUser user = getCurrentUser();
        giftsDto.setList(new ArrayList<>(userRepository.findById(user.getId()).get().getGifts()));
        return giftsDto;
    }

    private GifterUser getCurrentUser() {
        return (GifterUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
