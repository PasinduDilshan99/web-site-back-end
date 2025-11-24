package com.felicita.service;

import com.felicita.model.request.UserProfileDetailsRequest;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.UserProfileDetailsResponse;
import com.felicita.model.response.UserProfileSidebarResponse;

import java.util.List;

public interface UserProfileService {
    CommonResponse<UserProfileDetailsResponse> getUserProfileDetails(UserProfileDetailsRequest userProfileDetailsRequest);

    CommonResponse<List<UserProfileSidebarResponse>> getUserProfileSideBar();
}
