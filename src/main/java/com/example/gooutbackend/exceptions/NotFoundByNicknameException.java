package com.example.gooutbackend.exceptions;

public class NotFoundByNicknameException extends RuntimeException {
    public <T> NotFoundByNicknameException(Class<T> clazz, String nickname){
        super("User with nickname = " + nickname + " not found");
    }
}