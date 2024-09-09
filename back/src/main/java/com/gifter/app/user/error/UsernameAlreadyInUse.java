package com.gifter.app.user.error;

public class UsernameAlreadyInUse extends RuntimeException {
    public UsernameAlreadyInUse() {
        super("Username already in use");
    }
}
