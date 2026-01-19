package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.OurServiceResponse;
import com.felicita.queries.OurServicesQueries;
import com.felicita.repository.OurServicesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class OurServicesRepositoryImpl implements OurServicesRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(OurServicesRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OurServicesRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<OurServiceResponse> getAllOurServices() {

        String GET_ALL_OUR_SERVICES = OurServicesQueries.GET_ALL_OUR_SERVICES_DATA;

        try {
            LOGGER.info("Executing query to fetch all our services.");

            List<OurServiceResponse> results = jdbcTemplate.query(GET_ALL_OUR_SERVICES, (rs, rowNum) -> {
                OurServiceResponse service = new OurServiceResponse();

                service.setServiceId(rs.getInt("SERVICE_ID"));
                service.setServiceTitle(rs.getString("SERVICE_TITLE"));
                service.setServiceSubTitle(rs.getString("SERVICE_SUB_TITLE"));
                service.setServiceDescription(rs.getString("SERVICE_DESCRIPTION"));
                service.setServiceImageUrl(rs.getString("SERVICE_IMAGE_URL"));
                service.setServiceColor(rs.getString("SERVICE_COLOR"));
                service.setServiceStatus(rs.getString("SERVICE_STATUS"));
                service.setServiceStatusStatus(rs.getString("SERVICE_STATUS_STATUS"));
                service.setServiceCreatedAt(rs.getTimestamp("SERVICE_CREATED_AT") != null
                        ? rs.getTimestamp("SERVICE_CREATED_AT").toLocalDateTime() : null);
                service.setServiceCreatedBy(rs.getInt("SERVICE_CREATED_BY"));
                service.setServiceUpdatedAt(rs.getTimestamp("SERVICE_UPDATED_AT") != null
                        ? rs.getTimestamp("SERVICE_UPDATED_AT").toLocalDateTime() : null);
                service.setServiceUpdatedBy(rs.getInt("SERVICE_UPDATED_BY"));
                service.setServiceTerminatedAt(rs.getTimestamp("SERVICE_TERMINATED_AT") != null
                        ? rs.getTimestamp("SERVICE_TERMINATED_AT").toLocalDateTime() : null);
                service.setServiceTerminatedBy(rs.getInt("SERVICE_TERMINATED_BY"));
                service.setServiceIconUrl(rs.getString("SERVICE_ICON_URL"));
                return service;
            });

            LOGGER.info("Successfully fetched {} our services.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching our services: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch our services from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching our services: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching our services");
        }
    }

}
