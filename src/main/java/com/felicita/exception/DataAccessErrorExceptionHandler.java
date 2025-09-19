package com.felicita.exception;

public class DataAccessErrorExceptionHandler extends RuntimeException{
    public DataAccessErrorExceptionHandler(String message) {
        super(message);
    }
}
