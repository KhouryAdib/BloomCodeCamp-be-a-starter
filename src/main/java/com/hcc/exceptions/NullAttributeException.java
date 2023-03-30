package com.hcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NullAttributeException extends RuntimeException{
    public NullAttributeException(String message) {
        super(message);
    }
}
