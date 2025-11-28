package com.felicita.service;

import com.felicita.model.request.UpdateUserNotificationPermissionRequest;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.UpdateResponse;
import com.felicita.model.response.UserNotificationPermissionResponse;

public interface UserNotificationPermissionService {
    CommonResponse<UserNotificationPermissionResponse> getUserNotificationPermissionById();

    CommonResponse<UpdateResponse> updateUserNotificationPermissionDetails(UpdateUserNotificationPermissionRequest updateUserNotificationPermissionRequest);
}
