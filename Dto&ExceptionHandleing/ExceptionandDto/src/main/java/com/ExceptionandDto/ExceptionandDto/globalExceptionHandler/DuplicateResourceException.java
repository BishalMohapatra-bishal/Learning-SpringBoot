package com.ExceptionandDto.ExceptionandDto.globalExceptionHandler;

public class DuplicateResourceException extends RuntimeException{
    public DuplicateResourceException(String message) {
        super(message);
    }
}
