package com.felicita.service;

import com.felicita.model.request.ActivityWishListInsertRequest;
import com.felicita.model.request.DestinationWishListInsertRequest;
import com.felicita.model.request.PackageWishListInsertRequest;
import com.felicita.model.request.TourWishListInsertRequest;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.InsertResponse;
import com.felicita.model.response.WishListResponse;

public interface WishListService {
    CommonResponse<WishListResponse> getWishListDetails();

    CommonResponse<InsertResponse> addActivityWishList(ActivityWishListInsertRequest activityWishListInsertRequest);

    CommonResponse<InsertResponse> addDestinationWishList(DestinationWishListInsertRequest destinationWishListInsertRequest);

    CommonResponse<InsertResponse> addPackageWishList(PackageWishListInsertRequest packageWishListInsertRequest);

    CommonResponse<InsertResponse> addTourWishList(TourWishListInsertRequest tourWishListInsertRequest);
}
