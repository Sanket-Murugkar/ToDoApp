package com.demo.todoapp.exception;


import lombok.Builder;

@Builder
public class NotFoundException extends RuntimeException {
    private String message;
    public NotFoundException() {
        super();
    }

    public NotFoundException(final String message) {
        super(message);
    }

}
