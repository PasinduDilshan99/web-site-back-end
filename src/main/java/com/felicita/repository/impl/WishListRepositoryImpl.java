package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.*;
import com.felicita.model.response.WishlistItemResponse;
import com.felicita.queries.WishItemsQueries;
import com.felicita.repository.WishListRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.*;

@Repository
public class WishListRepositoryImpl implements WishListRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(WishListRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbc;

    @Autowired
    public WishListRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbc = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    // --------------------------------------------------------------
    // FETCH ALL WISHLIST ITEMS FOR USER
    // --------------------------------------------------------------

    @Override
    public List<WishlistItemResponse> getWishListItems(Long userId) {
        try {
            return jdbcTemplate.query(
                    WishItemsQueries.GET_ALL_WISHLIST_ITEMS,
                    new Object[]{userId},
                    (rs, rowNum) -> WishlistItemResponse.builder()
                            .wishlistType(rs.getString("wishlist_type"))
                            .itemId(rs.getLong("item_id"))
                            .statusId(rs.getInt("status_id"))
                            .build()
            );
        } catch (DataAccessException ex) {
            LOGGER.error("DB error fetching wishlist items", ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch wishlist items");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error fetching wishlist items", ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error fetching wishlist items");
        }
    }

    // --------------------------------------------------------------
    // PACKAGES WISHLIST DETAILS
    // --------------------------------------------------------------

    @Override
    public List<PackageWishResponseDto> getPackageWishList(List<Long> ids) {
        try {
            Map<String, Object> params = Map.of("ids", ids);

            return namedJdbc.query(
                    WishItemsQueries.GET_PACKAGE_WISH_LIST_DETAILS,
                    params,
                    rs -> {

                        Map<Long, PackageWishResponseDto> map = new LinkedHashMap<>();

                        while (rs.next()) {
                            Long id = rs.getLong("package_id");

                            PackageWishResponseDto dto = map.computeIfAbsent(id, k ->
                                    {
                                        try {
                                            return PackageWishResponseDto.builder()
                                                    .packageId(id)
                                                    .packageName(rs.getString("package_name"))
                                                    .packageDescription(rs.getString("package_description"))
                                                    .packageDate(
                                                            rs.getString("package_start_date")
                                                                    + " - " +
                                                                    rs.getString("package_end_date"))
                                                    .packagePrice(rs.getDouble("package_price"))
                                                    .packageColor(rs.getString("package_color"))
                                                    .packageUrl(rs.getString("package_url"))
                                                    .tourName(rs.getString("tour_name"))
                                                    .discount(rs.getDouble("discount"))
                                                    .status(rs.getString("status"))
                                                    .createdAt(rs.getString("created_at"))
                                                    .packageImages(new ArrayList<>())
                                                    .build();
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                            );

                            String img = rs.getString("package_image");
                            if (img != null) dto.getPackageImages().add(img);
                        }
                        return new ArrayList<>(map.values());
                    }
            );

        } catch (Exception e) {
            LOGGER.error("Failed to fetch package wishlist", e);
            throw new DataAccessErrorExceptionHandler("Failed to fetch package wishlist");
        }
    }

    // --------------------------------------------------------------
    // TOURS WISHLIST DETAILS
    // --------------------------------------------------------------

    @Override
    public List<TourWishResponsesDto> getTourWishList(List<Long> ids) {
        try {
            Map<String, Object> params = Map.of("ids", ids);

            return namedJdbc.query(
                    WishItemsQueries.GET_TOUR_WISH_LIST_DETAILS,
                    params,
                    rs -> {

                        Map<Long, TourWishResponsesDto> map = new LinkedHashMap<>();

                        while (rs.next()) {
                            Long id = rs.getLong("tour_id");

                            TourWishResponsesDto dto = map.computeIfAbsent(id, k ->
                                    {
                                        try {
                                            return TourWishResponsesDto.builder()
                                                    .tourId(id)
                                                    .tourName(rs.getString("tour_name"))
                                                    .tourDescription(rs.getString("tour_description"))
                                                    .tourStartLocation(rs.getString("tour_start_location"))
                                                    .tourEndLocation(rs.getString("tour_end_location"))
                                                    .tourImages(new ArrayList<>())
                                                    .season(rs.getString("season"))
                                                    .tourUrl(rs.getString("tour_url"))
                                                    .status(rs.getString("status"))
                                                    .createdAt(rs.getString("created_at"))
                                                    .build();
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                            );

                            String img = rs.getString("tour_image");
                            if (img != null) dto.getTourImages().add(img);
                        }

                        return new ArrayList<>(map.values());
                    }
            );
        } catch (Exception e) {
            LOGGER.error("Failed to fetch tour wishlist", e);
            throw new DataAccessErrorExceptionHandler("Failed to fetch tour wishlist");
        }
    }

    // --------------------------------------------------------------
    // DESTINATIONS WISHLIST DETAILS
    // --------------------------------------------------------------

    @Override
    public List<DestinationWishResponseDto> getDestinationWishList(List<Long> ids) {
        try {
            Map<String, Object> params = Map.of("ids", ids);

            return namedJdbc.query(
                    WishItemsQueries.GET_DESTINATION_WISH_LIST_DETAILS,
                    params,
                    rs -> {

                        Map<Long, DestinationWishResponseDto> map = new LinkedHashMap<>();

                        while (rs.next()) {
                            Long id = rs.getLong("destination_id");

                            DestinationWishResponseDto dto = map.computeIfAbsent(id, k ->
                                    {
                                        try {
                                            return DestinationWishResponseDto.builder()
                                                    .destinationId(id)
                                                    .destinationName(rs.getString("destination_name"))
                                                    .destinationDescription(rs.getString("destination_description"))
                                                    .destinationLocation(rs.getString("destination_location"))
                                                    .destinationCategory(rs.getString("destination_category"))
                                                    .destinationImages(new ArrayList<>())
                                                    .destinationUrl(rs.getString("destination_url"))
                                                    .status(rs.getString("status"))
                                                    .createdAt(rs.getString("created_at"))
                                                    .build();
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                            );

                            String img = rs.getString("destination_image");
                            if (img != null) dto.getDestinationImages().add(img);
                        }

                        return new ArrayList<>(map.values());
                    }
            );
        } catch (Exception e) {
            LOGGER.error("Failed to fetch destination wishlist", e);
            throw new DataAccessErrorExceptionHandler("Failed to fetch destination wishlist");
        }
    }

    // --------------------------------------------------------------
    // ACTIVITIES WISHLIST DETAILS
    // --------------------------------------------------------------

    @Override
    public List<ActivityWishResponseDto> getActivitiesWishList(List<Long> ids) {
        try {
            Map<String, Object> params = Map.of("ids", ids);

            return namedJdbc.query(
                    WishItemsQueries.GET_ACTIVITIES_WISH_LIST_DETAILS,
                    params,
                    rs -> {

                        Map<Long, ActivityWishResponseDto> map = new LinkedHashMap<>();

                        while (rs.next()) {
                            Long id = rs.getLong("activity_id");

                            ActivityWishResponseDto dto = map.computeIfAbsent(id, k ->
                                    {
                                        try {
                                            return ActivityWishResponseDto.builder()
                                                    .activityId(id)
                                                    .activityName(rs.getString("activity_name"))
                                                    .activityDescription(rs.getString("activity_description"))
                                                    .activitiesCategory(rs.getString("activities_category"))
                                                    .season(rs.getString("season"))
                                                    .activityUrl(rs.getString("activity_url"))
                                                    .activityDuration(rs.getDouble("activity_duration"))
                                                    .status(rs.getString("status"))
                                                    .createdAt(rs.getString("created_at"))
                                                    .activityImages(new ArrayList<>())
                                                    .build();
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                            );

                            String img = rs.getString("activity_image");
                            if (img != null) dto.getActivityImages().add(img);
                        }

                        return new ArrayList<>(map.values());
                    }
            );
        } catch (Exception e) {
            LOGGER.error("Failed to fetch activity wishlist", e);
            throw new DataAccessErrorExceptionHandler("Failed to fetch activity wishlist");
        }
    }
}
