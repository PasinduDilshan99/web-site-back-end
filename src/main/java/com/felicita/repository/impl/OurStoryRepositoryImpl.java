package com.felicita.repository.impl;

import com.felicita.model.response.OurStoryAndValuesResponse;
import com.felicita.queries.OurStoryQueries;
import com.felicita.repository.OurStoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class OurStoryRepositoryImpl implements OurStoryRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(OurStoryRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OurStoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<OurStoryAndValuesResponse.Timeline> getOurStoryDetails() {
        LOGGER.info("Fetching all our story details.");

        return jdbcTemplate.query(
                OurStoryQueries.GET_ALL_OUR_STORY_DETAILS,
                (rs, rowNum) -> OurStoryAndValuesResponse.Timeline.builder()
                        .storyId(rs.getInt("story_id"))
                        .yearLabel(rs.getString("year_label"))
                        .title(rs.getString("title"))
                        .description(rs.getString("description"))
                        .iconName(rs.getString("icon_name"))
                        .color(rs.getString("color"))
                        .orderNo(rs.getInt("order_no"))

                        .statusId(rs.getInt("status_id"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .createdBy(rs.getInt("created_by"))
                        .updatedAt(rs.getTimestamp("updated_at"))
                        .updatedBy(rs.getInt("updated_by"))
                        .build()
        );
    }

    @Override
    public List<OurStoryAndValuesResponse.CoreValue> getOurCoreValues() {
        LOGGER.info("Fetching all our core values.");

        return jdbcTemplate.query(
                OurStoryQueries.GET_ALL_OUR_KEY_VALUES,
                (rs, rowNum) -> OurStoryAndValuesResponse.CoreValue.builder()
                        .valueId(rs.getInt("value_id"))
                        .title(rs.getString("title"))
                        .description(rs.getString("description"))
                        .iconName(rs.getString("icon_name"))
                        .color(rs.getString("color"))
                        .orderNo(rs.getInt("order_no"))

                        .statusId(rs.getInt("status_id"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .createdBy(rs.getInt("created_by"))
                        .updatedAt(rs.getTimestamp("updated_at"))
                        .updatedBy(rs.getInt("updated_by"))
                        .build()
        );
    }

}
