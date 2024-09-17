package com.gifter.app.request.follow.controller;

import com.gifter.app.request.follow.dto.PendingFollowRequestDto;
import com.gifter.app.request.follow.service.FollowRequestService;
import com.gifter.app.request.follow.dto.UserFollowRequestDto;
import com.gifter.app.request.follow.dto.FollowRequestDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("follow")
@CrossOrigin
public class FollowRequestController {
    @Autowired
    private FollowRequestService followRequestService;

    Logger logger = LoggerFactory.getLogger(FollowRequestController.class);

    @PostMapping()
    public UserFollowRequestDto createFollowRequest(@Valid @RequestBody FollowRequestDto followRequest) {
        return followRequestService.createFollowRequest(followRequest);
    }

    @GetMapping("/destination/{destinationId}")
    public ResponseEntity<UserFollowRequestDto> getFollowRequest(@PathVariable Long destinationId) {
        return ResponseEntity.of(followRequestService.getFollowRequest(destinationId));
    }

    @DeleteMapping("{id}")
    public void removeFollowRequest(@PathVariable Long id) {
        followRequestService.removeFollowRequest(id);
    }

    @GetMapping
    public PendingFollowRequestDto getUserFollowRequests() {
        return followRequestService.getUserFollowRequests();
    }

    @PostMapping("{id}")
    public void useFollowRequest(@PathVariable Long id) {
        followRequestService.useFollowRequest(id);
    }
}
