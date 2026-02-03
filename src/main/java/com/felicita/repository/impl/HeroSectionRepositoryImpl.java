package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.*;
import com.felicita.queries.HeroSectionQueries;
import com.felicita.repository.HeroSectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class HeroSectionRepositoryImpl implements HeroSectionRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeroSectionRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HeroSectionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<HeroSectionResponse> getAllHomeHeroSectionData() {
        String GET_ALL_HERO_SECTION = HeroSectionQueries.GET_ALL_HERO_SECTION_DATA;
        try {
            LOGGER.info("Executing query to fetch home hero section all data");

            List<HeroSectionResponse> results = jdbcTemplate.query(GET_ALL_HERO_SECTION, (rs, rowNum) -> {
                HeroSectionResponse hero = new HeroSectionResponse();

                hero.setImageId(rs.getInt("IMAGE_ID"));
                hero.setImageName(rs.getString("IMAGE_NAME"));
                hero.setImageUrl(rs.getString("IMAGE_URL"));
                hero.setImageTitle(rs.getString("IMAGE_TITLE"));
                hero.setImageSubTitle(rs.getString("IMAGE_SUB_TITLE"));
                hero.setImageDescription(rs.getString("IMAGE_DESCRIPTION"));
                hero.setImagePrimaryButtonText(rs.getString("IMAGE_PRIMARY_BUTTON_TEXT"));
                hero.setImagePrimaryButtonLink(rs.getString("IMAGE_PRIMARY_BUTTON_LINK"));
                hero.setImageSecondaryButtonText(rs.getString("IMAGE_SECONDRY_BUTTON_TEXT"));
                hero.setImageSecondaryButtonLink(rs.getString("IMAGE_SECONDRY_BUTTON_LINK"));
                hero.setImageStatus(rs.getString("IMAGE_STATUS"));
                hero.setImageStatusStatus(rs.getString("IMAGE_STATUS_STATUS"));
                hero.setImageOrder(rs.getInt("IMAGE_ORDER"));
                hero.setImageCreatedAt(rs.getTimestamp("IMAGE_CREATED_AT") != null ? rs.getTimestamp("IMAGE_CREATED_AT").toLocalDateTime() : null);
                hero.setImageCreatedBy(rs.getInt("IMAGE_CREATED_BY"));
                hero.setImageUpdatedAt(rs.getTimestamp("IMAGE_UPDATED_AT") != null ? rs.getTimestamp("IMAGE_UPDATED_AT").toLocalDateTime() : null);
                hero.setImageUpdatedBy(rs.getInt("IMAGE_UPDATED_BY"));
                hero.setImageTerminatedAt(rs.getTimestamp("IMAGE_TERMINATED_AT") != null ? rs.getTimestamp("IMAGE_TERMINATED_AT").toLocalDateTime() : null);
                hero.setImageTerminatedBy(rs.getInt("IMAGE_TERMINATED_BY"));
                return hero;
            });

            LOGGER.info("Successfully fetched {} home hero section data.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching home hero section data : {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch home hero section data from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching home hero section data : {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching home hero section data");
        }
    }

    @Override
    public List<AboutUsHeroSectionResponse> getAboutUsHeroSectionDetails() {
        String GET_ALL_ABOUT_US_HERO_SECTION_DATA = HeroSectionQueries.GET_ALL_ABOUT_US_HERO_SECTION_DATA;
        try {
            LOGGER.info("Executing query to fetch about us hero section data");

            List<AboutUsHeroSectionResponse> results = jdbcTemplate.query(GET_ALL_ABOUT_US_HERO_SECTION_DATA, (rs, rowNum) -> {
                AboutUsHeroSectionResponse hero = AboutUsHeroSectionResponse.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .imageUrl(rs.getString("image_url"))
                        .title(rs.getString("title"))
                        .subtitle(rs.getString("subtitle"))
                        .description(rs.getString("description"))
                        .primaryButtonText(rs.getString("primary_button_text"))
                        .primaryButtonLink(rs.getString("primary_button_link"))
                        .secondaryButtonText(rs.getString("secondary_button_text"))
                        .secondaryButtonLink(rs.getString("secondary_button_link"))
                        .order(rs.getInt("order"))
                        .createdAt(rs.getTimestamp("created_at") != null ?
                                rs.getTimestamp("created_at").toLocalDateTime() : null)
                        .updatedAt(rs.getTimestamp("updated_at") != null ?
                                rs.getTimestamp("updated_at").toLocalDateTime() : null)
                        .statusName(rs.getString("status_name")) // Note: This is from common_status table
                        .build();

                if (rs.wasNull()) {
                    hero.setOrder(null);
                }
                return hero;
            });

            LOGGER.info("Successfully fetched {} about us hero section data.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching about us hero section data: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch about us hero section data from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching about us hero section data: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching about us hero section data");
        }
    }

    @Override
    public List<ContactUsHeroSectionResponse> getContactUsHeroSectionDetails() {

        String GET_ALL_CONTACT_US_HERO_SECTION_DATA =
                HeroSectionQueries.GET_ALL_CONTACT_US_HERO_SECTION_DATA;

        try {
            LOGGER.info("Executing query to fetch contact us hero section data");

            List<ContactUsHeroSectionResponse> results =
                    jdbcTemplate.query(GET_ALL_CONTACT_US_HERO_SECTION_DATA, (rs, rowNum) -> {
                        Integer order = rs.getInt("order");
                        if (rs.wasNull()) {
                            order = null;
                        }
                        return ContactUsHeroSectionResponse.builder()
                                .id(rs.getLong("id"))
                                .name(rs.getString("name"))
                                .imageUrl(rs.getString("image_url"))
                                .title(rs.getString("title"))
                                .subtitle(rs.getString("subtitle"))
                                .description(rs.getString("description"))
                                .primaryButtonText(rs.getString("primary_button_text"))
                                .primaryButtonLink(rs.getString("primary_button_link"))
                                .secondaryButtonText(rs.getString("secondary_button_text"))
                                .secondaryButtonLink(rs.getString("secondary_button_link"))
                                .order(order)
                                .statusName(rs.getString("status_name"))
                                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                                .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                                .build();
                    });

            LOGGER.info("Successfully fetched {} contact us hero section data.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching contact us hero section data: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler(
                    "Failed to fetch contact us hero section data from database"
            );

        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching contact us hero section data: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler(
                    "Unexpected error occurred while fetching contact us hero section data"
            );
        }
    }

    @Override
    public List<BlogHeroSectionResponse> getBlogHeroSectionDetails() {

        String GET_ALL_BLOG_HERO_SECTION_DATA = HeroSectionQueries.GET_ALL_BLOG_HERO_SECTION_DATA;

        try {
            LOGGER.info("Executing query to fetch blog hero section data");

            List<BlogHeroSectionResponse> results = jdbcTemplate.query(
                    GET_ALL_BLOG_HERO_SECTION_DATA,
                    (rs, rowNum) -> {

                        Integer order = rs.getInt("order");
                        if (rs.wasNull()) {
                            order = null;
                        }

                        return BlogHeroSectionResponse.builder()
                                .id(rs.getInt("id"))
                                .name(rs.getString("name"))
                                .imageUrl(rs.getString("image_url"))
                                .title(rs.getString("title"))
                                .subtitle(rs.getString("subtitle"))
                                .description(rs.getString("description"))
                                .primaryButtonText(rs.getString("primary_button_text"))
                                .primaryButtonLink(rs.getString("primary_button_link"))
                                .secondaryButtonText(rs.getString("secondary_button_text"))
                                .secondaryButtonLink(rs.getString("secondary_button_link"))
                                .order(order)
                                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                                .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                                .statusName(rs.getString("status_name"))
                                .build();
                    }
            );

            LOGGER.info("Successfully fetched {} blog hero section data.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching blog hero section data: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler(
                    "Failed to fetch blog hero section data from database"
            );

        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching blog hero section data: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler(
                    "Unexpected error occurred while fetching blog hero section data"
            );
        }
    }

    @Override
    public List<FaqHeroSectionResponse> getFAQHeroSectionDetails() {
        String GET_ALL_FAQ_HERO_SECTION_DATA = HeroSectionQueries.GET_ALL_FAQ_HERO_SECTION_DATA;

        try {
            LOGGER.info("Executing query to fetch faq hero section data");

            List<FaqHeroSectionResponse> results = jdbcTemplate.query(
                    GET_ALL_FAQ_HERO_SECTION_DATA,
                    (rs, rowNum) -> {

                        Integer order = rs.getInt("order");
                        if (rs.wasNull()) {
                            order = null;
                        }

                        return FaqHeroSectionResponse.builder()
                                .id(rs.getLong("id"))
                                .name(rs.getString("name"))
                                .imageUrl(rs.getString("image_url"))
                                .title(rs.getString("title"))
                                .subtitle(rs.getString("subtitle"))
                                .description(rs.getString("description"))
                                .primaryButtonText(rs.getString("primary_button_text"))
                                .primaryButtonLink(rs.getString("primary_button_link"))
                                .secondaryButtonText(rs.getString("secondary_button_text"))
                                .secondaryButtonLink(rs.getString("secondary_button_link"))
                                .status(rs.getString("status"))
                                .order(order)
                                .createdAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null)
                                .createdBy(rs.getObject("created_by", Integer.class))
                                .updatedAt(rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null)
                                .updatedBy(rs.getObject("updated_by", Integer.class))
                                .terminatedAt(rs.getTimestamp("terminated_at") != null ? rs.getTimestamp("terminated_at").toLocalDateTime() : null)
                                .terminatedBy(rs.getObject("terminated_by", Integer.class))
                                .build();
                    }
            );

            LOGGER.info("Successfully fetched {} faq hero section data.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching faq hero section data: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler(
                    "Failed to fetch faq hero section data from database"
            );

        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching faq hero section data: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler(
                    "Unexpected error occurred while fetching faq hero section data"
            );
        }
    }

    @Override
    public List<TourHeroSectionResponse> getTourHeroSectionDetails() {
        String GET_ALL_TOUR_HERO_SECTION_DATA = HeroSectionQueries.GET_ALL_TOUR_HERO_SECTION_DATA;
        try {
            LOGGER.info("Executing query to fetch tour hero section data");
            List<TourHeroSectionResponse> results = jdbcTemplate.query(
                    GET_ALL_TOUR_HERO_SECTION_DATA,
                    (rs, rowNum) -> {
                        Integer order = rs.getInt("order");
                        if (rs.wasNull()) {
                            order = null;
                        }
                        return TourHeroSectionResponse.builder()
                                .id(rs.getLong("id"))
                                .name(rs.getString("name"))
                                .imageUrl(rs.getString("image_url"))
                                .title(rs.getString("title"))
                                .subtitle(rs.getString("subtitle"))
                                .description(rs.getString("description"))
                                .primaryButtonText(rs.getString("primary_button_text"))
                                .primaryButtonLink(rs.getString("primary_button_link"))
                                .secondaryButtonText(rs.getString("secondary_button_text"))
                                .secondaryButtonLink(rs.getString("secondary_button_link"))
                                .status(rs.getString("status"))
                                .order(order)
                                .createdAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null)
                                .createdBy(rs.getObject("created_by", Integer.class))
                                .updatedAt(rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null)
                                .updatedBy(rs.getObject("updated_by", Integer.class))
                                .terminatedAt(rs.getTimestamp("terminated_at") != null ? rs.getTimestamp("terminated_at").toLocalDateTime() : null)
                                .terminatedBy(rs.getObject("terminated_by", Integer.class))
                                .build();
                    }
            );
            LOGGER.info("Successfully fetched {} tour hero section data.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching tour hero section data: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler(
                    "Failed to fetch tour hero section data from database"
            );

        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching tour hero section data: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler(
                    "Unexpected error occurred while fetching tour hero section data"
            );
        }
    }

    @Override
    public List<ActivityHeroSectionResponse> getActivityHeroSectionDetails() {
        String GET_ALL_ACTIVITY_HERO_SECTION_DATA =
                HeroSectionQueries.GET_ALL_ACTIVITY_HERO_SECTION_DATA;

        try {
            LOGGER.info("Executing query to fetch activity hero section data");

            List<ActivityHeroSectionResponse> results = jdbcTemplate.query(
                    GET_ALL_ACTIVITY_HERO_SECTION_DATA,
                    (rs, rowNum) -> {

                        Integer order = rs.getInt("order");
                        if (rs.wasNull()) {
                            order = null;
                        }

                        return ActivityHeroSectionResponse.builder()
                                .id(rs.getLong("id"))
                                .name(rs.getString("name"))
                                .imageUrl(rs.getString("image_url"))
                                .title(rs.getString("title"))
                                .subtitle(rs.getString("subtitle"))
                                .description(rs.getString("description"))

                                .primaryButtonText(rs.getString("primary_button_text"))
                                .primaryButtonLink(rs.getString("primary_button_link"))

                                .secondaryButtonText(rs.getString("secondary_button_text"))
                                .secondaryButtonLink(rs.getString("secondary_button_link"))

                                .status(rs.getString("status"))
                                .order(order)

                                .createdAt(rs.getTimestamp("created_at") != null
                                        ? rs.getTimestamp("created_at").toLocalDateTime()
                                        : null)
                                .createdBy(rs.getInt("created_by"))

                                .updatedAt(rs.getTimestamp("updated_at") != null
                                        ? rs.getTimestamp("updated_at").toLocalDateTime()
                                        : null)
                                .updatedBy(rs.getInt("updated_by"))

                                .terminatedAt(rs.getTimestamp("terminated_at") != null
                                        ? rs.getTimestamp("terminated_at").toLocalDateTime()
                                        : null)
                                .terminatedBy(rs.getInt("terminated_by"))
                                .build();
                    }
            );

            LOGGER.info("Successfully fetched {} activity hero section data.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching activity hero section data: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler(
                    "Failed to fetch activity hero section data from database"
            );

        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching activity hero section data: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler(
                    "Unexpected error occurred while fetching activity hero section data"
            );
        }
    }

    @Override
    public List<DestinationHeroSectionResponse> getDestinationHeroSectionDetails() {
        String GET_ALL_DESTINATION_HERO_SECTION_DATA =
                HeroSectionQueries.GET_ALL_DESTINATION_HERO_SECTION_DATA;

        try {
            LOGGER.info("Executing query to fetch destination hero section data");

            List<DestinationHeroSectionResponse> results = jdbcTemplate.query(
                    GET_ALL_DESTINATION_HERO_SECTION_DATA,
                    (rs, rowNum) -> {

                        Integer order = rs.getInt("order");
                        if (rs.wasNull()) {
                            order = null;
                        }

                        return DestinationHeroSectionResponse.builder()
                                .id(rs.getLong("id"))
                                .name(rs.getString("name"))
                                .imageUrl(rs.getString("image_url"))
                                .title(rs.getString("title"))
                                .subtitle(rs.getString("subtitle"))
                                .description(rs.getString("description"))

                                .primaryButtonText(rs.getString("primary_button_text"))
                                .primaryButtonLink(rs.getString("primary_button_link"))

                                .secondaryButtonText(rs.getString("secondary_button_text"))
                                .secondaryButtonLink(rs.getString("secondary_button_link"))

                                .status(rs.getString("status"))
                                .order(order)

                                .createdAt(rs.getTimestamp("created_at") != null
                                        ? rs.getTimestamp("created_at").toLocalDateTime()
                                        : null)
                                .createdBy(rs.getInt("created_by"))

                                .updatedAt(rs.getTimestamp("updated_at") != null
                                        ? rs.getTimestamp("updated_at").toLocalDateTime()
                                        : null)
                                .updatedBy(rs.getInt("updated_by"))

                                .terminatedAt(rs.getTimestamp("terminated_at") != null
                                        ? rs.getTimestamp("terminated_at").toLocalDateTime()
                                        : null)
                                .terminatedBy(rs.getInt("terminated_by"))
                                .build();
                    }
            );

            LOGGER.info("Successfully fetched {} destination hero section data.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching destination hero section data: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler(
                    "Failed to fetch destination hero section data from database"
            );

        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching destination hero section data: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler(
                    "Unexpected error occurred while fetching destination hero section data"
            );
        }
    }

    @Override
    public List<PackageHeroSectionResponse> getPackageHeroSectionDetails() {
        String GET_ALL_PACKAGE_HERO_SECTION_DATA = HeroSectionQueries.GET_ALL_PACKAGE_HERO_SECTION_DATA;

        try {
            LOGGER.info("Executing query to fetch package hero section data");

            List<PackageHeroSectionResponse> results = jdbcTemplate.query(
                    GET_ALL_PACKAGE_HERO_SECTION_DATA,
                    (rs, rowNum) -> {

                        Integer order = rs.getInt("order");
                        if (rs.wasNull()) {
                            order = null;
                        }

                        return PackageHeroSectionResponse.builder()
                                .id(rs.getLong("id"))
                                .name(rs.getString("name"))
                                .imageUrl(rs.getString("image_url"))
                                .title(rs.getString("title"))
                                .subtitle(rs.getString("subtitle"))
                                .description(rs.getString("description"))

                                .primaryButtonText(rs.getString("primary_button_text"))
                                .primaryButtonLink(rs.getString("primary_button_link"))

                                .secondaryButtonText(rs.getString("secondary_button_text"))
                                .secondaryButtonLink(rs.getString("secondary_button_link"))

                                .status(rs.getString("status"))
                                .order(order)

                                .createdAt(rs.getTimestamp("created_at") != null
                                        ? rs.getTimestamp("created_at").toLocalDateTime()
                                        : null)
                                .createdBy(rs.getInt("created_by"))

                                .updatedAt(rs.getTimestamp("updated_at") != null
                                        ? rs.getTimestamp("updated_at").toLocalDateTime()
                                        : null)
                                .updatedBy(rs.getInt("updated_by"))

                                .terminatedAt(rs.getTimestamp("terminated_at") != null
                                        ? rs.getTimestamp("terminated_at").toLocalDateTime()
                                        : null)
                                .terminatedBy(rs.getInt("terminated_by"))
                                .build();
                    }
            );

            LOGGER.info("Successfully fetched {} package hero section data.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching package hero section data: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler(
                    "Failed to fetch package hero section data from database"
            );

        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching package hero section data: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler(
                    "Unexpected error occurred while fetching package hero section data"
            );
        }
    }

    @Override
    public List<PackageScheduleHeroSectionResponse> getPackageScheduleHeroSectionDetails(Long packageScheduleId) {
        String GET_ALL_PACKAGE_SCHEDULE_HERO_SECTION_DATA = HeroSectionQueries.GET_ALL_PACKAGE_SCHEDULE_HERO_SECTION_DATA;

        try {
            LOGGER.info("Executing query to fetch package schedule hero section data");

            List<PackageScheduleHeroSectionResponse> results = jdbcTemplate.query(
                    GET_ALL_PACKAGE_SCHEDULE_HERO_SECTION_DATA,
                    new Object[]{packageScheduleId},
                    (rs, rowNum) -> PackageScheduleHeroSectionResponse.builder()
                            .id(rs.getLong("id"))
                            .name(rs.getString("name"))
                            .description(rs.getString("description"))
                            .imageUrl(rs.getString("image_url"))
                            .color(rs.getString("color"))
                            .build()
            );
            return results;
        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching package hero section data: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler(
                    "Failed to fetch package hero section data from database"
            );

        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching package hero section data: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler(
                    "Unexpected error occurred while fetching package hero section data"
            );
        }
    }

    @Override
    public List<BookedTourHeroSectionResponse> getBookedTourHeroSectionDetails(Long bookingId) {
        String GET_BOOKED_TOUR_HERO_SECTION_DATA = HeroSectionQueries.GET_BOOKED_TOUR_HERO_SECTION_DATA;

        try {
            LOGGER.info("Executing query to fetch booked tour hero section data");

            List<BookedTourHeroSectionResponse> results = jdbcTemplate.query(
                    GET_BOOKED_TOUR_HERO_SECTION_DATA,
                    new Object[]{bookingId},
                    (rs, rowNum) -> BookedTourHeroSectionResponse.builder()
                            .id(rs.getLong("id"))
                            .name(rs.getString("name"))
                            .description(rs.getString("description"))
                            .imageUrl(rs.getString("image_url"))
                            .build()
            );
            return results;
        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching booked tour hero section data: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler(
                    "Failed to fetch booked tour hero section data from database"
            );

        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching booked tour hero section data: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler(
                    "Unexpected error occurred while fetching booked tour hero section data"
            );
        }
    }

    @Override
    public List<ActivityDetailsHeroSectionResponse> getActivityHeroSectionDetailsByActivityId(Long activityId) {

        try {
            LOGGER.info(
                    "Executing query to fetch activity hero section data by activity id : {}",
                    activityId
            );

            List<ActivityDetailsHeroSectionResponse> results = jdbcTemplate.query(
                    HeroSectionQueries.GET_ACTIVITY_HERO_SECTION_DATA_BY_ACTIVITY_ID,
                    new Object[]{activityId},
                    (rs, rowNum) -> ActivityDetailsHeroSectionResponse.builder()
                            .activityId(rs.getLong("activity_id"))
                            .imageId(rs.getLong("image_id"))
                            .name(rs.getString("name"))
                            .imageUrl(rs.getString("image_url"))
                            .description(rs.getString("description"))
                            .status(rs.getString("status"))
                            .build()
            );

            LOGGER.info(
                    "Successfully fetched {} activity hero section data by activity id : {}.",
                    results.size(),
                    activityId
            );

            return results;

        } catch (DataAccessException ex) {
            LOGGER.error(
                    "Database error while fetching activity hero section data by activity id : {} , {}",
                    activityId, ex.getMessage(), ex
            );
            throw new DataAccessErrorExceptionHandler(
                    "Failed to fetch activity hero section data by activity id : " + activityId
            );

        } catch (Exception ex) {
            LOGGER.error(
                    "Unexpected error while fetching activity hero section data by activity id : {} , {}",
                    activityId, ex.getMessage(), ex
            );
            throw new InternalServerErrorExceptionHandler(
                    "Unexpected error occurred while fetching activity hero section data by activity id : " + activityId
            );
        }
    }


}
