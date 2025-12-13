package com.felicita.service;

public interface CommonService {
    Long getUserIdBySecurityContext();
    Long getUserIdBySecurityContextWithOutException();
    String generateRandomOtp();
}
