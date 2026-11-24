package com.ExceptionandDto.ExceptionandDto.globalExceptionHandler;

public class AppointmentConflictException extends RuntimeException {
    public AppointmentConflictException(String message) {
        super(message);
    }
}
