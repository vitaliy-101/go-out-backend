package com.example.gooutbackend.exceptions;

public class ExistByNicknameException extends RuntimeException {
    public <T> ExistByNicknameException(Class<T> clazz, String nickname){
        super("User with nickname = " + nickname + " already exist");
    }
}