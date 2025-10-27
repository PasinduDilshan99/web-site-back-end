package com.felicita.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.*;
import com.felicita.model.response.AccommodationResponse;
import com.felicita.queries.AccommodationQueries;
import com.felicita.repository.AccommodationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public class AccommodationRepositoryImpl implements AccommodationRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccommodationRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccommodationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<AccommodationResponse> getAllAccommodations() {
        String GET_ALL_ACCOMMODATION = AccommodationQueries.GET_ALL_ACCOMMODATION_DATA;
        try {
            LOGGER.info("Executing query to fetch all accommodations...");

            List<AccommodationResponse> results = jdbcTemplate.query(GET_ALL_ACCOMMODATION, (rs, rowNum) -> {
                AccommodationResponse accommodation = new AccommodationResponse();
                accommodation.setAccommodationId(rs.getInt("ACCOMMADATION_ID"));
                accommodation.setAccommodationTitle(rs.getString("ACCOMMADATION_TITLE"));
                accommodation.setAccommodationSubTitle(rs.getString("ACCOMMADATION_SUB_TITLE"));
                accommodation.setAccommodationDescription(rs.getString("ACCOMMADATION_DESCRIPTION"));
                accommodation.setAccommodationIconUrl(rs.getString("ACCOMMADATION_ICON_URL"));
                accommodation.setAccommodationImageUrl(rs.getString("ACCOMMADATION_IMAGE_URL"));
                accommodation.setAccommodationColor(rs.getString("ACCOMMADATION_COLOR"));
                accommodation.setAccommodationHoverColor(rs.getString("ACCOMMADATION_HOVER_COLOR"));
                accommodation.setAccommodationLinkUrl(rs.getString("ACCOMMADATION_LINK_URL"));
                accommodation.setAccommodationStatus(rs.getString("ACCOMMADATION_STATUS"));
                accommodation.setAccommodationStatusStatus(rs.getString("ACCOMMADATION_STATUS_STATUS"));
                accommodation.setAccommodationCreatedAt(rs.getTimestamp("ACCOMMADATION_CREATED_AT") != null
                        ? rs.getTimestamp("ACCOMMADATION_CREATED_AT").toLocalDateTime()
                        : null);
                accommodation.setAccommodationCreatedBy(rs.getInt("ACCOMMADATION_CREATED_BY"));
                accommodation.setAccommodationUpdatedAt(rs.getTimestamp("ACCOMMADATION_UPDATED_AT") != null
                        ? rs.getTimestamp("ACCOMMADATION_UPDATED_AT").toLocalDateTime()
                        : null);
                accommodation.setAccommodationUpdatedBy(rs.getInt("ACCOMMADATION_UPDATED_BY"));
                accommodation.setAccommodationTerminatedAt(rs.getTimestamp("ACCOMMADATION_TERMINATED_AT") != null
                        ? rs.getTimestamp("ACCOMMADATION_TERMINATED_AT").toLocalDateTime()
                        : null);
                accommodation.setAccommodationTerminatedBy(rs.getInt("ACCOMMADATION_TERMINATED_BY"));

                return accommodation;
            });

            LOGGER.info("Successfully fetched {} accommodations.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching accommodations: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch accommodations from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching accommodations: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching accommodations");
        }
    }

    @Override
    public List<HotelDetailsSectionResponseDto> getHotelsDetailsForSection() {
        String GET_HOTELS_DETAILS_FOR_SECTION = AccommodationQueries.GET_HOTELS_DETAILS_FOR_SECTION;
        try {
            LOGGER.info("Executing query to fetch all accommodations...");

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

            List<HotelDetailsSectionResponseDto> results = jdbcTemplate.query(
                    GET_HOTELS_DETAILS_FOR_SECTION,
                    (rs, rowNum) -> {
                        HotelDetailsSectionResponseDto dto = HotelDetailsSectionResponseDto.builder()
                                .hotelId(rs.getLong("hotel_id"))
                                .hotelName(rs.getString("hotel_name"))
                                .hotelDescription(rs.getString("hotel_description"))
                                .address(rs.getString("address"))
                                .contactNumber(rs.getString("contact_number"))
                                .email(rs.getString("email"))
                                .websiteUrl(rs.getString("website_url"))
                                .checkInTime(rs.getObject("check_in_time", LocalTime.class))
                                .checkOutTime(rs.getObject("check_out_time", LocalTime.class))
                                .starRating(rs.getInt("star_rating"))
                                .currencyCode(rs.getString("currency_code"))
                                .cancellationPolicy(rs.getString("cancellation_policy"))
                                .totalRooms(rs.getInt("total_rooms"))
                                .parkingFacility(rs.getBoolean("parking_facility"))
                                .wifiAvailable(rs.getBoolean("wifi_available"))
                                .petFriendly(rs.getBoolean("pet_friendly"))
                                .hotelType(rs.getString("hotel_type"))
                                .latitude(rs.getBigDecimal("latitude"))
                                .longitude(rs.getBigDecimal("longitude"))
                                .googlePlaceId(rs.getString("google_place_id"))
                                .build();

                        try {
                            String hotelImagesJson = rs.getString("hotel_images");
                            if (hotelImagesJson != null) {
                                dto.setHotelImages(objectMapper.readValue(
                                        hotelImagesJson,
                                        new TypeReference<List<HotelDetailsSectionResponseDto.HotelImage>>() {}
                                ));
                            }

                            String mealsJson = rs.getString("meals");
                            if (mealsJson != null) {
                                dto.setMeals(objectMapper.readValue(
                                        mealsJson,
                                        new TypeReference<List<HotelDetailsSectionResponseDto.Meal>>() {}
                                ));
                            }

                            String roomsJson = rs.getString("rooms");
                            if (roomsJson != null) {
                                dto.setRooms(objectMapper.readValue(
                                        roomsJson,
                                        new TypeReference<List<HotelDetailsSectionResponseDto.Room>>() {}
                                ));
                            }

                            String roomAvailabilityJson = rs.getString("room_availability");
                            if (roomAvailabilityJson != null) {
                                dto.setRoomAvailability(objectMapper.readValue(
                                        roomAvailabilityJson,
                                        new TypeReference<List<HotelDetailsSectionResponseDto.RoomAvailability>>() {}
                                ));
                            }

                            String reviewsJson = rs.getString("reviews");
                            if (reviewsJson != null) {
                                dto.setReviews(objectMapper.readValue(
                                        reviewsJson,
                                        new TypeReference<HotelDetailsSectionResponseDto.ReviewSummary>() {}
                                ));
                            }

                        } catch (JsonProcessingException e) {
                            LOGGER.warn("Error parsing JSON for hotel {}: {}", dto.getHotelId(), e.getMessage());
                        }

                        return dto;
                    });

            LOGGER.info("Successfully fetched {} accommodations.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching accommodations: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch accommodations from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching accommodations: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching accommodations");
        }
    }

    @Override
    public List<ResortDetailsSectionResponseDto> getResortsDetailsForSection() {
        String GET_RESORTS_DETAILS_FOR_SECTION = AccommodationQueries.GET_RESORTS_DETAILS_FOR_SECTION;
        try {
            LOGGER.info("Executing query to fetch all resorts...");

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

            List<ResortDetailsSectionResponseDto> results = jdbcTemplate.query(
                    GET_RESORTS_DETAILS_FOR_SECTION,
                    (rs, rowNum) -> {
                        ResortDetailsSectionResponseDto dto = ResortDetailsSectionResponseDto.builder()
                                .resortId(rs.getLong("resort_id"))
                                .resortName(rs.getString("resort_name"))
                                .resortDescription(rs.getString("resort_description"))
                                .address(rs.getString("address"))
                                .contactNumber(rs.getString("contact_number"))
                                .email(rs.getString("email"))
                                .websiteUrl(rs.getString("website_url"))
                                .checkInTime(rs.getObject("check_in_time", LocalTime.class))
                                .checkOutTime(rs.getObject("check_out_time", LocalTime.class))
                                .starRating(rs.getInt("star_rating"))
                                .currencyCode(rs.getString("currency_code"))
                                .cancellationPolicy(rs.getString("cancellation_policy"))
                                .totalRooms(rs.getInt("total_rooms"))
                                .parkingFacility(rs.getBoolean("parking_facility"))
                                .wifiAvailable(rs.getBoolean("wifi_available"))
                                .petFriendly(rs.getBoolean("pet_friendly"))
                                .resortType(rs.getString("resort_type"))
                                .latitude(rs.getBigDecimal("latitude"))
                                .longitude(rs.getBigDecimal("longitude"))
                                .googlePlaceId(rs.getString("google_place_id"))
                                .build();

                        try {
                            String resortImagesJson = rs.getString("resort_images");
                            if (resortImagesJson != null) {
                                dto.setResortImages(objectMapper.readValue(
                                        resortImagesJson,
                                        new TypeReference<List<ResortDetailsSectionResponseDto.ResortImage>>() {}
                                ));
                            }

                            String diningOptionsJson = rs.getString("dining_options");
                            if (diningOptionsJson != null) {
                                dto.setDiningOptions(objectMapper.readValue(
                                        diningOptionsJson,
                                        new TypeReference<List<ResortDetailsSectionResponseDto.DiningOption>>() {}
                                ));
                            }

                            String accommodationsJson = rs.getString("accommodations");
                            if (accommodationsJson != null) {
                                dto.setAccommodations(objectMapper.readValue(
                                        accommodationsJson,
                                        new TypeReference<List<ResortDetailsSectionResponseDto.Accommodation>>() {}
                                ));
                            }

                            String availabilityJson = rs.getString("availability");
                            if (availabilityJson != null) {
                                dto.setAvailability(objectMapper.readValue(
                                        availabilityJson,
                                        new TypeReference<List<ResortDetailsSectionResponseDto.Availability>>() {}
                                ));
                            }

                            String resortFacilitiesJson = rs.getString("resort_facilities");
                            if (resortFacilitiesJson != null) {
                                dto.setResortFacilities(objectMapper.readValue(
                                        resortFacilitiesJson,
                                        new TypeReference<List<ResortDetailsSectionResponseDto.ResortFacility>>() {}
                                ));
                            }

                            String amenitiesJson = rs.getString("amenities");
                            if (amenitiesJson != null) {
                                dto.setAmenities(objectMapper.readValue(
                                        amenitiesJson,
                                        new TypeReference<List<ResortDetailsSectionResponseDto.Amenity>>() {}
                                ));
                            }

                            String guestReviewsJson = rs.getString("guest_reviews");
                            if (guestReviewsJson != null) {
                                dto.setGuestReviews(objectMapper.readValue(
                                        guestReviewsJson,
                                        new TypeReference<ResortDetailsSectionResponseDto.GuestReviews>() {}
                                ));
                            }

                        } catch (JsonProcessingException e) {
                            LOGGER.warn("Error parsing JSON for resort {}: {}", dto.getResortId(), e.getMessage());
                        }

                        return dto;
                    });

            LOGGER.info("Successfully fetched {} resorts.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching resorts: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch resorts from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching resorts: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching resorts");
        }
    }

    @Override
    public List<VillaDetailsSectionResponseDto> getVillasDetailsForSection() {
        String GET_VILLAS_DETAILS_FOR_SECTION = AccommodationQueries.GET_VILLAS_DETAILS_FOR_SECTION;
        try {
            LOGGER.info("Executing query to fetch all villas...");

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

            List<VillaDetailsSectionResponseDto> results = jdbcTemplate.query(
                    GET_VILLAS_DETAILS_FOR_SECTION,
                    (rs, rowNum) -> {
                        VillaDetailsSectionResponseDto dto = VillaDetailsSectionResponseDto.builder()
                                .villaId(rs.getLong("villa_id"))
                                .villaName(rs.getString("villa_name"))
                                .villaDescription(rs.getString("villa_description"))
                                .address(rs.getString("address"))
                                .contactNumber(rs.getString("contact_number"))
                                .email(rs.getString("email"))
                                .websiteUrl(rs.getString("website_url"))
                                .checkInTime(rs.getObject("check_in_time", LocalTime.class))
                                .checkOutTime(rs.getObject("check_out_time", LocalTime.class))
                                .starRating(rs.getInt("star_rating"))
                                .currencyCode(rs.getString("currency_code"))
                                .cancellationPolicy(rs.getString("cancellation_policy"))
                                .totalRooms(rs.getInt("total_rooms"))
                                .parkingFacility(rs.getBoolean("parking_facility"))
                                .wifiAvailable(rs.getBoolean("wifi_available"))
                                .petFriendly(rs.getBoolean("pet_friendly"))
                                .villaType(rs.getString("property_type"))
                                .latitude(rs.getBigDecimal("latitude"))
                                .longitude(rs.getBigDecimal("longitude"))
                                .googlePlaceId(rs.getString("google_place_id"))
                                .build();

                        try {
                            String villaImagesJson = rs.getString("villa_images");
                            if (villaImagesJson != null) {
                                dto.setVillaImages(objectMapper.readValue(
                                        villaImagesJson,
                                        new TypeReference<List<VillaDetailsSectionResponseDto.HotelImage>>() {}
                                ));
                            }

                            String villaUnitsJson = rs.getString("villa_units");
                            if (villaUnitsJson != null) {
                                dto.setRooms(objectMapper.readValue(
                                        villaUnitsJson,
                                        new TypeReference<List<VillaDetailsSectionResponseDto.Room>>() {}
                                ));
                            }

                            String availabilityJson = rs.getString("availability");
                            if (availabilityJson != null) {
                                dto.setRoomAvailability(objectMapper.readValue(
                                        availabilityJson,
                                        new TypeReference<List<VillaDetailsSectionResponseDto.RoomAvailability>>() {}
                                ));
                            }

                            String guestReviewsJson = rs.getString("guest_reviews");
                            if (guestReviewsJson != null) {
                                dto.setReviews(objectMapper.readValue(
                                        guestReviewsJson,
                                        new TypeReference<VillaDetailsSectionResponseDto.ReviewSummary>() {}
                                ));
                            }

                        } catch (JsonProcessingException e) {
                            LOGGER.warn("Error parsing JSON for villa {}: {}", dto.getVillaId(), e.getMessage());
                        }

                        return dto;
                    });

            LOGGER.info("Successfully fetched {} villas.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching villas: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch villas from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching villas: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching villas");
        }
    }

    @Override
    public List<RestaurantDetailsSectionResponseDto> getRestaurantsDetailsForSection() {
        String GET_RESTAURANTS_DETAILS_FOR_SECTION = AccommodationQueries.GET_RESTAURANTS_DETAILS_FOR_SECTION;
        try {
            LOGGER.info("Executing query to fetch all restaurants...");

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

            List<RestaurantDetailsSectionResponseDto> results = jdbcTemplate.query(
                    GET_RESTAURANTS_DETAILS_FOR_SECTION,
                    (rs, rowNum) -> {
                        RestaurantDetailsSectionResponseDto dto = RestaurantDetailsSectionResponseDto.builder()
                                .restaurantId(rs.getLong("restaurant_id"))
                                .restaurantName(rs.getString("restaurant_name"))
                                .restaurantDescription(rs.getString("restaurant_description"))
                                .address(rs.getString("address"))
                                .contactNumber(rs.getString("contact_number"))
                                .email(rs.getString("email"))
                                .websiteUrl(rs.getString("website_url"))
                                .starRating(rs.getInt("star_rating"))
                                .currencyCode(rs.getString("currency_code"))
                                .resortType(rs.getString("restaurant_type"))
                                .latitude(rs.getBigDecimal("latitude"))
                                .longitude(rs.getBigDecimal("longitude"))
                                .googlePlaceId(rs.getString("google_place_id"))
                                .parkingFacility(rs.getBoolean("parking_facility"))
                                .wifiAvailable(rs.getBoolean("wifi_available"))
                                .petFriendly(rs.getBoolean("pet_friendly"))
                                .build();

                        try {
                            String restaurantImagesJson = rs.getString("restaurant_images");
                            if (restaurantImagesJson != null) {
                                dto.setRestaurantImages(objectMapper.readValue(
                                        restaurantImagesJson,
                                        new TypeReference<List<RestaurantDetailsSectionResponseDto.ResortImage>>() {}
                                ));
                            }

                            String menuItemsJson = rs.getString("menu_items");
                            if (menuItemsJson != null) {
                                dto.setDiningOptions(objectMapper.readValue(
                                        menuItemsJson,
                                        new TypeReference<List<RestaurantDetailsSectionResponseDto.DiningOption>>() {}
                                ));
                            }

                            String operatingHoursJson = rs.getString("operating_hours");
                            if (operatingHoursJson != null) {
                                // Note: You'll need to create OperatingHours class or adjust the DTO
                                // For now, we'll skip this field or you can add it to your DTO
                            }

                            String restaurantFacilitiesJson = rs.getString("restaurant_facilities");
                            if (restaurantFacilitiesJson != null) {
                                dto.setRestaurantFacilities(objectMapper.readValue(
                                        restaurantFacilitiesJson,
                                        new TypeReference<List<RestaurantDetailsSectionResponseDto.ResortFacility>>() {}
                                ));
                            }

                            String paymentMethodsJson = rs.getString("payment_methods");
                            if (paymentMethodsJson != null) {
                                // Note: You'll need to create PaymentMethod class or adjust the DTO
                                // For now, we'll skip this field or you can add it to your DTO
                            }

                            String customerReviewsJson = rs.getString("customer_reviews");
                            if (customerReviewsJson != null) {
                                dto.setGuestReviews(objectMapper.readValue(
                                        customerReviewsJson,
                                        new TypeReference<RestaurantDetailsSectionResponseDto.GuestReviews>() {}
                                ));
                            }

                        } catch (JsonProcessingException e) {
                            LOGGER.warn("Error parsing JSON for restaurant {}: {}", dto.getRestaurantId(), e.getMessage());
                        }

                        return dto;
                    });

            LOGGER.info("Successfully fetched {} restaurants.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching restaurants: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch restaurants from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching restaurants: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching restaurants");
        }
    }

    @Override
    public List<HostelDetailsSectionResponseDto> getHostelsDetailsForSection() {
        String GET_HOSTELS_DETAILS_FOR_SECTION = AccommodationQueries.GET_HOSTELS_DETAILS_FOR_SECTION;
        try {
            LOGGER.info("Executing query to fetch all hostels...");

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

            List<HostelDetailsSectionResponseDto> results = jdbcTemplate.query(
                    GET_HOSTELS_DETAILS_FOR_SECTION,
                    (rs, rowNum) -> {
                        HostelDetailsSectionResponseDto dto = HostelDetailsSectionResponseDto.builder()
                                .hostelId(rs.getLong("hostel_id"))
                                .hostelName(rs.getString("hostel_name"))
                                .hostelDescription(rs.getString("hostel_description"))
                                .address(rs.getString("address"))
                                .contactNumber(rs.getString("contact_number"))
                                .email(rs.getString("email"))
                                .websiteUrl(rs.getString("website_url"))
                                .checkInTime(rs.getObject("check_in_time", LocalTime.class))
                                .checkOutTime(rs.getObject("check_out_time", LocalTime.class))
                                .starRating(rs.getInt("star_rating"))
                                .currencyCode(rs.getString("currency_code"))
                                .cancellationPolicy(rs.getString("cancellation_policy"))
                                .totalRooms(rs.getInt("total_rooms"))
                                .parkingFacility(rs.getBoolean("parking_facility"))
                                .wifiAvailable(rs.getBoolean("wifi_available"))
                                .petFriendly(rs.getBoolean("pet_friendly"))
                                .hostelType(rs.getString("hostel_type"))
                                .latitude(rs.getBigDecimal("latitude"))
                                .longitude(rs.getBigDecimal("longitude"))
                                .googlePlaceId(rs.getString("google_place_id"))
                                .build();

                        try {
                            String hostelImagesJson = rs.getString("hostel_images");
                            if (hostelImagesJson != null) {
                                dto.setHostelImages(objectMapper.readValue(
                                        hostelImagesJson,
                                        new TypeReference<List<HostelDetailsSectionResponseDto.HotelImage>>() {}
                                ));
                            }

                            String roomOptionsJson = rs.getString("room_options");
                            if (roomOptionsJson != null) {
                                dto.setRooms(objectMapper.readValue(
                                        roomOptionsJson,
                                        new TypeReference<List<HostelDetailsSectionResponseDto.Room>>() {}
                                ));
                            }

                            String bedAvailabilityJson = rs.getString("bed_availability");
                            if (bedAvailabilityJson != null) {
                                dto.setRoomAvailability(objectMapper.readValue(
                                        bedAvailabilityJson,
                                        new TypeReference<List<HostelDetailsSectionResponseDto.RoomAvailability>>() {}
                                ));
                            }

                            String foodServicesJson = rs.getString("food_services");
                            if (foodServicesJson != null) {
                                dto.setMeals(objectMapper.readValue(
                                        foodServicesJson,
                                        new TypeReference<List<HostelDetailsSectionResponseDto.Meal>>() {}
                                ));
                            }

                            String travelerReviewsJson = rs.getString("traveler_reviews");
                            if (travelerReviewsJson != null) {
                                dto.setReviews(objectMapper.readValue(
                                        travelerReviewsJson,
                                        new TypeReference<HostelDetailsSectionResponseDto.ReviewSummary>() {}
                                ));
                            }

                        } catch (JsonProcessingException e) {
                            LOGGER.warn("Error parsing JSON for hostel {}: {}", dto.getHostelId(), e.getMessage());
                        }

                        return dto;
                    });

            LOGGER.info("Successfully fetched {} hostels.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching hostels: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch hostels from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching hostels: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching hostels");
        }
    }

}
