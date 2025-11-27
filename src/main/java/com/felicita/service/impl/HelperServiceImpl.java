package com.felicita.service.impl;

import com.felicita.service.HelperService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HelperServiceImpl implements HelperService {
    public String generateUniqueCode() {
        return UUID.randomUUID().toString();
    }


}
