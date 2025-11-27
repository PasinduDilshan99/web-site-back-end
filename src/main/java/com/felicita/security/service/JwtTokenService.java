package com.felicita.security.service;

import com.felicita.security.model.InsertJwtTokenRequest;

public interface JwtTokenService {
    void insertJwtToken(InsertJwtTokenRequest insertJwtTokenRequest);
}
