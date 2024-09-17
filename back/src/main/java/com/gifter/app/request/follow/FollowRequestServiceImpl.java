package com.gifter.app.request.follow;

import com.gifter.app.request.follow.dto.FollowRequestDto;
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
import java.util.Set;

@Service
public class FollowRequestServiceImpl implements FollowRequestService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRequestRepository followRequestRepository;

    @Override
    public void removeFollowRequest(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserFollowRequestDto> getUserFollowRequests() {
        GifterUser current = getCurrentUser();
        List<FollowRequest> requests = followRequestRepository.findUserFollowRequests(current.getId());
        return UserFollowRequestDto.fromEntity(requests);
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
    public UserFollowRequestDto getFollowRequest(Long destinationId) {
        Long currentId = getCurrentUser().getId();
        FollowRequest fr = followRequestRepository.findByOrigAndDestId(currentId, destinationId).orElseThrow();
        return UserFollowRequestDto.fromEntity(fr);
    }

    @Override
    public void useFollowRequest(Long id) {
        FollowRequest followRequest = followRequestRepository.findById(id).orElseThrow();
        GifterUser origin = followRequest.getUserOrigin();
        GifterUser destination = followRequest.getUserDestination();

        Set<GifterUser> originFollowing = origin.getFollowing();
        Set<GifterUser> destinationFollowers = destination.getFollowers();

        originFollowing.add(destination);
        destinationFollowers.add(origin);

        userRepository.save(origin);
        userRepository.save(destination);
    }

    private GifterUser getCurrentUser() {
        return (GifterUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
