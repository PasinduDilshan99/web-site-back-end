package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.FaqResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.queries.FaqQueries;
import com.felicita.queries.PartnerQueries;
import com.felicita.repository.PartnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartnerRepositoryImpl implements PartnerRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartnerRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PartnerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PartnerResponse> getAllPartners() {
        String GET_ALL_PARTNERS = PartnerQueries.GET_ALL_PARTNERS;
        try {
            LOGGER.info("Executing query to fetch all partners...");

            List<PartnerResponse> results = jdbcTemplate.query(GET_ALL_PARTNERS, (rs, rowNum) -> {
                PartnerResponse partner = new PartnerResponse();

                partner.setPartnerId(rs.getInt("PARTNER_ID"));
                partner.setPartnerName(rs.getString("PARTNER_NAME"));
                partner.setPartnerCompanyName(rs.getString("PARTNER_COMPANY_NAME"));
                partner.setPartnerCompanyDescription(rs.getString("PARTNER_COMPANY_DESCRIPTION"));
                partner.setPartnerLogoUrl(rs.getString("PARTNER_LOGO_URL"));
                partner.setPartnerWebsiteUrl(rs.getString("PARTNER_WEBSITE_URL"));
                partner.setPartnerAgreement(rs.getString("PARTNER_AGREEMENT"));
                partner.setPartnerStatus(rs.getString("PARTNER_STATUS"));
                partner.setPartnerStatusStatus(rs.getString("PARTNER_STATUS_STATUS"));
                partner.setPartnerFromDate(rs.getDate("PARTNER_FROM_DATE") != null ? rs.getDate("PARTNER_FROM_DATE").toLocalDate() : null);
                partner.setPartnerToDate(rs.getDate("PARTNER_TO_DATE") != null ? rs.getDate("PARTNER_TO_DATE").toLocalDate() : null);
                partner.setPartnerCreatedAt(rs.getTimestamp("PARTNER_CREATED_AT") != null ? rs.getTimestamp("PARTNER_CREATED_AT").toLocalDateTime() : null);
                partner.setPartnerCreatedBy(rs.getInt("PARTNER_CREATED_BY"));
                partner.setPartnerUpdatedAt(rs.getTimestamp("PARTNER_UPDATED_AT") != null ? rs.getTimestamp("PARTNER_UPDATED_AT").toLocalDateTime() : null);
                partner.setPartnerUpdatedBy(rs.getInt("PARTNER_UPDATED_BY"));
                partner.setPartnerTerminatedAt(rs.getTimestamp("PARTNER_TERMINATED_AT") != null ? rs.getTimestamp("PARTNER_TERMINATED_AT").toLocalDateTime() : null);
                partner.setPartnerTerminatedBy(rs.getInt("PARTNER_TERMINATED_BY"));

                return partner;
            });

            LOGGER.info("Successfully fetched {} partners.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching partners: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch partners from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching partners: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching partners");
        }
    }

}
