package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InsertFailedErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.request.BrowsingHistoryRequest;
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

import java.util.ArrayList;
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
    public BrowserHistoryResponse getHistoryData(Long userId, BrowsingHistoryRequest request) {
        try {
            LOGGER.info("Fetching browser history for userId: {}", userId);

            // Base query
            String baseQuery = """
                FROM browser_history bh
                LEFT JOIN common_status cs ON bh.status_id = cs.id
                WHERE bh.user_id = ?
                """;

            List<Object> params = new ArrayList<>();
            params.add(userId);

            // Apply filters dynamically
            if (request.getHistoryType() != null && !request.getHistoryType().isBlank()) {
                baseQuery += " AND UPPER(bh.type) = ?";
                params.add(request.getHistoryType().toUpperCase());
            }

            if (request.getFrom() != null) {
                baseQuery += " AND bh.created_at >= ?";
                params.add(new java.sql.Timestamp(request.getFrom().getTime()));
            }

            if (request.getTo() != null) {
                baseQuery += " AND bh.created_at <= ?";
                params.add(new java.sql.Timestamp(request.getTo().getTime()));
            }

            if (request.getNoOfLastDays() != null && request.getNoOfLastDays() > 0) {
                baseQuery += " AND bh.created_at >= NOW() - INTERVAL ? DAY";
                params.add(request.getNoOfLastDays());
            }

            // 1️⃣ Get total count
            String countQuery = "SELECT COUNT(*) " + baseQuery;
            Integer totalCount = jdbcTemplate.queryForObject(countQuery, params.toArray(), Integer.class);

            // 2️⃣ Fetch paginated history
            int pageSize = request.getPageSize() > 0 ? request.getPageSize() : 20;
            int offset = request.getPageNumber() > 0 ? (request.getPageNumber() - 1) * pageSize : 0;

            String dataQuery = "SELECT bh.id, bh.type, bh.data_id, bh.user_id, bh.created_at, cs.name AS status_name "
                    + baseQuery + " ORDER BY bh.created_at DESC LIMIT ? OFFSET ?";

            List<Object> dataParams = new ArrayList<>(params);
            dataParams.add(pageSize);
            dataParams.add(offset);

            List<BrowserHistoryResponse.HistoryResponse> history = jdbcTemplate.query(
                    dataQuery,
                    dataParams.toArray(),
                    (rs, rowNum) -> new BrowserHistoryResponse.HistoryResponse(
                            rs.getLong("id"),
                            rs.getString("type"),
                            rs.getLong("data_id"),
                            rs.getLong("user_id"),
                            rs.getTimestamp("created_at").toLocalDateTime(),
                            rs.getString("status_name")
                    )
            );

            // Return combined response
            return BrowserHistoryResponse.builder()
                    .totalCount(totalCount)
                    .history(history)
                    .build();

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching history: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch history from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching history: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching history");
        }
    }




}
