package com.gifter.app.auth.errors;


public class UserAlreadyCreatedException extends RuntimeException{
    public UserAlreadyCreatedException() {
        super("User already created");
    }
}
