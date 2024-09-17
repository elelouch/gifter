package com.gifter.app.request.follow.service;

import com.gifter.app.request.follow.dto.FollowRequestDto;
import com.gifter.app.request.follow.dto.PendingFollowRequestDto;
import com.gifter.app.request.follow.dto.UserFollowRequestDto;
import com.gifter.app.request.follow.entity.FollowRequest;
import com.gifter.app.request.follow.repository.FollowRequestRepository;
import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FollowRequestServiceImpl implements FollowRequestService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRequestRepository followRequestRepository;

    @Override
    public void removeFollowRequest(Long id) {
        followRequestRepository.deleteById(id);
    }

    @Override
    public PendingFollowRequestDto getUserFollowRequests() {
        GifterUser current = getCurrentUser();
        List<UserFollowRequestDto> pendingFollows = UserFollowRequestDto.fromEntity(followRequestRepository.findUserFollowRequests(current.getId()));
        PendingFollowRequestDto pending = new PendingFollowRequestDto();
        pending.setPendingList(pendingFollows);
        return pending;
    }

    @Override
    public UserFollowRequestDto createFollowRequest(FollowRequestDto followRequestDto) {
        GifterUser origin = getCurrentUser();
        GifterUser destiny = userRepository.getReferenceById(followRequestDto.getDestination());
        FollowRequest fr = new FollowRequest();
        fr.setUserOrigin(origin);
        fr.setUserDestination(destiny);
        fr.setDate(new Date());
        return UserFollowRequestDto.fromEntity(followRequestRepository.save(fr));
    }

    @Override
    public Optional<UserFollowRequestDto> getFollowRequest(Long destinationId) {
        Long currentId = getCurrentUser().getId();
        return followRequestRepository.findByOrigAndDestId(currentId, destinationId).map(UserFollowRequestDto::fromEntity);
    }

    @Override
    public void useFollowRequest(Long id) {
        followRequestRepository.findById(id).ifPresent(followRequest -> {
            GifterUser origin = followRequest.getUserOrigin();
            origin.getFollowing().add(followRequest.getUserDestination());
            userRepository.save(origin);
        });
    }

    private GifterUser getCurrentUser() {
        return (GifterUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
