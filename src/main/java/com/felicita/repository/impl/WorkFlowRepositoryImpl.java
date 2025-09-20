package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.WorkFlowResponse;
import com.felicita.queries.PartnerQueries;
import com.felicita.queries.WorkFlowQueries;
import com.felicita.repository.WorkFlowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkFlowRepositoryImpl implements WorkFlowRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkFlowRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkFlowRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkFlowResponse> getAllWorkFlowSteps() {
        String GET_ALL_WORK_FLOW = WorkFlowQueries.GET_ALL_WORK_FLOW_DATA;
        try {
            LOGGER.info("Executing query to fetch all work flow steps...");

            List<WorkFlowResponse> results = jdbcTemplate.query(GET_ALL_WORK_FLOW, (rs, rowNum) -> {
                WorkFlowResponse response = new WorkFlowResponse();
                response.setId(rs.getInt("WF_ID"));
                response.setTitle(rs.getString("WF_TITLE"));
                response.setDescription(rs.getString("WF_DESCRIPTION"));
                response.setOrderNumber(rs.getInt("WF_ORDER"));
                response.setIconUrl(rs.getString("WF_ICON_URL"));
                response.setIconColor(rs.getString("WF_ICON_COLOR"));
                response.setBgColor(rs.getString("WF_BG_COLOR"));
                response.setConnectText(rs.getString("WF_CONNECT_TEXT"));
                response.setLinkUrl(rs.getString("WF_LINK_URL"));
                response.setStatus(rs.getString("WF_STATUS"));
                response.setStatusStatus(rs.getString("WF_STATUS_STATUS"));
                response.setCreatedAt(rs.getTimestamp("WF_CREATED_AT") != null ? rs.getTimestamp("WF_CREATED_AT").toLocalDateTime() : null);
                response.setCreatedBy((Integer) rs.getObject("WF_CREATED_BY"));
                response.setUpdatedAt(rs.getTimestamp("WF_UPDATED_AT") != null ? rs.getTimestamp("WF_UPDATED_AT").toLocalDateTime() : null);
                response.setUpdatedBy((Integer) rs.getObject("WF_UPDATED_BY"));
                response.setTerminatedAt(rs.getTimestamp("WF_TERMINATED_AT") != null ? rs.getTimestamp("WF_TERMINATED_AT").toLocalDateTime() : null);
                response.setTerminatedBy((Integer) rs.getObject("WF_TERMINATED_BY"));
                return response;
            });

            LOGGER.info("Successfully fetched {} work flow steps.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching work flow steps: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch work flow steps from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching work flow steps: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching work flow steps");
        }
    }

}
