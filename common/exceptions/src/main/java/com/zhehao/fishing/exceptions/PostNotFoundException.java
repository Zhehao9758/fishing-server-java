package com.zhehao.fishing.exceptions;

public class PostNotFoundException extends RuntimeException{

    public PostNotFoundException(String message){
        super(message);
    }
}
