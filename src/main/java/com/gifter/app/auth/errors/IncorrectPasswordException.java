package com.gifter.app.auth.errors;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {super("Incorrect password");}
}
