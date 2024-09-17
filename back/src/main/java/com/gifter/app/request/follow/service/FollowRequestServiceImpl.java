package com.gifter.app.request.follow.service;

import com.gifter.app.request.follow.dto.FollowRequestDto;
import com.gifter.app.request.follow.dto.PendingFollowRequestDto;
import com.gifter.app.request.follow.dto.UserFollowRequestDto;
import com.gifter.app.request.follow.entity.FollowRequest;
import com.gifter.app.request.follow.error.FollowRequestNotFound;
import com.gifter.app.request.follow.repository.FollowRequestRepository;
import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(FollowRequestServiceImpl.class);

    @Override
    public void removeFollowRequest(Long id) {
        FollowRequest followRequest = followRequestRepository.findById(id).orElseThrow(FollowRequestNotFound::new);
        if (followRequest.isUsed()) {
            GifterUser origin = followRequest.getUserOrigin();
            GifterUser destination = followRequest.getUserDestination();
            origin.getFollowers().removeIf(curr -> curr.equals(destination));
            userRepository.save(origin);
        }
        followRequestRepository.delete(followRequest);
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
        FollowRequest followRequest = followRequestRepository.findById(id).orElseThrow(FollowRequestNotFound::new);
        GifterUser orig = followRequest.getUserOrigin();
        GifterUser dest = followRequest.getUserDestination();
        orig.getFollowing().add(dest);
        dest.getFollowers().add(orig);
        followRequestRepository.save(followRequest);
    }

    private GifterUser getCurrentUser() {
        return (GifterUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
