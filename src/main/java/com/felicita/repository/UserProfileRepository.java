package com.felicita.repository;

import com.felicita.model.request.UserProfileDetailsRequest;
import com.felicita.model.request.UserUpdateRequest;
import com.felicita.model.response.*;

import java.util.List;

public interface UserProfileRepository {
    UserProfileDetailsResponse getUserProfileDetails(UserProfileDetailsRequest userProfileDetailsRequest);

    List<UserProfileSidebarResponse> getUserProfileSideBar();

    List<UserProfileReviewResponse> getUserProfileReviews(Long id);

    List<UserProfilePackageReviewResponse> getUserProfilePackageReviews(Long userId);

    List<UserProfileDestinationReviewResponse> getUserProfileDestiantionsReviews(Long userId);

    List<UserProfileActivitesReviewsResponse> getUserProfileActivitesReviews(Long userId);

    List<UserProfileTourReviewResponse> getUserProfileTourReviews(Long userId);

    UserProfileWalletResponse getUserProfileWalletDetails(Long userId);

    void updateUserProfileDetails(UserUpdateRequest userUpdateRequest, Long userId);
}
