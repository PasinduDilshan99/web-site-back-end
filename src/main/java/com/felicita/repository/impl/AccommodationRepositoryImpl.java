package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.AccommodationResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.queries.AccommodationQueries;
import com.felicita.queries.PartnerQueries;
import com.felicita.repository.AccommodationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccommodationRepositoryImpl implements AccommodationRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccommodationRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccommodationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<AccommodationResponse> getAllAccommodations() {
        String GET_ALL_ACCOMMODATION = AccommodationQueries.GET_ALL_ACCOMMODATION_DATA;
        try {
            LOGGER.info("Executing query to fetch all accommodations...");

            List<AccommodationResponse> results = jdbcTemplate.query(GET_ALL_ACCOMMODATION, (rs, rowNum) -> {
                AccommodationResponse accommodation = new AccommodationResponse();
                accommodation.setAccommodationId(rs.getInt("ACCOMMADATION_ID"));
                accommodation.setAccommodationTitle(rs.getString("ACCOMMADATION_TITLE"));
                accommodation.setAccommodationSubTitle(rs.getString("ACCOMMADATION_SUB_TITLE"));
                accommodation.setAccommodationDescription(rs.getString("ACCOMMADATION_DESCRIPTION"));
                accommodation.setAccommodationIconUrl(rs.getString("ACCOMMADATION_ICON_URL"));
                accommodation.setAccommodationImageUrl(rs.getString("ACCOMMADATION_IMAGE_URL"));
                accommodation.setAccommodationColor(rs.getString("ACCOMMADATION_COLOR"));
                accommodation.setAccommodationHoverColor(rs.getString("ACCOMMADATION_HOVER_COLOR"));
                accommodation.setAccommodationLinkUrl(rs.getString("ACCOMMADATION_LINK_URL"));
                accommodation.setAccommodationStatus(rs.getString("ACCOMMADATION_STATUS"));
                accommodation.setAccommodationStatusStatus(rs.getString("ACCOMMADATION_STATUS_STATUS"));
                accommodation.setAccommodationCreatedAt(rs.getTimestamp("ACCOMMADATION_CREATED_AT") != null
                        ? rs.getTimestamp("ACCOMMADATION_CREATED_AT").toLocalDateTime()
                        : null);
                accommodation.setAccommodationCreatedBy(rs.getInt("ACCOMMADATION_CREATED_BY"));
                accommodation.setAccommodationUpdatedAt(rs.getTimestamp("ACCOMMADATION_UPDATED_AT") != null
                        ? rs.getTimestamp("ACCOMMADATION_UPDATED_AT").toLocalDateTime()
                        : null);
                accommodation.setAccommodationUpdatedBy(rs.getInt("ACCOMMADATION_UPDATED_BY"));
                accommodation.setAccommodationTerminatedAt(rs.getTimestamp("ACCOMMADATION_TERMINATED_AT") != null
                        ? rs.getTimestamp("ACCOMMADATION_TERMINATED_AT").toLocalDateTime()
                        : null);
                accommodation.setAccommodationTerminatedBy(rs.getInt("ACCOMMADATION_TERMINATED_BY"));

                return accommodation;
            });

            LOGGER.info("Successfully fetched {} accommodations.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching accommodations: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch accommodations from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching accommodations: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching accommodations");
        }
    }

}
