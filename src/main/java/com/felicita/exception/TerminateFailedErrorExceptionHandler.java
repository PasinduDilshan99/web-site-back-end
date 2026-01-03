package com.felicita.exception;

public class TerminateFailedErrorExceptionHandler extends RuntimeException{
    public TerminateFailedErrorExceptionHandler(String message) {
        super(message);
    }
}
