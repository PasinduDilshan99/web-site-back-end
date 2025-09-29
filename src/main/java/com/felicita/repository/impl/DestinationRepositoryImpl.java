package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.DestinationCategoryDto;
import com.felicita.model.dto.DestinationImageDto;
import com.felicita.model.response.DestinationCategoryResponse;
import com.felicita.model.response.DestinationResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.TrendingDestinationResponse;
import com.felicita.queries.DestinationQueries;
import com.felicita.queries.PartnerQueries;
import com.felicita.repository.DestinationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class DestinationRepositoryImpl implements DestinationRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(DestinationRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DestinationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DestinationResponse> getAllDestinations() {
        String GET_ALL_DESTINATIONS = DestinationQueries.GET_ALL_DESTINATIONS;
        try {
            LOGGER.info("Executing query to fetch all destinations...");

            List<DestinationResponse> results = jdbcTemplate.query(GET_ALL_DESTINATIONS, rs -> {
                Map<Integer, DestinationResponse> destinationMap = new LinkedHashMap<>();

                while (rs.next()) {
                    int destinationId = rs.getInt("DESTINATION_ID");

                    // If not already created, create DestinationResponse
                    DestinationResponse destination = destinationMap.get(destinationId);
                    if (destination == null) {
                        destination = new DestinationResponse();
                        destination.setDestinationId(destinationId);
                        destination.setDestinationName(rs.getString("DESTINATION_NAME"));
                        destination.setDestinationDescription(rs.getString("DESTINATION_DESCRIPTION"));
                        destination.setDestinationStatus(rs.getString("DESTINATION_STATUS"));

                        // Map Category
                        DestinationCategoryDto category = new DestinationCategoryDto();
                        category.setCategoryName(rs.getString("DESTINATION_CATEGORY"));
                        category.setCategoryDescription(rs.getString("DESTINATION_CATEGORY_DESCRIPTION"));
                        category.setCategoryStatus(rs.getString("DESTINATION_CATEGORY_STATUS"));
                        category.setCategoryImageUrl(rs.getString("DESTINATION_CATEGORY_IMAGE_URL"));
                        destination.setCategory(category);

                        destination.setLocation(rs.getString("DESTINATION_LOCATION"));
                        destination.setRating(rs.getDouble("DESTINATION_RATING"));
                        destination.setPopularity(rs.getInt("DESTINATION_POPULARITY"));

                        Timestamp createdAt = rs.getTimestamp("DESTINATION_CREATED_AT");
                        if (createdAt != null) destination.setCreatedAt(createdAt.toLocalDateTime());
                        destination.setCreatedBy(rs.getInt("DESTINATION_CREATED_BY"));

                        Timestamp updatedAt = rs.getTimestamp("DESTINATION_UPDATED_AT");
                        if (updatedAt != null) destination.setUpdatedAt(updatedAt.toLocalDateTime());
                        destination.setUpdatedBy(rs.getInt("DESTINATION_UPDATED_BY"));

                        Timestamp terminatedAt = rs.getTimestamp("DESTINATION_TERMINATED_AT");
                        if (terminatedAt != null) destination.setTerminatedAt(terminatedAt.toLocalDateTime());
                        destination.setTerminatedBy(rs.getInt("DESTINATION_TERMINATED_BY"));

                        destination.setImages(new ArrayList<>());

                        destinationMap.put(destinationId, destination);
                    }

                    // Add image if exists
                    int imageId = rs.getInt("IMAGE_ID");
                    if (imageId > 0) {
                        DestinationImageDto image = new DestinationImageDto();
                        image.setImageId(imageId);
                        image.setImageName(rs.getString("IMAGE_NAME"));
                        image.setImageDescription(rs.getString("IMAGE_DESCRIPTION"));
                        image.setImageUrl(rs.getString("IMAGE_URL"));
                        image.setImageStatus(rs.getString("IMAGE_STATUS"));
                        destination.getImages().add(image);
                    }
                }
                return new ArrayList<>(destinationMap.values());
            });

            LOGGER.info("Successfully fetched {} destinations.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching destinations: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch destinations from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching destinations: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching destinations");
        }
    }

    @Override
    public List<DestinationCategoryResponse> getAllActiveDestinationsCategory() {
        String GET_ALL_ACTIVE_DESTINATIONS_CATEGORY = DestinationQueries.GET_ALL_ACTIVE_DESTINATIONS_CATEGORY;
        try {
            LOGGER.info("Executing query to fetch all destination categories...");

            List<DestinationCategoryResponse> results = jdbcTemplate.query(
                    GET_ALL_ACTIVE_DESTINATIONS_CATEGORY,
                    (rs, rowNum) -> {
                        DestinationCategoryResponse category = new DestinationCategoryResponse();
                        category.setCategoryId(rs.getInt("CATEGORY_ID"));
                        category.setCategoryName(rs.getString("CATEGORY_NAME"));
                        category.setCategoryDescription(rs.getString("CATEGORY_DESCRIPTION"));
                        category.setCategoryStatus(rs.getString("CATEGORY_STATUS"));
                        category.setCreatedAt(rs.getTimestamp("CATEGORY_CREATED_AT") != null
                                ? rs.getTimestamp("CATEGORY_CREATED_AT").toLocalDateTime()
                                : null);
                        category.setCreatedBy(rs.getObject("CATEGORY_CREATED_BY", Integer.class));
                        category.setUpdatedAt(rs.getTimestamp("CATEGORY_UPDATED_AT") != null
                                ? rs.getTimestamp("CATEGORY_UPDATED_AT").toLocalDateTime()
                                : null);
                        category.setUpdatedBy(rs.getObject("CATEGORY_UPDATED_BY", Integer.class));
                        category.setTerminatedAt(rs.getTimestamp("CATEGORY_TERMINATED_AT") != null
                                ? rs.getTimestamp("CATEGORY_TERMINATED_AT").toLocalDateTime()
                                : null);
                        category.setTerminatedBy(rs.getObject("CATEGORY_TERMINATED_BY", Integer.class));
                        category.setImageUrl(rs.getString("CATEGORY_IMAGE_URL"));
                        return category;
                    });

            LOGGER.info("Successfully fetched {} destination categories.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching destination categories: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch destination categories from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching destination categories: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching destination categories");
        }
    }

    @Override
    public List<DestinationResponse> getAllDestinationsByCategoryId(String categoryId) {
        String GET_ALL_DESTINATIONS_BY_CATEGORY_ID = DestinationQueries.GET_ALL_DESTINATIONS_BY_CATEGORY_ID;
        try {
            LOGGER.info("Executing query to fetch all destinations for categoryId={}", categoryId);

            List<DestinationResponse> results = jdbcTemplate.query(
                    GET_ALL_DESTINATIONS_BY_CATEGORY_ID,
                    new Object[]{categoryId}, // pass categoryId as parameter
                    rs -> {
                        Map<Integer, DestinationResponse> destinationMap = new LinkedHashMap<>();

                        while (rs.next()) {
                            int destinationId = rs.getInt("DESTINATION_ID");

                            // If not already created, create DestinationResponse
                            DestinationResponse destination = destinationMap.get(destinationId);
                            if (destination == null) {
                                destination = new DestinationResponse();
                                destination.setDestinationId(destinationId);
                                destination.setDestinationName(rs.getString("DESTINATION_NAME"));
                                destination.setDestinationDescription(rs.getString("DESTINATION_DESCRIPTION"));
                                destination.setDestinationStatus(rs.getString("DESTINATION_STATUS"));

                                // Map Category
                                DestinationCategoryDto category = new DestinationCategoryDto();
                                category.setCategoryName(rs.getString("DESTINATION_CATEGORY"));
                                category.setCategoryDescription(rs.getString("DESTINATION_CATEGORY_DESCRIPTION"));
                                category.setCategoryStatus(rs.getString("DESTINATION_CATEGORY_STATUS"));
                                // Note: your query does not have category image, so it will be null
                                category.setCategoryImageUrl(null);
                                destination.setCategory(category);

                                destination.setLocation(rs.getString("DESTINATION_LOCATION"));
                                destination.setRating(rs.getDouble("DESTINATION_RATING"));
                                destination.setPopularity(rs.getInt("DESTINATION_POPULARITY"));

                                Timestamp createdAt = rs.getTimestamp("DESTINATION_CREATED_AT");
                                if (createdAt != null) destination.setCreatedAt(createdAt.toLocalDateTime());
                                destination.setCreatedBy(rs.getInt("DESTINATION_CREATED_BY"));

                                Timestamp updatedAt = rs.getTimestamp("DESTINATION_UPDATED_AT");
                                if (updatedAt != null) destination.setUpdatedAt(updatedAt.toLocalDateTime());
                                destination.setUpdatedBy(rs.getInt("DESTINATION_UPDATED_BY"));

                                Timestamp terminatedAt = rs.getTimestamp("DESTINATION_TERMINATED_AT");
                                if (terminatedAt != null) destination.setTerminatedAt(terminatedAt.toLocalDateTime());
                                destination.setTerminatedBy(rs.getInt("DESTINATION_TERMINATED_BY"));

                                destination.setImages(new ArrayList<>());
                                destinationMap.put(destinationId, destination);
                            }

                            // Add image if exists
                            int imageId = rs.getInt("IMAGE_ID");
                            if (imageId > 0) {
                                DestinationImageDto image = new DestinationImageDto();
                                image.setImageId(imageId);
                                image.setImageName(rs.getString("IMAGE_NAME"));
                                image.setImageDescription(rs.getString("IMAGE_DESCRIPTION"));
                                image.setImageUrl(rs.getString("IMAGE_URL"));
                                image.setImageStatus(rs.getString("IMAGE_STATUS"));
                                destination.getImages().add(image);
                            }
                        }
                        return new ArrayList<>(destinationMap.values());
                    }
            );

            LOGGER.info("Successfully fetched {} destinations.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching destinations: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch destinations from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching destinations: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching destinations");
        }
    }

    @Override
    public DestinationResponse getDestinationDetailsById(String destinationId) {
        String GET_DESTINATION_BY_ID = DestinationQueries.GET_DESTINATION_BY_ID;
        try {
            LOGGER.info("Executing query to fetch destination details for destinationId={}", destinationId);

            List<DestinationResponse> results = jdbcTemplate.query(
                    GET_DESTINATION_BY_ID,
                    new Object[]{destinationId}, // bind destinationId
                    rs -> {
                        Map<Integer, DestinationResponse> destinationMap = new LinkedHashMap<>();

                        while (rs.next()) {
                            int destId = rs.getInt("DESTINATION_ID");

                            // If not already created, create DestinationResponse
                            DestinationResponse destination = destinationMap.get(destId);
                            if (destination == null) {
                                destination = new DestinationResponse();
                                destination.setDestinationId(destId);
                                destination.setDestinationName(rs.getString("DESTINATION_NAME"));
                                destination.setDestinationDescription(rs.getString("DESTINATION_DESCRIPTION"));
                                destination.setDestinationStatus(rs.getString("DESTINATION_STATUS"));

                                // Map Category
                                DestinationCategoryDto category = new DestinationCategoryDto();
                                category.setCategoryName(rs.getString("DESTINATION_CATEGORY"));
                                category.setCategoryDescription(rs.getString("DESTINATION_CATEGORY_DESCRIPTION"));
                                category.setCategoryStatus(rs.getString("DESTINATION_CATEGORY_STATUS"));
                                category.setCategoryImageUrl(null); // no category image in query
                                destination.setCategory(category);

                                destination.setLocation(rs.getString("DESTINATION_LOCATION"));
                                destination.setRating(rs.getDouble("DESTINATION_RATING"));
                                destination.setPopularity(rs.getInt("DESTINATION_POPULARITY"));

                                Timestamp createdAt = rs.getTimestamp("DESTINATION_CREATED_AT");
                                if (createdAt != null) destination.setCreatedAt(createdAt.toLocalDateTime());
                                destination.setCreatedBy(rs.getInt("DESTINATION_CREATED_BY"));

                                Timestamp updatedAt = rs.getTimestamp("DESTINATION_UPDATED_AT");
                                if (updatedAt != null) destination.setUpdatedAt(updatedAt.toLocalDateTime());
                                destination.setUpdatedBy(rs.getInt("DESTINATION_UPDATED_BY"));

                                Timestamp terminatedAt = rs.getTimestamp("DESTINATION_TERMINATED_AT");
                                if (terminatedAt != null) destination.setTerminatedAt(terminatedAt.toLocalDateTime());
                                destination.setTerminatedBy(rs.getInt("DESTINATION_TERMINATED_BY"));

                                destination.setImages(new ArrayList<>());
                                destinationMap.put(destId, destination);
                            }

                            // Add image if exists
                            int imageId = rs.getInt("IMAGE_ID");
                            if (imageId > 0) {
                                DestinationImageDto image = new DestinationImageDto();
                                image.setImageId(imageId);
                                image.setImageName(rs.getString("IMAGE_NAME"));
                                image.setImageDescription(rs.getString("IMAGE_DESCRIPTION"));
                                image.setImageUrl(rs.getString("IMAGE_URL"));
                                image.setImageStatus(rs.getString("IMAGE_STATUS"));
                                destination.getImages().add(image);
                            }
                        }
                        return new ArrayList<>(destinationMap.values());
                    }
            );

            if (results.isEmpty()) {
                LOGGER.warn("No destination found for id={}", destinationId);
                return null; // or throw NotFoundException
            }

            LOGGER.info("Successfully fetched destination details for id={}", destinationId);
            return results.get(0); // return single destination

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching destination: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch destination from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching destination: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching destination");
        }
    }

    @Override
    public List<DestinationCategoryResponse> getAllDestinationsCategory() {
        String GET_ALL_DESTINATIONS_CATEGORY = DestinationQueries.GET_ALL_DESTINATIONS_CATEGORY;
        try {
            LOGGER.info("Executing query to fetch all destination categories...");

            List<DestinationCategoryResponse> results = jdbcTemplate.query(
                    GET_ALL_DESTINATIONS_CATEGORY,
                    (rs, rowNum) -> {
                        DestinationCategoryResponse category = new DestinationCategoryResponse();
                        category.setCategoryId(rs.getInt("CATEGORY_ID"));
                        category.setCategoryName(rs.getString("CATEGORY_NAME"));
                        category.setCategoryDescription(rs.getString("CATEGORY_DESCRIPTION"));
                        category.setCategoryStatus(rs.getString("CATEGORY_STATUS"));
                        category.setCreatedAt(rs.getTimestamp("CATEGORY_CREATED_AT") != null
                                ? rs.getTimestamp("CATEGORY_CREATED_AT").toLocalDateTime()
                                : null);
                        category.setCreatedBy(rs.getObject("CATEGORY_CREATED_BY", Integer.class));
                        category.setUpdatedAt(rs.getTimestamp("CATEGORY_UPDATED_AT") != null
                                ? rs.getTimestamp("CATEGORY_UPDATED_AT").toLocalDateTime()
                                : null);
                        category.setUpdatedBy(rs.getObject("CATEGORY_UPDATED_BY", Integer.class));
                        category.setTerminatedAt(rs.getTimestamp("CATEGORY_TERMINATED_AT") != null
                                ? rs.getTimestamp("CATEGORY_TERMINATED_AT").toLocalDateTime()
                                : null);
                        category.setTerminatedBy(rs.getObject("CATEGORY_TERMINATED_BY", Integer.class));
                        category.setImageUrl(rs.getString("CATEGORY_IMAGE_URL"));
                        return category;
                    });

            LOGGER.info("Successfully fetched {} destination categories.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching destination categories: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch destination categories from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching destination categories: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching destination categories");
        }
    }

    @Override
    public List<DestinationResponse> getAllActiveDestinations() {
        String GET_ALL_ACTIVE_DESTINATIONS = DestinationQueries.GET_ALL_ACTIVE_DESTINATIONS;
        try {
            LOGGER.info("Executing query to fetch all destinations...");

            List<DestinationResponse> results = jdbcTemplate.query(GET_ALL_ACTIVE_DESTINATIONS, rs -> {
                Map<Integer, DestinationResponse> destinationMap = new LinkedHashMap<>();

                while (rs.next()) {
                    int destinationId = rs.getInt("DESTINATION_ID");

                    // If not already created, create DestinationResponse
                    DestinationResponse destination = destinationMap.get(destinationId);
                    if (destination == null) {
                        destination = new DestinationResponse();
                        destination.setDestinationId(destinationId);
                        destination.setDestinationName(rs.getString("DESTINATION_NAME"));
                        destination.setDestinationDescription(rs.getString("DESTINATION_DESCRIPTION"));
                        destination.setDestinationStatus(rs.getString("DESTINATION_STATUS"));

                        // Map Category
                        DestinationCategoryDto category = new DestinationCategoryDto();
                        category.setCategoryName(rs.getString("DESTINATION_CATEGORY"));
                        category.setCategoryDescription(rs.getString("DESTINATION_CATEGORY_DESCRIPTION"));
                        category.setCategoryStatus(rs.getString("DESTINATION_CATEGORY_STATUS"));
                        category.setCategoryImageUrl(rs.getString("DESTINATION_CATEGORY_IMAGE_URL"));
                        destination.setCategory(category);

                        destination.setLocation(rs.getString("DESTINATION_LOCATION"));
                        destination.setRating(rs.getDouble("DESTINATION_RATING"));
                        destination.setPopularity(rs.getInt("DESTINATION_POPULARITY"));

                        Timestamp createdAt = rs.getTimestamp("DESTINATION_CREATED_AT");
                        if (createdAt != null) destination.setCreatedAt(createdAt.toLocalDateTime());
                        destination.setCreatedBy(rs.getInt("DESTINATION_CREATED_BY"));

                        Timestamp updatedAt = rs.getTimestamp("DESTINATION_UPDATED_AT");
                        if (updatedAt != null) destination.setUpdatedAt(updatedAt.toLocalDateTime());
                        destination.setUpdatedBy(rs.getInt("DESTINATION_UPDATED_BY"));

                        Timestamp terminatedAt = rs.getTimestamp("DESTINATION_TERMINATED_AT");
                        if (terminatedAt != null) destination.setTerminatedAt(terminatedAt.toLocalDateTime());
                        destination.setTerminatedBy(rs.getInt("DESTINATION_TERMINATED_BY"));

                        destination.setImages(new ArrayList<>());

                        destinationMap.put(destinationId, destination);
                    }

                    // Add image if exists
                    int imageId = rs.getInt("IMAGE_ID");
                    if (imageId > 0) {
                        DestinationImageDto image = new DestinationImageDto();
                        image.setImageId(imageId);
                        image.setImageName(rs.getString("IMAGE_NAME"));
                        image.setImageDescription(rs.getString("IMAGE_DESCRIPTION"));
                        image.setImageUrl(rs.getString("IMAGE_URL"));
                        image.setImageStatus(rs.getString("IMAGE_STATUS"));
                        destination.getImages().add(image);
                    }
                }
                return new ArrayList<>(destinationMap.values());
            });

            LOGGER.info("Successfully fetched {} destinations.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching destinations: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch destinations from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching destinations: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching destinations");
        }
    }

    @Override
    public List<TrendingDestinationResponse> getAllTrendingDestinations() {
        String GET_ALL_TRENDING_DESTINATIONS = DestinationQueries.GET_ALL_TRENDING_DESTINATIONS;
        try {
            LOGGER.info("Executing query to fetch all trending destinations...");

            List<TrendingDestinationResponse> results = jdbcTemplate.query(GET_ALL_TRENDING_DESTINATIONS, rs -> {
                Map<Integer, TrendingDestinationResponse> trendingMap = new LinkedHashMap<>();

                while (rs.next()) {
                    int trendingId = rs.getInt("TRENDING_ID");

                    // If not already created, create TrendingDestinationResponse
                    TrendingDestinationResponse trending = trendingMap.get(trendingId);
                    if (trending == null) {
                        trending = new TrendingDestinationResponse();
                        trending.setTrendingId(trendingId);
                        trending.setTrendingStatus(rs.getString("TRENDING_STATUS"));

                        Timestamp trendingCreatedAt = rs.getTimestamp("TRENDING_CREATED_AT");
                        if (trendingCreatedAt != null) trending.setTrendingCreatedAt(trendingCreatedAt.toLocalDateTime());
                        trending.setTrendingCreatedBy(rs.getInt("TRENDING_CREATED_BY"));

                        Timestamp trendingUpdatedAt = rs.getTimestamp("TRENDING_UPDATED_AT");
                        if (trendingUpdatedAt != null) trending.setTrendingUpdatedAt(trendingUpdatedAt.toLocalDateTime());
                        trending.setTrendingUpdatedBy(rs.getInt("TRENDING_UPDATED_BY"));

                        Timestamp trendingTerminatedAt = rs.getTimestamp("TRENDING_TERMINATED_AT");
                        if (trendingTerminatedAt != null) trending.setTrendingTerminatedAt(trendingTerminatedAt.toLocalDateTime());
                        trending.setTrendingTerminatedBy(rs.getInt("TRENDING_TERMINATED_BY"));

                        // Create DestinationResponse
                        DestinationResponse destination = new DestinationResponse();
                        destination.setDestinationId(rs.getInt("DESTINATION_ID"));
                        destination.setDestinationName(rs.getString("DESTINATION_NAME"));
                        destination.setDestinationDescription(rs.getString("DESTINATION_DESCRIPTION"));
                        destination.setDestinationStatus(rs.getString("DESTINATION_STATUS"));

                        // Map Category
                        DestinationCategoryDto category = new DestinationCategoryDto();
                        category.setCategoryName(rs.getString("DESTINATION_CATEGORY"));
                        category.setCategoryDescription(rs.getString("DESTINATION_CATEGORY_DESCRIPTION"));
                        category.setCategoryStatus(rs.getString("DESTINATION_CATEGORY_STATUS"));
                        destination.setCategory(category);

                        destination.setLocation(rs.getString("DESTINATION_LOCATION"));
                        destination.setRating(rs.getDouble("DESTINATION_RATING"));
                        destination.setPopularity(rs.getInt("DESTINATION_POPULARITY"));

                        Timestamp createdAt = rs.getTimestamp("DESTINATION_CREATED_AT");
                        if (createdAt != null) destination.setCreatedAt(createdAt.toLocalDateTime());
                        destination.setCreatedBy(rs.getInt("DESTINATION_CREATED_BY"));

                        Timestamp updatedAt = rs.getTimestamp("DESTINATION_UPDATED_AT");
                        if (updatedAt != null) destination.setUpdatedAt(updatedAt.toLocalDateTime());
                        destination.setUpdatedBy(rs.getInt("DESTINATION_UPDATED_BY"));

                        Timestamp terminatedAt = rs.getTimestamp("DESTINATION_TERMINATED_AT");
                        if (terminatedAt != null) destination.setTerminatedAt(terminatedAt.toLocalDateTime());
                        destination.setTerminatedBy(rs.getInt("DESTINATION_TERMINATED_BY"));

                        destination.setImages(new ArrayList<>());

                        trending.setDestination(destination);
                        trendingMap.put(trendingId, trending);
                    }

                    // Add image if exists
                    int imageId = rs.getInt("IMAGE_ID");
                    if (imageId > 0) {
                        DestinationImageDto image = new DestinationImageDto();
                        image.setImageId(imageId);
                        image.setImageName(rs.getString("IMAGE_NAME"));
                        image.setImageDescription(rs.getString("IMAGE_DESCRIPTION"));
                        image.setImageUrl(rs.getString("IMAGE_URL"));
                        image.setImageStatus(rs.getString("IMAGE_STATUS"));
                        trending.getDestination().getImages().add(image);
                    }
                }

                return new ArrayList<>(trendingMap.values());
            });

            LOGGER.info("Successfully fetched {} trending destinations.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching trending destinations: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch trending destinations from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching trending destinations: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching trending destinations");
        }
    }

    @Override
    public List<DestinationResponse> getDestinationByIds(List<Integer> destinationIds) {
        if (destinationIds == null || destinationIds.isEmpty()) {
            return Collections.emptyList();
        }

        // Dynamically build IN clause (?, ?, ?)
        String inSql = destinationIds.stream().map(id -> "?").collect(Collectors.joining(", "));
        String sql = DestinationQueries.GET_DESTINATIONS_BY_ID.replace(":ids", inSql);

        try {
            LOGGER.info("Executing query to fetch destinations for IDs: {}", destinationIds);

            return jdbcTemplate.query(sql, ps -> {
                for (int i = 0; i < destinationIds.size(); i++) {
                    ps.setInt(i + 1, destinationIds.get(i));
                }
            }, rs -> {
                Map<Integer, DestinationResponse> destinationMap = new LinkedHashMap<>();

                while (rs.next()) {
                    int destinationId = rs.getInt("DESTINATION_ID");

                    DestinationResponse destination = destinationMap.get(destinationId);
                    if (destination == null) {
                        destination = new DestinationResponse();
                        destination.setDestinationId(destinationId);
                        destination.setDestinationName(rs.getString("DESTINATION_NAME"));
                        destination.setDestinationDescription(rs.getString("DESTINATION_DESCRIPTION"));
                        destination.setDestinationStatus(rs.getString("DESTINATION_STATUS"));

                        // Category
                        DestinationCategoryDto category = new DestinationCategoryDto();
                        category.setCategoryName(rs.getString("DESTINATION_CATEGORY"));
                        category.setCategoryDescription(rs.getString("DESTINATION_CATEGORY_DESCRIPTION"));
                        category.setCategoryStatus(rs.getString("DESTINATION_CATEGORY_STATUS"));
                        category.setCategoryImageUrl(rs.getString("DESTINATION_CATEGORY_IMAGE_URL"));
                        destination.setCategory(category);

                        destination.setLocation(rs.getString("DESTINATION_LOCATION"));
                        destination.setRating(rs.getDouble("DESTINATION_RATING"));
                        destination.setPopularity(rs.getInt("DESTINATION_POPULARITY"));

                        Timestamp createdAt = rs.getTimestamp("DESTINATION_CREATED_AT");
                        if (createdAt != null) destination.setCreatedAt(createdAt.toLocalDateTime());
                        destination.setCreatedBy(rs.getObject("DESTINATION_CREATED_BY", Integer.class));

                        Timestamp updatedAt = rs.getTimestamp("DESTINATION_UPDATED_AT");
                        if (updatedAt != null) destination.setUpdatedAt(updatedAt.toLocalDateTime());
                        destination.setUpdatedBy(rs.getObject("DESTINATION_UPDATED_BY", Integer.class));

                        Timestamp terminatedAt = rs.getTimestamp("DESTINATION_TERMINATED_AT");
                        if (terminatedAt != null) destination.setTerminatedAt(terminatedAt.toLocalDateTime());
                        destination.setTerminatedBy(rs.getObject("DESTINATION_TERMINATED_BY", Integer.class));

                        destination.setImages(new ArrayList<>());
                        destinationMap.put(destinationId, destination);
                    }

                    int imageId = rs.getInt("IMAGE_ID");
                    if (!rs.wasNull()) {
                        DestinationImageDto image = new DestinationImageDto();
                        image.setImageId(imageId);
                        image.setImageName(rs.getString("IMAGE_NAME"));
                        image.setImageDescription(rs.getString("IMAGE_DESCRIPTION"));
                        image.setImageUrl(rs.getString("IMAGE_URL"));
                        image.setImageStatus(rs.getString("IMAGE_STATUS"));
                        destination.getImages().add(image);
                    }
                }

                LOGGER.info("Successfully fetched {} destinations.", destinationMap.size());
                return new ArrayList<>(destinationMap.values());
            });

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching destinations: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch destinations from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching destinations: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching destinations");
        }
    }





}
