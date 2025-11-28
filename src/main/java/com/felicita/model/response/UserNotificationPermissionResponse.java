package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserNotificationPermissionResponse {

    private Integer id;
    private Integer userId;

    private Boolean newToursUpdate;
    private LocalDateTime newToursUpdateAt;

    private Boolean newPackagesUpdate;
    private LocalDateTime newPackagesUpdateAt;

    private Boolean newDestinationsUpdate;
    private LocalDateTime newDestinationsUpdateAt;

    private Boolean newActivitiesUpdate;
    private LocalDateTime newActivitiesUpdateAt;

    private Boolean discounts;
    private LocalDateTime discountsUpdatedAt;

    private Boolean freeCoupons;
    private LocalDateTime freeCouponsUpdatedAt;

    private Boolean yourTourDetailsUpdates;
    private LocalDateTime yourTourDetailsUpdatesAt;

    private Boolean tourReminders;
    private LocalDateTime tourRemindersUpdatedAt;

    private Boolean tourSuggestions;
    private LocalDateTime tourSuggestionsUpdatedAt;

    private Boolean specialNotices;
    private LocalDateTime specialNoticesUpdatedAt;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
