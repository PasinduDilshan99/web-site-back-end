package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.LinkBarResponse;
import com.felicita.model.response.NavBarResponse;
import com.felicita.queries.LinkBarQueries;
import com.felicita.queries.NavBarQueries;
import com.felicita.repository.NavBarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class NavBarRepositoryImpl implements NavBarRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(NavBarRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NavBarRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<NavBarResponse> getAllNavBarItems() {
        String GET_ALL_NAV_BAR = NavBarQueries.GET_ALL_NAV_BAR_DATA;
        try {
            LOGGER.info("Executing query to fetch all nav bar items...");

            List<NavBarResponse> results = jdbcTemplate.query(GET_ALL_NAV_BAR, (rs, rowNum) -> {
                NavBarResponse response = new NavBarResponse();

                response.setName(rs.getString("NAME"));
                response.setDescription(rs.getString("DESCRIPTION"));
                response.setStatus(rs.getString("STATUS"));
                response.setNavBarStatusStatus(rs.getString("NAV_BAR_STATUS_STATUS"));
                response.setLinkUrl(rs.getString("LINK_URL"));

                Timestamp createdTs = rs.getTimestamp("CREATED_AT");
                response.setCreatedAt(createdTs != null ? createdTs.toLocalDateTime() : null);

                Integer createdBy = rs.getObject("CREATED_BY") != null ? rs.getInt("CREATED_BY") : null;
                response.setCreatedBy(createdBy);

                Timestamp updatedTs = rs.getTimestamp("UPDATED_AT");
                response.setUpdatedAt(updatedTs != null ? updatedTs.toLocalDateTime() : null);

                Integer updatedBy = rs.getObject("UPDATED_BY") != null ? rs.getInt("UPDATED_BY") : null;
                response.setUpdatedBy(updatedBy);

                Timestamp terminatedTs = rs.getTimestamp("TERMINATED_AT");
                response.setTerminatedAt(terminatedTs != null ? terminatedTs.toLocalDateTime() : null);

                Integer terminatedBy = rs.getObject("TERMINATED_BY") != null ? rs.getInt("TERMINATED_BY") : null;
                response.setTerminatedBy(terminatedBy);

                return response;
            });

            LOGGER.info("Successfully fetched {} nav bar items.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching nav bar items: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch nav bar items from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching nav bar items: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching nav bar items");
        }
    }


}
