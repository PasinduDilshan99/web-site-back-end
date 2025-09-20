package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.FaqResponse;
import com.felicita.queries.FaqQueries;
import com.felicita.repository.FaqRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FaqRepositoryImpl implements FaqRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(FaqRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FaqRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<FaqResponse> getAllFaqItems() {
        String GET_ALL_FAQ = FaqQueries.GET_ALL_FAQ_DATA;
        try {
            LOGGER.info("Executing query to fetch all faq items...");

            List<FaqResponse> results = jdbcTemplate.query(GET_ALL_FAQ, (rs, rowNum) -> {
                FaqResponse response = new FaqResponse();

                response.setFaqId(rs.getLong("FAQ_ID"));
                response.setFaqQuestion(rs.getString("FAQ_QUESTION"));
                response.setFaqAnswer1(rs.getString("FAQ_ANSWER1"));
                response.setFaqAnswer2(rs.getString("FAQ_ANSWER2"));
                response.setFaqAnswer3(rs.getString("FAQ_ANSWER3"));
                response.setFaqAnswer4(rs.getString("FAQ_ANSWER4"));
                response.setFaqAnswer5(rs.getString("FAQ_ANSWER5"));
                response.setFaqDisplayAnswer(rs.getString("FAQ_DISPLAY_ASNWER"));
                response.setFaqStatus(rs.getString("FAQ_STATUS"));
                response.setFaqStatusStatus(rs.getString("FAQ_STATUS_STATUS"));
                response.setFaqCreatedAt(rs.getTimestamp("FAQ_CREATED_AT") != null ?
                        rs.getTimestamp("FAQ_CREATED_AT").toLocalDateTime() : null);
                response.setFaqCreatedBy(rs.getLong("FAQ_CREATED_BY"));
                response.setFaqUpdatedAt(rs.getTimestamp("FAQ_UPDATED_AT") != null ?
                        rs.getTimestamp("FAQ_UPDATED_AT").toLocalDateTime() : null);
                response.setFaqUpdatedBy(rs.getLong("FAQ_UPDATED_BY"));
                response.setFaqTerminatedAt(rs.getTimestamp("FAQ_TERMINATED_AT") != null ?
                        rs.getTimestamp("FAQ_TERMINATED_AT").toLocalDateTime() : null);
                response.setFaqTerminatedBy(rs.getLong("FAQ_TERMINATED_BY"));
                response.setFaqViewCount(rs.getObject("FAQ_VIEW_COUNT") != null ? rs.getInt("FAQ_VIEW_COUNT") : 0);
                response.setFaqLastView(rs.getTimestamp("FAQ_LAST_VIEW") != null ?
                        rs.getTimestamp("FAQ_LAST_VIEW").toLocalDateTime() : null);

                return response;
            });

            LOGGER.info("Successfully fetched {} faq items.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching faq items: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch faq items from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching faq items: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching faq items");
        }
    }

}
