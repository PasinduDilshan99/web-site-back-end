package com.felicita.repository.impl;

import com.felicita.repository.ServiceProviderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceProviderRepositoryImpl implements ServiceProviderRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceProviderRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ServiceProviderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


}
