package com.felicita.repository;

import com.felicita.model.request.UserProfileDetailsRequest;
import com.felicita.model.response.UserProfileDetailsResponse;
import com.felicita.model.response.UserProfileSidebarResponse;

import java.util.List;

public interface UserProfileRepository {
    UserProfileDetailsResponse getUserProfileDetails(UserProfileDetailsRequest userProfileDetailsRequest);

    List<UserProfileSidebarResponse> getUserProfileSideBar();
}
