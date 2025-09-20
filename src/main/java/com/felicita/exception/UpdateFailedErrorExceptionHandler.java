package com.felicita.exception;

public class UpdateFailedErrorExceptionHandler extends RuntimeException{

    public UpdateFailedErrorExceptionHandler(String message) {
        super(message);
    }
}
