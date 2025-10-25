package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InsertFailedErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.exception.UpdateFailedErrorExceptionHandler;
import com.felicita.model.request.InsertFaqRequest;
import com.felicita.model.response.FaqOptionDetailsResponse;
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

    @Override
    public void updateFaqViewCount(FaqResponse faqResponse) {
        String UPDATE_VIEW_COUNT = FaqQueries.UPDATE_VIEW_COUNT;

        try {
            int rowsAffected = jdbcTemplate.update(
                    UPDATE_VIEW_COUNT,
                    faqResponse.getFaqViewCount(),
                    faqResponse.getFaqLastView(),
                    faqResponse.getFaqId()
            );

            if (rowsAffected == 0) {
                throw new UpdateFailedErrorExceptionHandler(
                        "Failed to update view count for FAQ ID: " + faqResponse.getFaqId()
                );
            }

            LOGGER.info("Successfully updated view count for FAQ ID {}", faqResponse.getFaqId());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while updating view count for FAQ ID {}: {}",
                    faqResponse.getFaqId(), ex.getMessage(), ex);
            throw new UpdateFailedErrorExceptionHandler(
                    "Database error: Failed to update view count for FAQ ID " + faqResponse.getFaqId()
            );
        }
    }


    @Override
    public FaqResponse getFaqItemById(long faqId) {
        String GET_FAQ_BY_ID = FaqQueries.GET_FAQ_BY_ID;

        try {
            return jdbcTemplate.queryForObject(GET_FAQ_BY_ID, new Object[]{faqId}, (rs, rowNum) -> {
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

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching FAQ ID {}: {}", faqId, ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler(
                    "Database error: Failed to fetch FAQ item with ID " + faqId
            );
        }
    }

    @Override
    public List<FaqOptionDetailsResponse> getFaqOptions() {
        String GET_FAQ_OPTIONS = FaqQueries.GET_FAQ_OPTIONS;

        try {
            LOGGER.info("Executing query to fetch all FAQ options...");

            List<FaqOptionDetailsResponse> results = jdbcTemplate.query(GET_FAQ_OPTIONS, (rs, rowNum) -> {
                return FaqOptionDetailsResponse.builder()
                        .optionId(rs.getLong("option_id"))
                        .optionKey(rs.getString("option_key"))
                        .optionValue(rs.getString("option_value"))
                        .optionType(rs.getString("option_type"))
                        .optionTypeDescription(rs.getString("option_type_description"))
                        .optionDescription(rs.getString("option_description"))
                        .commonStatusName(rs.getString("common_status_name"))
                        .createdAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null)
                        .updatedAt(rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null)
                        .createdBy(rs.getObject("created_by") != null ? rs.getLong("created_by") : null)
                        .updatedBy(rs.getObject("updated_by") != null ? rs.getLong("updated_by") : null)
                        .build();
            });

            LOGGER.info("Successfully fetched {} FAQ options.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching FAQ options: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch FAQ options from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching FAQ options: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching FAQ options");
        }
    }

    @Override
    public void insertFaqRequest(InsertFaqRequest request) {
        String INSERT_FAQ_REQUEST = FaqQueries.INSERT_FAQ_REQUEST;
        try {
            int rowsAffected = jdbcTemplate.update(INSERT_FAQ_REQUEST,
                    request.getTicketNumber(),
                    request.getName(),
                    request.getEmail(),
                    request.getCategory(),
                    request.getSubject(),
                    request.getMessage(),
                    request.getIpAddress(),
                    request.getUserId()
            );

            if (rowsAffected == 0) {
                throw new InsertFailedErrorExceptionHandler("No rows affected when inserting FAQ");
            }

        } catch (InsertFailedErrorExceptionHandler e) {
            LOGGER.error("Failed to insert FAQ for email: {}", request.getEmail(), e);
            throw new InsertFailedErrorExceptionHandler("Failed to insert FAQ record");
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }


}
