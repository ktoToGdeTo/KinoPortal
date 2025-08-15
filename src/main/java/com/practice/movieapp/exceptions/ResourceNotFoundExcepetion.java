package com.practice.movieapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExcepetion extends RuntimeException {
    public ResourceNotFoundExcepetion(String message) {
        super(message);
    }
}
