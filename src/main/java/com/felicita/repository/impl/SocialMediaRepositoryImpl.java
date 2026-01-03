package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.FooterSectionDto;
import com.felicita.model.response.SocialMediaResponse;
import com.felicita.model.response.SocialMediaWithBestForRespone;
import com.felicita.queries.FooterQueries;
import com.felicita.queries.SocialMediaQueries;
import com.felicita.repository.SocialMediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<SocialMediaWithBestForRespone> getSocialMediaWithBestForData() {

        String GET_ALL_SOCIAL_MEDIA_WITH_BEST_FOR =
                SocialMediaQueries.GET_ALL_SOCIAL_MEDIA_WITH_BEST_FOR;

        try {
            LOGGER.info("Executing query to fetch social media with best for...");
            return jdbcTemplate.query(GET_ALL_SOCIAL_MEDIA_WITH_BEST_FOR, rs -> {
                Map<Long, SocialMediaWithBestForRespone> resultMap = new LinkedHashMap<>();
                while (rs.next()) {

                    Long socialMediaId = rs.getLong("social_media_id");

                    // 1️⃣ Create SocialMedia object if not exists
                    SocialMediaWithBestForRespone socialMedia =
                            resultMap.computeIfAbsent(socialMediaId, id ->
                                    {
                                        try {
                                            return SocialMediaWithBestForRespone.builder()
                                                    .socialMediaId(id)
                                                    .socialMediaName(rs.getString("social_media_name"))
                                                    .socialMediaUsername(rs.getString("social_media_username"))
                                                    .socialMediaDescription(rs.getString("social_media_description"))
                                                    .link(rs.getString("link"))
                                                    .iconUrl(rs.getString("icon_url"))
                                                    .color(rs.getString("color"))
                                                    .hoverColor(rs.getString("hover_color"))
                                                    .socialMediaStatus(rs.getString("social_media_status"))
                                                    .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                                                    .createdBy(rs.getLong("created_by"))
                                                    .updatedAt(rs.getTimestamp("updated_at") != null
                                                            ? rs.getTimestamp("updated_at").toLocalDateTime()
                                                            : null)
                                                    .updatedBy(rs.getObject("updated_by", Long.class))
                                                    .bestForList(new ArrayList<>())
                                                    .build();
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                            );

                    // 2️⃣ Add BestFor if exists
                    Long bestForId = rs.getObject("best_for_id", Long.class);

                    if (bestForId != null) {
                        SocialMediaWithBestForRespone.BestFor bestFor =
                                SocialMediaWithBestForRespone.BestFor.builder()
                                        .bestForId(bestForId)
                                        .bestForName(rs.getString("best_for_name"))
                                        .bestForDescription(rs.getString("best_for_description"))
                                        .bestForStatus(rs.getString("best_for_status"))
                                        .build();

                        socialMedia.getBestForList().add(bestFor);
                    }
                }

                return new ArrayList<>(resultMap.values());
            });

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching social media with best for: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler(
                    "Failed to fetch social media with best for from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching social media with best for: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler(
                    "Unexpected error occurred while fetching social media with best for");
        }
    }


}
