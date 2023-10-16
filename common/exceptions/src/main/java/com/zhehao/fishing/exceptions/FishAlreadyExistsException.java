package com.zhehao.fishing.exceptions;

public class FishAlreadyExistsException extends RuntimeException{
    public FishAlreadyExistsException(String message){
        super(message);
    }
}
