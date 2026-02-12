package com.felicita.repository.impl;

import com.felicita.exception.InsertFailedErrorExceptionHandler;
import com.felicita.model.request.InsertBrowserHistoryRequest;
import com.felicita.queries.BrowserHistoryQueries;
import com.felicita.repository.BrowserHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;

@Repository
public class BrowserHistoryRepositoryImpl implements BrowserHistoryRepository {

    private final static Logger LOGGER = LoggerFactory.getLogger(BrowserHistoryRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BrowserHistoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addHistoryData(InsertBrowserHistoryRequest insertBrowserHistoryRequest, Long userId) {

        String INSERT_BROWSER_HISTORY_REQUEST = BrowserHistoryQueries.INSERT_BROWSER_HISTORY_REQUEST;

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT_BROWSER_HISTORY_REQUEST);
                ps.setString(1, insertBrowserHistoryRequest.getType());
                ps.setLong(2, insertBrowserHistoryRequest.getDataId());
                ps.setLong(3, userId);
                ps.setString(4, "ACTIVE");
                return ps;
            });

        } catch (Exception e) {
            LOGGER.error("Error inserting browser history: {}", e.toString(), e);
            throw new InsertFailedErrorExceptionHandler("Something went wrong while inserting browser history");
        }
    }

}
