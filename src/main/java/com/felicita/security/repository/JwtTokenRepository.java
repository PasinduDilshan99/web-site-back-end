package com.felicita.security.repository;

import com.felicita.security.model.InsertJwtTokenRequest;

public interface JwtTokenRepository {
    void insertJwtToken(InsertJwtTokenRequest request);
}
