package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceProviderDetailsResponse {
    private ServiceProviderDetails serviceProviderDetails;
    private List<NearbyDestination> nearbyDestinations;
    private List<MealDetail> mealDetails;
    private List<RoomDetail> roomDetails;
    private List<PackageDetail> packageDetails;
    private List<AmenityDetail> amenities;
    private List<FacilityDetail> facilities;
    private List<SeasonalPricing> seasonalPricing;
    private List<ContactPerson> contactPersons;
    private List<BankDetail> bankDetails;
    private TaxAndCommissionDetails taxAndCommissionDetails;
    private List<BookingRestriction> bookingRestrictions;
    private Statistics statistics;
    private List<SocialMedia> socialMedia;
    private List<ReviewDetail> reviews;

    // Inner classes for each section
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ServiceProviderDetails {
        private Integer serviceProviderId;
        private Integer userId;
        private Integer serviceProviderTypeId;
        private String name;
        private String description;
        private String address;
        private String contactNumber;
        private String email;
        private String websiteUrl;
        private LocalTime checkInTime;
        private LocalTime checkOutTime;
        private Integer starRating;
        private Integer currencyId;
        private String cancellationPolicy;
        private Integer minimumAdvanceBookingHours;
        private Integer establishmentYear;
        private Integer totalRooms;
        private Integer totalEmployees;
        private String awardsCertifications;
        private String languagesSpoken;
        private Boolean parkingFacility;
        private Integer parkingCapacity;
        private Boolean wifiAvailable;
        private Boolean petFriendly;
        private String checkInInstructions;
        private String specialInstructions;
        private Integer approvalStatusId;
        private Integer statusId;
        private LocalDateTime createdAt;
        private Integer createdBy;
        private LocalDateTime updatedAt;
        private Integer updatedBy;
        private LocalDateTime terminatedAt;
        private Integer terminatedBy;

        // Additional joined fields
        private String providerTypeName;
        private String currencyCode;
        private String currencySymbol;
        private String approvalStatusName;
        private String statusName;
        private String createdByUsername;

        // Approval details
        private String approvalComment;
        private LocalDateTime approvedAt;
        private String approvedByUsername;

        // Images
        private List<Image> images;

        // Operating hours
        private List<OperatingHour> operatingHours;

        // Payment methods
        private List<PaymentMethod> paymentMethods;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Image {
        private Integer imageId;
        private String imageUrl;
        private String imageName;
        private String imageDescription;
        private String caption;
        private Integer imageStatusId;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OperatingHour {
        private Integer hoursId;
        private Integer dayOfWeek;
        private LocalTime opensAt;
        private LocalTime closesAt;
        private Boolean is24Hours;
        private Integer operatingStatusId;
        private String hoursSpecialNote;
        private String operatingStatusName;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentMethod {
        private Integer paymentMethodId;
        private String methodType;
        private String methodDetails;
        private Boolean paymentMethodAvailable;
        private Integer paymentMethodStatusId;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NearbyDestination {
        private Integer destinationId;
        private String name;
        private String description;
        private BigDecimal latitude;
        private BigDecimal longitude;
        private String location;
        private String destinationCategory;
        private String statusName;
        private LocalDateTime linkedDate;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MealDetail {
        private Integer mealId;
        private Integer serviceProviderId;
        private Integer mealTypeId;
        private String description;
        private BigDecimal localPrice;
        private BigDecimal foreignPrice;
        private Integer currencyId;
        private BigDecimal discountPercentage;
        private String discountRequirements;
        private Integer servesPeople;
        private String cuisineType;
        private String dietaryTags;
        private Integer preparationTime;
        private Boolean isChefSpecial;
        private Boolean isSpicy;
        private Integer spiceLevel;
        private String servingSize;
        private Integer calories;
        private String allergens;
        private Boolean available;
        private LocalDateTime createdAt;
        private Integer createdBy;
        private LocalDateTime updatedAt;
        private Integer updatedBy;
        private LocalDateTime terminatedAt;
        private Integer terminatedBy;

        // Additional fields
        private String mealTypeName;
        private String currencyCode;
        private String statusName;

        // Images
        private List<MealImage> images;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MealImage {
        private String imageUrl;
        private String imageName;
        private String imageDescription;
        private String imageCaption;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RoomDetail {
        private Integer roomId;
        private Integer serviceProviderId;
        private Integer roomTypeId;
        private String roomNumber;
        private String roomDescription;
        private Integer capacity;
        private BigDecimal roomSize;
        private String bedType;
        private Integer numberOfBeds;
        private Integer numberOfBathrooms;
        private Integer maxAdults;
        private Integer maxChildren;
        private Boolean smokingAllowed;
        private Boolean isAccessible;
        private BigDecimal localPricePerNight;
        private BigDecimal foreignPricePerNight;
        private Integer currencyId;
        private BigDecimal discountPercentage;
        private String discountRequirements;
        private Integer roomFloor;
        private String viewType;
        private Boolean hasBalcony;
        private Boolean hasAirConditioning;
        private Boolean hasTv;
        private Boolean hasMinibar;
        private Boolean hasSafe;
        private Boolean hasKitchenette;
        private Boolean internetAccess;
        private Integer roomQualityRating;
        private Boolean extraBedAvailable;
        private BigDecimal extraBedCharge;
        private Integer statusId;
        private LocalDateTime createdAt;
        private Integer createdBy;
        private LocalDateTime updatedAt;
        private Integer updatedBy;
        private LocalDateTime terminatedAt;
        private Integer terminatedBy;

        // Additional fields
        private String roomTypeName;
        private String currencyCode;
        private String roomStatus;

        // Features
        private List<RoomFeature> features;

        // Amenities
        private List<RoomAmenity> amenities;

        // Availability
        private List<RoomAvailability> availability;

        // Images
        private List<RoomImage> images;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RoomFeature {
        private String featureName;
        private String featureValue;
        private String featureDescription;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RoomAmenity {
        private String amenityName;
        private String amenityCategory;
        private String iconClass;
        private String amenityNotes;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RoomAvailability {
        private LocalDate availabilityDate;
        private Integer availableRooms;
        private Integer bookedRooms;
        private BigDecimal localPriceForDate;
        private BigDecimal foreignPriceForDate;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RoomImage {
        private String roomImageUrl;
        private String roomImageName;
        private String roomImageCaption;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PackageDetail {
        private Integer serviceProviderPackageId;
        private Integer serviceProviderId;
        private String packageName;
        private String packageDescription;
        private BigDecimal localPrice;
        private BigDecimal foreignPrice;
        private Integer currencyId;
        private BigDecimal discountPercentage;
        private String discountRequirements;
        private Integer durationDays;
        private LocalDate startDate;
        private LocalDate endDate;
        private Integer minPersons;
        private Integer maxPersons;
        private Boolean includesChildren;
        private Integer maxChildrenIncluded;
        private Boolean isCustomizable;
        private Integer bookingDeadlineDays;
        private String packageCode;
        private String packageCategory;
        private String seasonType;
        private Integer advanceBookingDays;
        private String cancellationPolicy;
        private String refundPolicy;
        private String termsConditions;
        private String highlights;
        private String specialNote;
        private String requirements;
        private Integer statusId;
        private LocalDateTime createdAt;
        private Integer createdBy;
        private LocalDateTime updatedAt;
        private Integer updatedBy;
        private LocalDateTime terminatedAt;
        private Integer terminatedBy;

        // Additional fields
        private String currencyCode;
        private String packageStatus;

        // Features
        private List<PackageFeature> features;

        // Inclusions
        private List<PackageInclusion> inclusions;

        // Images
        private List<PackageImage> images;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PackageFeature {
        private String featureName;
        private String featureValue;
        private String featureDescription;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PackageInclusion {
        private String inclusionName;
        private String inclusionDescription;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PackageImage {
        private String packageImageUrl;
        private String packageImageName;
        private String packageImageCaption;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AmenityDetail {
        private Integer providerAmenityId;
        private String amenityName;
        private String amenityDescription;
        private BigDecimal localAdditionalCharge;
        private BigDecimal foreignAdditionalCharge;
        private Boolean isAvailable;
        private String amenityTypeName;
        private String amenityCategory;
        private String iconClass;
        private String currencyCode;
        private String statusName;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FacilityDetail {
        private Integer facilityId;
        private String facilityName;
        private String facilityDescription;
        private Boolean isAvailable;
        private String specialNote;

        // Images
        private List<FacilityImage> images;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FacilityImage {
        private String imageUrl;
        private String imageName;
        private String imageDescription;
        private String imageCaption;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SeasonalPricing {
        private Integer seasonalPriceId;
        private String seasonName;
        private LocalDate startDate;
        private LocalDate endDate;
        private BigDecimal localMultiplier;
        private BigDecimal foreignMultiplier;
        private String description;
        private String requirements;
        private String specialNote;
        private String statusName;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContactPerson {
        private Integer contactPersonId;
        private String fullName;
        private String designation;
        private String email;
        private String phone;
        private String mobile;
        private Boolean isPrimary;
        private String statusName;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BankDetail {
        private Integer bankId;
        private String bankName;
        private String accountHolderName;
        private String accountNumber;
        private String branchName;
        private String branchCode;
        private String swiftCode;
        private String iban;
        private Boolean isPrimary;
        private String currencyCode;
        private String statusName;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TaxAndCommissionDetails {
        // Tax Configuration
        private Integer taxId;
        private String taxName;
        private BigDecimal taxPercentage;
        private String taxNumber;
        private Boolean taxActive;
        private Boolean appliesToRooms;
        private Boolean appliesToMeals;
        private Boolean appliesToPackages;
        private Boolean appliesToAmenities;
        private String taxStatus;

        // Commission Settings
        private Integer commissionId;
        private BigDecimal commissionPercentage;
        private Boolean commissionAppliesRooms;
        private Boolean commissionAppliesMeals;
        private Boolean commissionAppliesPackages;
        private BigDecimal minimumCommissionAmount;
        private String commissionStatus;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookingRestriction {
        private Integer restrictionId;
        private String restrictionType;
        private Integer minStayNights;
        private Integer maxStayNights;
        private LocalDate startDate;
        private LocalDate endDate;
        private String description;
        private String statusName;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Statistics {
        private Integer statsId;
        private Integer totalBookings;
        private BigDecimal totalRevenue;
        private BigDecimal averageRating;
        private Integer totalReviews;
        private BigDecimal occupancyRate;
        private LocalDateTime lastUpdated;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SocialMedia {
        private Integer socialId;
        private String platform;
        private String profileUrl;
        private String verificationStatusName;
        private String statusName;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewDetail {
        private Integer reviewId;
        private Integer overallRating;
        private String title;
        private String comment;
        private LocalDateTime reviewDate;
        private Boolean isApproved;
        private String username;
        private String firstName;
        private String lastName;
        private String reviewStatus;

        // Rating details
        private List<RatingCategory> ratingCategories;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RatingCategory {
        private Integer categoryRating;
        private String categoryName;
        private String categoryDescription;
    }
}
