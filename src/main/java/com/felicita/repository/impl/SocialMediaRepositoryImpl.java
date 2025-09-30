package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.FooterSectionDto;
import com.felicita.model.response.SocialMediaResponse;
import com.felicita.queries.FooterQueries;
import com.felicita.queries.SocialMediaQueries;
import com.felicita.repository.SocialMediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SocialMediaRepositoryImpl implements SocialMediaRepository {


    private static final Logger LOGGER = LoggerFactory.getLogger(SocialMediaRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SocialMediaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SocialMediaResponse> getAllSocialMediaData() {
        String GET_ALL_SOCIAL_MEDIA = SocialMediaQueries.GET_ALL_SOCIAL_MEDIA;
        try {
            LOGGER.info("Executing query to fetch social media...");

            return jdbcTemplate.query(GET_ALL_SOCIAL_MEDIA, (rs, rowNum) -> {
                SocialMediaResponse sm = new SocialMediaResponse();
                sm.setId(rs.getInt("id"));
                sm.setName(rs.getString("name"));
                sm.setDescription(rs.getString("description"));
                sm.setLink(rs.getString("link"));
                sm.setIconUrl(rs.getString("icon_url"));
                sm.setColor(rs.getString("color"));
                sm.setHoverColor(rs.getString("hover_color"));
                sm.setStatus(rs.getString("status"));
                return sm;
            });

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching social media: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch social media from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching social media: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching social media");
        }
    }

}
