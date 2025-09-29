package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.ComponentColorsDto;
import com.felicita.model.dto.ComponentThemeColorsDto;
import com.felicita.model.dto.PageComponentColorsDto;
import com.felicita.model.response.PageColorsResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.queries.ColorsHandleQueries;
import com.felicita.queries.PartnerQueries;
import com.felicita.repository.ColorHandleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ColorHandleRepositoryImpl implements ColorHandleRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ColorHandleRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ColorHandleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PageColorsResponse getHomePageColors(String pageName) {
        String GET_HOME_PAGE_COLORS = ColorsHandleQueries.GET_HOME_PAGE_COLORS;

        try {
            LOGGER.info("Executing query to fetch colors for page: {}", pageName);

            List<Map<String, Object>> rows = jdbcTemplate.queryForList(GET_HOME_PAGE_COLORS, pageName);

            if (rows.isEmpty()) {
                throw new DataNotFoundErrorExceptionHandler("No colors found for page: " + pageName);
            }

            PageColorsResponse pageResponse = new PageColorsResponse();
            pageResponse.setPageId(((Number) rows.get(0).get("page_id")).longValue());
            pageResponse.setPageName((String) rows.get(0).get("page_name"));
            pageResponse.setPageSlug((String) rows.get(0).get("page_slug"));

            Map<Long, PageComponentColorsDto> componentMap = new LinkedHashMap<>();

            for (Map<String, Object> row : rows) {
                Long pageComponentId = ((Number) row.get("page_component_id")).longValue();
                PageComponentColorsDto pageComponent = componentMap.get(pageComponentId);

                if (pageComponent == null) {
                    pageComponent = new PageComponentColorsDto();
                    pageComponent.setPageComponentId(pageComponentId);
                    pageComponent.setOrderIndex(((Number) row.get("order_index")).intValue());
                    pageComponent.setVisible(row.get("is_visible") != null && ((Boolean) row.get("is_visible")));

                    ComponentColorsDto component = new ComponentColorsDto();
                    component.setComponentId(((Number) row.get("component_id")).longValue());
                    component.setComponentName((String) row.get("component_name"));
                    component.setComponentDescription((String) row.get("component_description"));
                    component.setThemes(new ArrayList<>());

                    pageComponent.setComponent(component);
                    componentMap.put(pageComponentId, pageComponent);
                }

                // Add theme if exists
                if (row.get("theme_id") != null) {
                    ComponentThemeColorsDto theme = new ComponentThemeColorsDto();
                    theme.setThemeId(((Number) row.get("theme_id")).longValue());
                    theme.setThemeName((String) row.get("theme_name"));
                    theme.setThemeDescription((String) row.get("theme_description"));
                    theme.setPrimaryColor((String) row.get("primary_color"));
                    theme.setSecondaryColor((String) row.get("secondary_color"));
                    theme.setBackgroundColor((String) row.get("background_color"));
                    theme.setGradientEnabled(row.get("gradient_enabled") != null && ((Boolean) row.get("gradient_enabled")));
                    theme.setGradientType((String) row.get("gradient_type"));
                    theme.setGradientDirection((String) row.get("gradient_direction"));
                    theme.setGradientStart((String) row.get("gradient_start"));
                    theme.setGradientEnd((String) row.get("gradient_end"));
                    theme.setTextPrimary((String) row.get("text_primary"));
                    theme.setTextSecondary((String) row.get("text_secondary"));
                    theme.setBannerImage((String) row.get("banner_image"));
                    theme.setCustomCss((String) row.get("custom_css"));

                    pageComponent.getComponent().getThemes().add(theme);
                }
            }

            pageResponse.setComponents(new ArrayList<>(componentMap.values()));
            return pageResponse;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching colors for page {}: {}", pageName, ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch colors from database for page: " + pageName);
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching colors for page {}: {}", pageName, ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching colors for page: " + pageName);
        }
    }



}
