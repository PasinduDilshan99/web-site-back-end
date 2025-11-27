package com.felicita.service;

import com.felicita.model.request.UserProfileDetailsRequest;
import com.felicita.model.response.*;

import java.util.List;

public interface UserProfileService {
    CommonResponse<UserProfileDetailsResponse> getUserProfileDetails( );

    CommonResponse<List<UserProfileSidebarResponse>> getUserProfileSideBar();

    CommonResponse< List<UserProfileReviewResponse>> getUserProfileReviews();

    CommonResponse<List<UserProfilePackageReviewResponse>> getUserProfilePackageReviews();

    CommonResponse<List<UserProfileDestinationReviewResponse>> getUserProfileDestiantionsReviews();

    CommonResponse<List<UserProfileActivitesReviewsResponse>> getUserProfileActivitesReviews();

    CommonResponse<List<UserProfileTourReviewResponse>> getUserProfileTourReviews();

    CommonResponse<UserProfileWalletResponse> getUserProfileWalletDetails();
}
