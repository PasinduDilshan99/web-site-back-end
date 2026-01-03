package com.felicita.repository.impl;

import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.BlogTagResponse;
import com.felicita.model.response.ContactMethodResponse;
import com.felicita.queries.BlogQueries;
import com.felicita.queries.ContactUsQueries;
import com.felicita.repository.ContactUsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactUsRepositoryImpl implements ContactUsRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactUsRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactUsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ContactMethodResponse> getContactMethods() {
        String GET_ALL_ACTIVE_CONTACT_METHODS = ContactUsQueries.GET_ALL_ACTIVE_CONTACT_METHODS;
        try {
            return jdbcTemplate.query(GET_ALL_ACTIVE_CONTACT_METHODS, (rs, rowNum) ->
                    ContactMethodResponse.builder()
                            .id(rs.getLong("id"))
                            .title(rs.getString("title"))
                            .value(rs.getString("value"))
                            .description(rs.getString("description"))
                            .link(rs.getString("link"))
                            .action(rs.getString("action"))
                            .icon(rs.getString("icon"))
                            .build()
            );
        } catch (Exception e) {
            LOGGER.error("Error fetching contact methods: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler(
                    "Failed to fetch contact methods from database"
            );
        }
    }



}
