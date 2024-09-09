package com.gifter.app.request.follow;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("follow")
public class FollowRequestController {
    @Autowired
    private FollowRequestService followRequestService;

    @PostMapping()
    public void createFollowRequest(@Valid @RequestBody FollowRequestDto followRequest) {
        followRequestService.createFollowRequest(followRequest);
    }

    @DeleteMapping("${id}")
    public void removeFollowRequest(@PathVariable Long id) {
        followRequestService.removeFollowRequest(id);
    }

    @GetMapping
    public List<UserFollowRequestDto> getUserRequests() {
        return followRequestService.getUserFollowRequests();
    }
}
