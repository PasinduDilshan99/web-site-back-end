package com.felicita.security.repository;

import com.felicita.security.model.RefreshToken;

import java.time.Instant;
import java.util.Optional;

public interface RefreshTokenRepository {

    RefreshToken save(RefreshToken refreshToken);

    Optional<RefreshToken> findByToken(String token);

    void revokeAllForUser(Integer userId);

    void revokeToken(String token);

    void deleteExpired(Instant now);
}
