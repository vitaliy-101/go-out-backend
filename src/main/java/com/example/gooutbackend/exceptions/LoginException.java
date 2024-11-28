package com.example.gooutbackend.exceptions;

public class LoginException extends RuntimeException {
    public <T> LoginException(Class<T> clazz) {
        super("The user with such data was not found. Check that the password and email are correct");
    }
}