package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InsertFailedErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.exception.UpdateFailedErrorExceptionHandler;
import com.felicita.model.dto.*;
import com.felicita.model.request.ActivityWishListInsertRequest;
import com.felicita.model.request.DestinationWishListInsertRequest;
import com.felicita.model.request.PackageWishListInsertRequest;
import com.felicita.model.request.TourWishListInsertRequest;
import com.felicita.model.response.WishlistItemResponse;
import com.felicita.queries.BlogQueries;
import com.felicita.queries.WishItemsQueries;
import com.felicita.repository.WishListRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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

    @Override
    public Long addActivityWishList(ActivityWishListInsertRequest activityWishListInsertRequest, Long userId) {
        String INSERT_ACTIVITY_WISH_DATA = WishItemsQueries.INSERT_ACTIVITY_WISH_DATA;
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            int rowsAffected = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT_ACTIVITY_WISH_DATA, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, userId);
                ps.setLong(2, activityWishListInsertRequest.getActivityId());
                return ps;
            }, keyHolder);

            if (rowsAffected == 0) {
                throw new InsertFailedErrorExceptionHandler("No rows affected when inserting activity wish data");
            }
            Number generatedId = keyHolder.getKey();
            if (generatedId == null) {
                throw new InsertFailedErrorExceptionHandler("Failed to retrieve inserted activity wish ID");
            }
            Long activityWishId = generatedId.longValue();
            LOGGER.info("Inserted activity wish ID: {}", activityWishId);
            return activityWishId;
        } catch (InsertFailedErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public void addActivityWishListHistory(ActivityWishListInsertRequest activityWishListInsertRequest,
                                           Long userId,
                                           Long wishListId,
                                           String status) {
        String INSERT_ACTIVITY_WISHLIST_HISTORY = WishItemsQueries.INSERT_ACTIVITY_WISHLIST_HISTORY;
        try {
            int rowsAffected = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT_ACTIVITY_WISHLIST_HISTORY);
                ps.setLong(1, userId);
                ps.setLong(2, activityWishListInsertRequest.getActivityId());
                ps.setLong(3, wishListId);
                ps.setString(4, status);
                return ps;
            });
            if (rowsAffected == 0) {
                throw new InsertFailedErrorExceptionHandler(
                        "No rows affected when inserting activity wishlist history"
                );
            }
            LOGGER.info("Inserted activity wishlist history for wishlist ID: {}", wishListId);
        } catch (InsertFailedErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error inserting activity wishlist history", e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }


    @Override
    public void updateActivityWishList(ActivityWishListInsertRequest activityWishListInsertRequest,
                                       Long userId,
                                       ExistActivityWishListDataDto existActivityWishListDataDto) {
        try {
            if (existActivityWishListDataDto == null) {
                LOGGER.warn("Wishlist data not found");
                return;
            }
            String currentStatus = existActivityWishListDataDto.getStatus();
            Long wishListId = existActivityWishListDataDto.getWishListId();
            String newStatus;

            if ("ACTIVE".equalsIgnoreCase(currentStatus)) {
                newStatus = "INACTIVE";
            } else if ("INACTIVE".equalsIgnoreCase(currentStatus)) {
                newStatus = "ACTIVE";
            } else {
                LOGGER.warn("Status is neither ACTIVE nor INACTIVE. No update performed.");
                return;
            }

            int rowsAffected = jdbcTemplate.update(
                    WishItemsQueries.UPDATE_ACTIVITY_WISHLIST_STATUS,
                    newStatus,
                    wishListId,
                    userId
            );
            if (rowsAffected == 0) {
                throw new UpdateFailedErrorExceptionHandler("Failed to update activity wishlist");
            }
            LOGGER.info("Wishlist status toggled successfully. Wishlist ID: {}, New Status: {}", wishListId, newStatus);
        } catch (UpdateFailedErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error updating wishlist status", e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public ExistActivityWishListDataDto getExistingWishListData(
            Long userId,
            ActivityWishListInsertRequest activityWishListInsertRequest) {
        try {
            return jdbcTemplate.query(
                    WishItemsQueries.GET_EXISTING_ACTIVITY_WISHLIST_DATA,
                    new Object[]{
                            userId,
                            activityWishListInsertRequest.getActivityId()
                    },
                    rs -> {
                        if (rs.next()) {
                            return ExistActivityWishListDataDto.builder()
                                    .wishListId(rs.getLong("wishListId"))
                                    .activityId(rs.getLong("activityId"))
                                    .userId(rs.getLong("userId"))
                                    .status(rs.getString("status"))
                                    .createdAt(
                                            rs.getTimestamp("createdAt") != null
                                                    ? rs.getTimestamp("createdAt").toLocalDateTime()
                                                    : null
                                    )
                                    .updatedAt(
                                            rs.getTimestamp("updatedAt") != null
                                                    ? rs.getTimestamp("updatedAt").toLocalDateTime()
                                                    : null
                                    )
                                    .build();
                        }
                        return null;
                    }
            );
        } catch (Exception e) {
            LOGGER.error("Error fetching existing wishlist data", e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public Long addDestinationWishList(DestinationWishListInsertRequest destinationWishListInsertRequest, Long userId) {
        String INSERT_DESTINATION_WISH_DATA = WishItemsQueries.INSERT_DESTINATION_WISH_DATA;
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            int rowsAffected = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT_DESTINATION_WISH_DATA, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, userId);
                ps.setLong(2, destinationWishListInsertRequest.getDestinationId());
                return ps;
            }, keyHolder);

            if (rowsAffected == 0) {
                throw new InsertFailedErrorExceptionHandler("No rows affected when inserting destination wish data");
            }
            Number generatedId = keyHolder.getKey();
            if (generatedId == null) {
                throw new InsertFailedErrorExceptionHandler("Failed to retrieve inserted destination wish ID");
            }
            Long destinationWishId = generatedId.longValue();
            LOGGER.info("Inserted destination wish ID: {}", destinationWishId);
            return destinationWishId;
        } catch (InsertFailedErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }


    @Override
    public void addDestinationWishListHistory(DestinationWishListInsertRequest destinationWishListInsertRequest,
                                              Long userId,
                                              Long wishListId,
                                              String status) {
        String INSERT_DESTINATION_WISHLIST_HISTORY = WishItemsQueries.INSERT_DESTINATION_WISHLIST_HISTORY;
        try {
            int rowsAffected = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT_DESTINATION_WISHLIST_HISTORY);
                ps.setLong(1, userId);
                ps.setLong(2, destinationWishListInsertRequest.getDestinationId());
                ps.setLong(3, wishListId);
                ps.setString(4, status);
                return ps;
            });
            if (rowsAffected == 0) {
                throw new InsertFailedErrorExceptionHandler(
                        "No rows affected when inserting destination wishlist history"
                );
            }
            LOGGER.info("Inserted destination wishlist history for wishlist ID: {}", wishListId);
        } catch (InsertFailedErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error inserting destination wishlist history", e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }


    @Override
    public void updateDestinationWishList(DestinationWishListInsertRequest destinationWishListInsertRequest,
                                          Long userId,
                                          ExistDestinationWishListDataDto existDestinationWishListDataDto) {
        try {
            if (existDestinationWishListDataDto == null) {
                LOGGER.warn("Wishlist data not found");
                return;
            }
            String currentStatus = existDestinationWishListDataDto.getStatus();
            Long wishListId = existDestinationWishListDataDto.getWishListId();
            String newStatus;

            if ("ACTIVE".equalsIgnoreCase(currentStatus)) {
                newStatus = "INACTIVE";
            } else if ("INACTIVE".equalsIgnoreCase(currentStatus)) {
                newStatus = "ACTIVE";
            } else {
                LOGGER.warn("Status is neither ACTIVE nor INACTIVE. No update performed.");
                return;
            }

            int rowsAffected = jdbcTemplate.update(
                    WishItemsQueries.UPDATE_DESTINATION_WISHLIST_STATUS,
                    newStatus,
                    wishListId,
                    userId
            );
            if (rowsAffected == 0) {
                throw new UpdateFailedErrorExceptionHandler("Failed to update destination wishlist");
            }
            LOGGER.info("Wishlist status toggled successfully. Wishlist ID: {}, New Status: {}", wishListId, newStatus);
        } catch (UpdateFailedErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error updating wishlist status", e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public ExistDestinationWishListDataDto getExistingDestinationWishListData(
            Long userId,
            DestinationWishListInsertRequest destinationWishListInsertRequest) {
        try {
            return jdbcTemplate.query(
                    WishItemsQueries.GET_EXISTING_DESTINATION_WISHLIST_DATA,
                    new Object[]{
                            userId,
                            destinationWishListInsertRequest.getDestinationId()
                    },
                    rs -> {
                        if (rs.next()) {
                            return ExistDestinationWishListDataDto.builder()
                                    .wishListId(rs.getLong("wishListId"))
                                    .destinationId(rs.getLong("destinationId"))
                                    .userId(rs.getLong("userId"))
                                    .status(rs.getString("status"))
                                    .createdAt(
                                            rs.getTimestamp("createdAt") != null
                                                    ? rs.getTimestamp("createdAt").toLocalDateTime()
                                                    : null
                                    )
                                    .updatedAt(
                                            rs.getTimestamp("updatedAt") != null
                                                    ? rs.getTimestamp("updatedAt").toLocalDateTime()
                                                    : null
                                    )
                                    .build();
                        }
                        return null;
                    }
            );
        } catch (Exception e) {
            LOGGER.error("Error fetching existing wishlist data", e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public Long addTourWishList(TourWishListInsertRequest tourWishListInsertRequest, Long userId) {
        String INSERT_TOUR_WISH_DATA = WishItemsQueries.INSERT_TOUR_WISH_DATA;
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            int rowsAffected = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT_TOUR_WISH_DATA, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, userId);
                ps.setLong(2, tourWishListInsertRequest.getTourId());
                return ps;
            }, keyHolder);

            if (rowsAffected == 0) {
                throw new InsertFailedErrorExceptionHandler("No rows affected when inserting tour wish data");
            }
            Number generatedId = keyHolder.getKey();
            if (generatedId == null) {
                throw new InsertFailedErrorExceptionHandler("Failed to retrieve inserted tour wish ID");
            }
            Long tourWishId = generatedId.longValue();
            LOGGER.info("Inserted tour wish ID: {}", tourWishId);
            return tourWishId;
        } catch (InsertFailedErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public void addTourWishListHistory(TourWishListInsertRequest tourWishListInsertRequest,
                                       Long userId,
                                       Long wishListId,
                                       String status) {
        String INSERT_TOUR_WISHLIST_HISTORY = WishItemsQueries.INSERT_TOUR_WISHLIST_HISTORY;
        try {
            int rowsAffected = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT_TOUR_WISHLIST_HISTORY);
                ps.setLong(1, userId);
                ps.setLong(2, tourWishListInsertRequest.getTourId());
                ps.setLong(3, wishListId);
                ps.setString(4, status);
                return ps;
            });
            if (rowsAffected == 0) {
                throw new InsertFailedErrorExceptionHandler(
                        "No rows affected when inserting tour wishlist history"
                );
            }
            LOGGER.info("Inserted tour wishlist history for wishlist ID: {}", wishListId);
        } catch (InsertFailedErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error inserting tour wishlist history", e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }


    @Override
    public void updateTourWishList(TourWishListInsertRequest tourWishListInsertRequest,
                                   Long userId,
                                   ExistTourWishListDataDto existTourWishListDataDto) {
        try {
            if (existTourWishListDataDto == null) {
                LOGGER.warn("Wishlist data not found");
                return;
            }
            String currentStatus = existTourWishListDataDto.getStatus();
            Long wishListId = existTourWishListDataDto.getWishListId();
            String newStatus;

            if ("ACTIVE".equalsIgnoreCase(currentStatus)) {
                newStatus = "INACTIVE";
            } else if ("INACTIVE".equalsIgnoreCase(currentStatus)) {
                newStatus = "ACTIVE";
            } else {
                LOGGER.warn("Status is neither ACTIVE nor INACTIVE. No update performed.");
                return;
            }

            int rowsAffected = jdbcTemplate.update(
                    WishItemsQueries.UPDATE_TOUR_WISHLIST_STATUS,
                    newStatus,
                    wishListId,
                    userId
            );
            if (rowsAffected == 0) {
                throw new UpdateFailedErrorExceptionHandler("Failed to update tour wishlist");
            }
            LOGGER.info("Wishlist status toggled successfully. Wishlist ID: {}, New Status: {}", wishListId, newStatus);
        } catch (UpdateFailedErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error updating wishlist status", e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public List<Long> getAllActivityWishListByUserId(Long userId) {
        String sql = """
                    SELECT activity_id 
                    FROM activity_wishlist 
                    WHERE user_id = ? 
                      AND status_id = (
                          SELECT cs.id
                          FROM common_status cs
                          WHERE cs.name = ?
                          LIMIT 1
                      )
                """;

        try {
            return jdbcTemplate.query(
                    sql,
                    new Object[]{userId, "ACTIVE"},
                    (rs, rowNum) -> rs.getLong("activity_id")
            );
        } catch (Exception e) {
            LOGGER.error("Error fetching existing wishlist data", e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public List<Long> getAllDestinationWishListByUserId(Long userId) {
        String sql = """
                    SELECT destination_id 
                    FROM destination_wishlist 
                    WHERE user_id = ? 
                      AND status_id = (
                          SELECT cs.id
                          FROM common_status cs
                          WHERE cs.name = ?
                          LIMIT 1
                      )
                """;

        try {
            return jdbcTemplate.query(
                    sql,
                    new Object[]{userId, "ACTIVE"},
                    (rs, rowNum) -> rs.getLong("destination_id")
            );
        } catch (Exception e) {
            LOGGER.error("Error fetching existing wishlist data", e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public List<Long> getAllTourWishListByUserId(Long userId) {
        String sql = """
                    SELECT tour_id 
                    FROM tour_wishlist 
                    WHERE user_id = ? 
                      AND status_id = (
                          SELECT cs.id
                          FROM common_status cs
                          WHERE cs.name = ?
                          LIMIT 1
                      )
                """;

        try {
            return jdbcTemplate.query(
                    sql,
                    new Object[]{userId, "ACTIVE"},
                    (rs, rowNum) -> rs.getLong("tour_id")
            );
        } catch (Exception e) {
            LOGGER.error("Error fetching existing wishlist data", e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public List<Long> getAllPackageWishListByUserId(Long userId) {
        String sql = """
                    SELECT package_id 
                    FROM package_wishlist 
                    WHERE user_id = ? 
                      AND status_id = (
                          SELECT cs.id
                          FROM common_status cs
                          WHERE cs.name = ?
                          LIMIT 1
                      )
                """;

        try {
            return jdbcTemplate.query(
                    sql,
                    new Object[]{userId, "ACTIVE"},
                    (rs, rowNum) -> rs.getLong("package_id")
            );
        } catch (Exception e) {
            LOGGER.error("Error fetching existing wishlist data", e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }


    @Override
    public ExistTourWishListDataDto getExistingTourWishListData(
            Long userId,
            TourWishListInsertRequest tourWishListInsertRequest) {
        try {
            return jdbcTemplate.query(
                    WishItemsQueries.GET_EXISTING_TOUR_WISHLIST_DATA,
                    new Object[]{
                            userId,
                            tourWishListInsertRequest.getTourId()
                    },
                    rs -> {
                        if (rs.next()) {
                            return ExistTourWishListDataDto.builder()
                                    .wishListId(rs.getLong("wishListId"))
                                    .tourId(rs.getLong("tourId"))
                                    .userId(rs.getLong("userId"))
                                    .status(rs.getString("status"))
                                    .createdAt(
                                            rs.getTimestamp("createdAt") != null
                                                    ? rs.getTimestamp("createdAt").toLocalDateTime()
                                                    : null
                                    )
                                    .updatedAt(
                                            rs.getTimestamp("updatedAt") != null
                                                    ? rs.getTimestamp("updatedAt").toLocalDateTime()
                                                    : null
                                    )
                                    .build();
                        }
                        return null;
                    }
            );
        } catch (Exception e) {
            LOGGER.error("Error fetching existing wishlist data", e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public Long addPackageWishList(PackageWishListInsertRequest packageWishListInsertRequest, Long userId) {
        String INSERT_PACKAGE_WISH_DATA = WishItemsQueries.INSERT_PACKAGE_WISH_DATA;
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            int rowsAffected = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT_PACKAGE_WISH_DATA, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, userId);
                ps.setLong(2, packageWishListInsertRequest.getPackageId());
                return ps;
            }, keyHolder);

            if (rowsAffected == 0) {
                throw new InsertFailedErrorExceptionHandler("No rows affected when inserting package wish data");
            }
            Number generatedId = keyHolder.getKey();
            if (generatedId == null) {
                throw new InsertFailedErrorExceptionHandler("Failed to retrieve inserted package wish ID");
            }
            Long packageWishId = generatedId.longValue();
            LOGGER.info("Inserted package wish ID: {}", packageWishId);
            return packageWishId;
        } catch (InsertFailedErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public void addPackageWishListHistory(PackageWishListInsertRequest packageWishListInsertRequest,
                                          Long userId,
                                          Long wishListId,
                                          String status) {
        String INSERT_PACKAGE_WISHLIST_HISTORY = WishItemsQueries.INSERT_PACKAGE_WISHLIST_HISTORY;
        try {
            int rowsAffected = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT_PACKAGE_WISHLIST_HISTORY);
                ps.setLong(1, userId);
                ps.setLong(2, packageWishListInsertRequest.getPackageId());
                ps.setLong(3, wishListId);
                ps.setString(4, status);
                return ps;
            });
            if (rowsAffected == 0) {
                throw new InsertFailedErrorExceptionHandler(
                        "No rows affected when inserting package wishlist history"
                );
            }
            LOGGER.info("Inserted package wishlist history for wishlist ID: {}", wishListId);
        } catch (InsertFailedErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error inserting package wishlist history", e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }


    @Override
    public void updatePackageWishList(PackageWishListInsertRequest packageWishListInsertRequest,
                                      Long userId,
                                      ExistPackageWishListDataDto existPackageWishListDataDto) {
        try {
            if (existPackageWishListDataDto == null) {
                LOGGER.warn("Wishlist data not found");
                return;
            }
            String currentStatus = existPackageWishListDataDto.getStatus();
            Long wishListId = existPackageWishListDataDto.getWishListId();
            String newStatus;

            if ("ACTIVE".equalsIgnoreCase(currentStatus)) {
                newStatus = "INACTIVE";
            } else if ("INACTIVE".equalsIgnoreCase(currentStatus)) {
                newStatus = "ACTIVE";
            } else {
                LOGGER.warn("Status is neither ACTIVE nor INACTIVE. No update performed.");
                return;
            }

            int rowsAffected = jdbcTemplate.update(
                    WishItemsQueries.UPDATE_PACKAGE_WISHLIST_STATUS,
                    newStatus,
                    wishListId,
                    userId
            );
            if (rowsAffected == 0) {
                throw new UpdateFailedErrorExceptionHandler("Failed to update package wishlist");
            }
            LOGGER.info("Wishlist status toggled successfully. Wishlist ID: {}, New Status: {}", wishListId, newStatus);
        } catch (UpdateFailedErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error updating wishlist status", e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }


    @Override
    public ExistPackageWishListDataDto getExistingPackageWishListData(
            Long userId,
            PackageWishListInsertRequest packageWishListInsertRequest) {
        try {
            return jdbcTemplate.query(
                    WishItemsQueries.GET_EXISTING_PACKAGE_WISHLIST_DATA,
                    new Object[]{
                            userId,
                            packageWishListInsertRequest.getPackageId()
                    },
                    rs -> {
                        if (rs.next()) {
                            return ExistPackageWishListDataDto.builder()
                                    .wishListId(rs.getLong("wishListId"))
                                    .packageId(rs.getLong("packageId"))
                                    .userId(rs.getLong("userId"))
                                    .status(rs.getString("status"))
                                    .createdAt(
                                            rs.getTimestamp("createdAt") != null
                                                    ? rs.getTimestamp("createdAt").toLocalDateTime()
                                                    : null
                                    )
                                    .updatedAt(
                                            rs.getTimestamp("updatedAt") != null
                                                    ? rs.getTimestamp("updatedAt").toLocalDateTime()
                                                    : null
                                    )
                                    .build();
                        }
                        return null;
                    }
            );
        } catch (Exception e) {
            LOGGER.error("Error fetching existing wishlist data", e);
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }


}
