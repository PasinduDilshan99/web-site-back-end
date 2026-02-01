package com.felicita.repository.impl;

import com.felicita.exception.InsertFailedErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.request.CreateInquiryRequest;
import com.felicita.queries.InquiryQueries;
import com.felicita.repository.InquiryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;

@Repository
public class InquiryRepositoryImpl implements InquiryRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(InquiryRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InquiryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createInquiry(CreateInquiryRequest createInquiryRequest, Long userId) {
        String CREATE_INQUIRY = InquiryQueries.CREATE_INQUIRY;
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            int rowsAffected = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        CREATE_INQUIRY,
                        Statement.RETURN_GENERATED_KEYS
                );
                if (userId != null) {
                    ps.setLong(1, userId);
                } else {
                    ps.setNull(1, Types.BIGINT);
                }
                ps.setString(2, createInquiryRequest.getName());
                ps.setString(3, createInquiryRequest.getCountry());
                ps.setString(4, createInquiryRequest.getEmail());
                ps.setString(5, createInquiryRequest.getPhoneNumber());
                ps.setString(6, createInquiryRequest.getPreferredContactMethod());
                ps.setString(7, createInquiryRequest.getPreferredDestination());
                if (createInquiryRequest.getArrivalDate() != null && !createInquiryRequest.getArrivalDate().isEmpty()) {
                    ps.setDate(8, Date.valueOf(createInquiryRequest.getArrivalDate()));
                } else {
                    ps.setNull(8, Types.DATE);
                }
                if (createInquiryRequest.getDepartureDate() != null && !createInquiryRequest.getDepartureDate().isEmpty()) {
                    ps.setDate(9, Date.valueOf(createInquiryRequest.getDepartureDate()));
                } else {
                    ps.setNull(9, Types.DATE);
                }
                ps.setInt(10, createInquiryRequest.getAdults() != null ? createInquiryRequest.getAdults() : 1);
                ps.setInt(11, createInquiryRequest.getKids() != null ? createInquiryRequest.getKids() : 0);
                ps.setString(12, createInquiryRequest.getMessage());
                ps.setInt(13, 1);
                if (userId != null) {
                    ps.setLong(14, userId);
                } else {
                    ps.setNull(14, Types.BIGINT);
                }
                return ps;
            }, keyHolder);

            if (rowsAffected == 0) {
                throw new InsertFailedErrorExceptionHandler("Failed to insert inquiry record");
            }

            Number generatedId = keyHolder.getKey();
            if (generatedId != null) {
                LOGGER.info("Inquiry created with ID: {}", generatedId.longValue());
            }

        } catch (InsertFailedErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error creating inquiry", e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

}
