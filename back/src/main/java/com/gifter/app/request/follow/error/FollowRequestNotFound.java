package com.gifter.app.request.follow.error;

public class FollowRequestNotFound extends RuntimeException{
    public FollowRequestNotFound() {
        super("Follow request not found");
    }
}
