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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GiftServiceImpl implements GiftService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GiftRepository giftRepository;

    Logger logger = LoggerFactory.getLogger(GiftServiceImpl.class);

    @Override
    public UpdateGiftsDto getCurrentUserGifts(String username) {
        return userRepository.findByUsername(username).map(fetchedUser -> {
            UpdateGiftsDto dto = new UpdateGiftsDto();
            dto.setList(List.copyOf(fetchedUser.getGifts()));
            return dto;
        }).orElseThrow(() -> new UsernameNotFoundException("Gifts: User not found"));
    }

    @Override
    public UpdateGiftsDto updateCurrentUserGifts(UpdateGiftsDto dto) {
        GifterUser user = getCurrentUser();
        Set<Gift> giftsSet = new HashSet<>(dto.getList()); // remove duplicates
        Set<Gift> updatedSet = new HashSet<>(giftRepository.saveAll(giftsSet));
        user.setGifts(updatedSet);
        userRepository.save(user);
        dto.setList(List.copyOf(updatedSet));
        return dto;
    }

    private GifterUser getCurrentUser() {
        return (GifterUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public void removeGift(Long id) {
        giftRepository.deleteById(id);
    }

    @Override
    public Gift updateGift(Gift gift) {
        Gift giftFound = giftRepository.findById(gift.getId()).orElse(new Gift());
        giftFound.setName(gift.getName());
        giftFound.setImageUrl(gift.getImageUrl());
        giftFound.setLocation(gift.getLocation());
        Gift giftSaved = giftRepository.save(giftFound);

        GifterUser curr = userRepository.findById(getCurrentUser().getId()).get();

        Set<Gift> gifts = curr.getGifts();
        gifts.add(giftSaved);
        curr.setGifts(gifts);
        userRepository.save(curr);
        return giftSaved;
    }

}
