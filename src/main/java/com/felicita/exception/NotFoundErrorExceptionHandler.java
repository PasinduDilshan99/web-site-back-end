package com.felicita.exception;

public class NotFoundErrorExceptionHandler extends RuntimeException{
    public NotFoundErrorExceptionHandler(String message) {
        super(message);
    }
}
