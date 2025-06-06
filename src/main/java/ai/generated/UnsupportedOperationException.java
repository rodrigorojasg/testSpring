package com.company.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnsupportedOperationException extends RuntimeException {
    public UnsupportedOperationException(String message) {
        super(message);
    }
}

