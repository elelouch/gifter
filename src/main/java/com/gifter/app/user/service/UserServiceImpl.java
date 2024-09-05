package com.gifter.app.user.service;

import com.gifter.app.gift.entity.Gift;
import com.gifter.app.request.follow.FollowRequest;
import com.gifter.app.request.follow.FollowRequestDto;
import com.gifter.app.request.follow.FollowRequestRepository;
import com.gifter.app.user.dto.GifterUserDto;
import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRequestRepository followRequestRepository;

    public List<GifterUserDto> getUsers() {
        return GifterUserDto.fromEntity(userRepository.findAll());
    }

    @Override
    public void createFollowRequest(FollowRequestDto followRequestDto) {
        GifterUser origin = userRepository.getReferenceById(followRequestDto.getUserId());
        GifterUser destiny = userRepository.getReferenceById(followRequestDto.getDestinationId());
        FollowRequest fr = new FollowRequest();
        fr.setUserOrigin(origin);
        fr.setUserDestination(destiny);
        fr.setDate(new Date());
        followRequestRepository.save(fr);
    }

    @Override
    public void removeFollowRequest(Long id) {
        followRequestRepository.deleteById(id);
    }

    @Override
    public void updateGifts(UpdateGiftsDto dto) {
        GifterUser user = userRepository.findById(dto.getId()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setGifts(dto.getGifts());
        userRepository.save(user);
    }

    @Override
    public Set<Gift> getUserGifts(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found")).getGifts();
    }
}
