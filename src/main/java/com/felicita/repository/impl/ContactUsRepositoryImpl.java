package com.felicita.repository.impl;

import com.felicita.repository.ContactUsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContactUsRepositoryImpl implements ContactUsRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactUsRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactUsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
