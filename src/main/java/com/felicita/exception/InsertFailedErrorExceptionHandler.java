package com.felicita.exception;

public class InsertFailedErrorExceptionHandler extends RuntimeException{
    public InsertFailedErrorExceptionHandler(String message) {
        super(message);
    }
}
