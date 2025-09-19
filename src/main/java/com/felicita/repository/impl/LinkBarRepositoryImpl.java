package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.LinkBarResponse;
import com.felicita.queries.LinkBarQueries;
import com.felicita.repository.LinkBarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LinkBarRepositoryImpl implements LinkBarRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkBarRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LinkBarRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<LinkBarResponse> getAllLinkBarItems(){
        String GET_ALL_LINK_BAR = LinkBarQueries.GET_ALL_LINK_BAR_DATA;
        try {
            LOGGER.info("Executing query to fetch all LinkBar items...");

            List<LinkBarResponse> results = jdbcTemplate.query(GET_ALL_LINK_BAR, (rs, rowNum) -> {
                LinkBarResponse response = new LinkBarResponse();
                response.setName(rs.getString("name"));
                response.setDescription(rs.getString("description"));
                response.setTypeName(rs.getString("type_name"));
                response.setTypeStatus(rs.getString("type_status"));
                response.setIconUrl(rs.getString("icon_url"));
                response.setLinkUrl(rs.getString("link_url"));
                response.setItemStatus(rs.getString("item_status"));
                response.setItemStatusStatus(rs.getString("item_status_status"));
                response.setCreatedAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null);
                response.setCreatedBy(rs.getObject("created_by") != null ? rs.getInt("created_by") : null);
                response.setUpdatedAt(rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null);
                response.setUpdatedBy(rs.getObject("updated_by") != null ? rs.getInt("updated_by") : null);
                response.setTerminatedAt(rs.getTimestamp("terminated_at") != null ? rs.getTimestamp("terminated_at").toLocalDateTime() : null);
                response.setTerminatedBy(rs.getObject("terminated_by") != null ? rs.getInt("terminated_by") : null);
                return response;
            });

            LOGGER.info("Successfully fetched {} LinkBar items.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching LinkBar items: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch LinkBar items from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching LinkBar items: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching LinkBar items");
        }
    }
}

