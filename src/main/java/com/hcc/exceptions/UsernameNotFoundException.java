package com.hcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// this is an example exception feel free to delete it after you make your own
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UsernameNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public UsernameNotFoundException(String message) {
        super(message);
    }
}