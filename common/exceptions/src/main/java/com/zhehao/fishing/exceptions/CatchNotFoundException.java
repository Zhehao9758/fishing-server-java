package com.zhehao.fishing.exceptions;

public class CatchNotFoundException extends RuntimeException{
    public CatchNotFoundException(String message){
        super(message);
    }
}
