package com.felicita.exception;

public class UserRegisterFailedErrorExceptionHandler extends RuntimeException{
    public UserRegisterFailedErrorExceptionHandler(String message) {
        super(message);
    }
}
