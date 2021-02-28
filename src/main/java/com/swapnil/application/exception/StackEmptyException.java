package com.swapnil.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class StackEmptyException extends Exception {
    public StackEmptyException(String message){
        super(message);
    }
}
