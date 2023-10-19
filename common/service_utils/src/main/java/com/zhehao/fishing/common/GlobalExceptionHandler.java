package com.zhehao.fishing.common;

import ch.qos.logback.core.encoder.EchoEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleException(){
        return ResponseEntity.internalServerError().build();
    }
}
