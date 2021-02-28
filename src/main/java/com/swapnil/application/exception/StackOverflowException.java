package com.swapnil.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class StackOverflowException extends Exception{
    public StackOverflowException(String message){
        super(message);
    }
}
