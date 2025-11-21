package com.felicita.security.repository;

import com.felicita.security.model.RegisterUser;
import com.felicita.security.model.User;

import java.util.Optional;

public interface AuthRepository {
    void signup(RegisterUser registerUser);
    Optional<User> getUserByUsername(String username);
}
