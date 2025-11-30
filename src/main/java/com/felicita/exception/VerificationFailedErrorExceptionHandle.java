package com.felicita.exception;

public class VerificationFailedErrorExceptionHandle extends RuntimeException{
    public VerificationFailedErrorExceptionHandle(String message) {
        super(message);
    }
}
