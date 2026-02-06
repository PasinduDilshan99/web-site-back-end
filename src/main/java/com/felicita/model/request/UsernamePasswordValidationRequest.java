package com.felicita.model.request;

import lombok.Data;

@Data
public class UsernamePasswordValidationRequest {
    private String username;
    private String password;
}
