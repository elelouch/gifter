package com.gifter.app.user.error;

public class EmailAlreadyInUse extends RuntimeException {
    public EmailAlreadyInUse() {
        super("Email already in use");
    }
}
