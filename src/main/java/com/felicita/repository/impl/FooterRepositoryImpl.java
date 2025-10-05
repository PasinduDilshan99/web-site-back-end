package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.*;
import com.felicita.queries.FooterQueries;
import com.felicita.repository.FooterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class FooterRepositoryImpl implements FooterRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(FooterRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FooterRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<FooterSectionDto> getAllFooterData() {
        String GET_ALL_FOOTER_DATA = FooterQueries.GET_ALL_FOOTER_DATA;
        try {
            LOGGER.info("Executing query to fetch all footer data...");

            return jdbcTemplate.query(GET_ALL_FOOTER_DATA, (rs, rowNum) -> {
                FooterSectionDto section = new FooterSectionDto();
                section.setId(rs.getInt("footer_id"));
                section.setTitle(rs.getString("footer_title"));
                section.setDescription(rs.getString("footer_description"));
                section.setColor(rs.getString("footer_color"));
                section.setStatus(rs.getString("footer_status"));

                // SubItem mapping
                FooterSubItemDto subItem = new FooterSubItemDto();
                subItem.setId(rs.getInt("sub_item_id"));
                subItem.setName(rs.getString("sub_item_name"));
                subItem.setDescription(rs.getString("sub_item_description"));
                subItem.setIcon(rs.getString("sub_item_icon"));
                subItem.setLinkUrl(rs.getString("sub_item_url"));
                subItem.setStatus(rs.getString("sub_item_status"));

                // You may need to group subItems under their section later in service
                section.setSubItems(List.of(subItem));

                return section;
            });

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching footer data: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch footer data from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching footer data: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching footer data");
        }
    }

    @Override
    public List<FooterOtherDto> getAllFooterOthersData() {
        String GET_ALL_OTHERS_FOOTER_DATA = FooterQueries.GET_ALL_OTHERS_FOOTER_DATA;
        try {
            LOGGER.info("Executing query to fetch footer others data...");

            return jdbcTemplate.query(GET_ALL_OTHERS_FOOTER_DATA, (rs, rowNum) -> {
                FooterOtherDto dto = new FooterOtherDto();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setDescription(rs.getString("description"));
                dto.setLinkUrl(rs.getString("link_url"));
                dto.setStatus(rs.getString("status_name"));
                return dto;
            });

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching footer others: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch footer others from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching footer others: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching footer others");
        }
    }

}
