package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InsertFailedErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.request.InsertHistoryData;
import com.felicita.model.response.BrowserHistoryResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.queries.FaqQueries;
import com.felicita.queries.HistoryManagementQueries;
import com.felicita.queries.PartnerQueries;
import com.felicita.repository.HistoryManagementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoryManagementRepositoryImpl implements HistoryManagementRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryManagementRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HistoryManagementRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void insertHistoryData(InsertHistoryData insertHistoryData, Long userId) {
        String INSERT_HISTORY_DATA = HistoryManagementQueries.INSERT_HISTORY_DATA;
        try {
            int rowsAffected = jdbcTemplate.update(INSERT_HISTORY_DATA,
                    insertHistoryData.getType(),
                    insertHistoryData.getDataId(),
                    userId
            );

            if (rowsAffected == 0) {
                throw new InsertFailedErrorExceptionHandler("No rows affected when inserting history data");
            }

        } catch (InsertFailedErrorExceptionHandler e) {
            throw new InsertFailedErrorExceptionHandler("Failed to insert history record");
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public List<BrowserHistoryResponse> getHistoryData(Long userId) {
        String GET_HISTORY_DATA = HistoryManagementQueries.GET_HISTORY_DATA;

        try {
            LOGGER.info("Executing query to fetch browser history for userId: {}", userId);

            List<BrowserHistoryResponse> results = jdbcTemplate.query(
                    GET_HISTORY_DATA,
                    new Object[]{userId},
                    (rs, rowNum) -> BrowserHistoryResponse.builder()
                            .id(rs.getLong("id"))
                            .type(rs.getString("type"))
                            .dataId(rs.getLong("data_id"))
                            .userId(rs.getLong("user_id"))
                            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                            .statusName(rs.getString("status_name"))
                            .build()
            );

            LOGGER.info("Successfully fetched {} history records.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching history: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch history from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching history: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching history");
        }
    }

}
