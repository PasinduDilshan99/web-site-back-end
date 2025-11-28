package com.felicita.repository;

import com.felicita.model.request.UpdateUserNotificationPermissionRequest;
import com.felicita.model.response.UserNotificationPermissionResponse;

public interface UserNotificationPermissionRepository {
    UserNotificationPermissionResponse getUserNotificationPermissionById(Long userId);

    void updateUserNotificationPermissionDetails(UpdateUserNotificationPermissionRequest updateUserNotificationPermissionRequest, Long userId);
}
