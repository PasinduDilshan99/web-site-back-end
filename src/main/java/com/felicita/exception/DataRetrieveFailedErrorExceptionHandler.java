package com.felicita.exception;

public class DataRetrieveFailedErrorExceptionHandler extends RuntimeException{
    public DataRetrieveFailedErrorExceptionHandler(String message) {
        super(message);
    }
}
