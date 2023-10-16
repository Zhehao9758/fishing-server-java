package com.zhehao.fishing.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message){
        super(message);
    }
}
