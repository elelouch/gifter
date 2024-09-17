package com.gifter.app.request.follow.controller;

import com.gifter.app.request.follow.FollowRequestService;
import com.gifter.app.request.follow.dto.UserFollowRequestDto;
import com.gifter.app.request.follow.dto.FollowRequestDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("follow")
@CrossOrigin
public class FollowRequestController {
    @Autowired
    private FollowRequestService followRequestService;

    @PostMapping()
    public UserFollowRequestDto createFollowRequest(@Valid @RequestBody FollowRequestDto followRequest) {
        return followRequestService.createFollowRequest(followRequest);
    }

    @GetMapping("/destination/{id}")
    public UserFollowRequestDto getFollowRequest(@PathVariable Long destinationId) {
        return followRequestService.getFollowRequest(destinationId);
    }

    @DeleteMapping("{id}")
    public void removeFollowRequest(@PathVariable Long id) {
        followRequestService.removeFollowRequest(id);
    }

    @GetMapping
    public List<UserFollowRequestDto> getUserRequests() {
        return followRequestService.getUserFollowRequests();
    }

    @PostMapping("{id}")
    public void useFollowRequest(@PathVariable Long id) {
        followRequestService.useFollowRequest(id);
    }
}
