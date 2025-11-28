CREATE TABLE user_notification_permission (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL UNIQUE,
    new_tours BOOLEAN DEFAULT FALSE,
    new_tours_updated_at DATETIME DEFAULT NULL,
    new_packages BOOLEAN DEFAULT FALSE,
    new_packages_updated_at DATETIME DEFAULT NULL,
    new_destinations BOOLEAN DEFAULT FALSE,
    new_destinations_updated_at DATETIME DEFAULT NULL,
    new_activities BOOLEAN DEFAULT FALSE,
    new_activities_updated_at DATETIME DEFAULT NULL,
    discounts BOOLEAN DEFAULT FALSE,
    discounts_updated_at DATETIME DEFAULT NULL,
    free_coupons BOOLEAN DEFAULT FALSE,
    free_coupons_updated_at DATETIME DEFAULT NULL,
    your_tour_details BOOLEAN DEFAULT FALSE,
    your_tour_details_updated_at DATETIME DEFAULT NULL,
    tour_reminders BOOLEAN DEFAULT FALSE,
    tour_reminders_updated_at DATETIME DEFAULT NULL,
    tour_suggestions BOOLEAN DEFAULT FALSE,
    tour_suggestions_updated_at DATETIME DEFAULT NULL,
    special_notices BOOLEAN DEFAULT FALSE,
    special_notices_updated_at DATETIME DEFAULT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);


INSERT INTO user_notification_permission (
    user_id,
    new_tours_update,
    new_tours_update_at,
    new_packages_update,
    new_packages_update_at,
    new_destinations_update,
    new_destinations_update_at,
    new_activities_update,
    new_activities_update_at,
    discounts,
    discounts_updated_at,
    free_coupons,
    free_coupons_updated_at,
    your_tour_details_updates,
    your_tour_details_updates_at,
    tour_reminders,
    tour_reminders_updated_at,
    tour_suggestions,
    tour_suggestions_updated_at,
    special_notices,
    special_notices_updated_at
)
VALUES (
    1,
    TRUE,
    NOW(),
    TRUE,
    NOW(),
    TRUE,
    NOW(),
    TRUE,
    NOW(),
    TRUE,
    NOW(),
    TRUE,
    NOW(),
    TRUE,
    NOW(),
    TRUE,
    NOW(),
    TRUE,
    NOW(),
    TRUE,
    NOW()
);


select * from user_notification_permission;


SELECT 
    id,
    user_id,
    new_tours_update,
    new_tours_update_at,
    new_packages_update,
    new_packages_update_at,
    new_destinations_update,
    new_destinations_update_at,
    new_activities_update,
    new_activities_update_at,
    discounts,
    discounts_updated_at,
    free_coupons,
    free_coupons_updated_at,
    your_tour_details_updates,
    your_tour_details_updates_at,
    tour_reminders,
    tour_reminders_updated_at,
    tour_suggestions,
    tour_suggestions_updated_at,
    special_notices,
    special_notices_updated_at,
    created_at,
    updated_at
FROM user_notification_permission
WHERE user_id = 1;




