package com.felicita.repository;

import com.felicita.model.dto.*;
import com.felicita.model.request.ActivityWishListInsertRequest;
import com.felicita.model.request.DestinationWishListInsertRequest;
import com.felicita.model.request.PackageWishListInsertRequest;
import com.felicita.model.request.TourWishListInsertRequest;
import com.felicita.model.response.WishlistItemResponse;

import java.util.List;

public interface WishListRepository {
    List<WishlistItemResponse> getWishListItems(Long userId);

    List<PackageWishResponseDto> getPackageWishList(List<Long> packagesIdList);

    List<TourWishResponsesDto> getTourWishList(List<Long> toursIdList);

    List<DestinationWishResponseDto> getDestinationWishList(List<Long> destinationsIdList);

    List<ActivityWishResponseDto> getActivitiesWishList(List<Long> activitiesIdList);

    Long addActivityWishList(ActivityWishListInsertRequest activityWishListInsertRequest, Long userId);

    void addActivityWishListHistory(ActivityWishListInsertRequest activityWishListInsertRequest, Long userId, Long wishListId, String status);

    void updateActivityWishList(ActivityWishListInsertRequest activityWishListInsertRequest, Long userId, ExistActivityWishListDataDto existActivityWishListDataDto);

    ExistActivityWishListDataDto getExistingWishListData(Long userId, ActivityWishListInsertRequest activityWishListInsertRequest);

    ExistDestinationWishListDataDto getExistingDestinationWishListData(Long userId, DestinationWishListInsertRequest destinationWishListInsertRequest);

    Long addDestinationWishList(DestinationWishListInsertRequest destinationWishListInsertRequest, Long userId);

    void addDestinationWishListHistory(DestinationWishListInsertRequest destinationWishListInsertRequest, Long userId, Long wishListId, String active);

    void updateDestinationWishList(DestinationWishListInsertRequest destinationWishListInsertRequest, Long userId, ExistDestinationWishListDataDto exist);

    ExistPackageWishListDataDto getExistingPackageWishListData(Long userId, PackageWishListInsertRequest packageWishListInsertRequest);

    Long addPackageWishList(PackageWishListInsertRequest packageWishListInsertRequest, Long userId);

    void addPackageWishListHistory(PackageWishListInsertRequest packageWishListInsertRequest, Long userId, Long wishListId, String active);

    void updatePackageWishList(PackageWishListInsertRequest packageWishListInsertRequest, Long userId, ExistPackageWishListDataDto exist);

    ExistTourWishListDataDto getExistingTourWishListData(Long userId, TourWishListInsertRequest tourWishListInsertRequest);

    Long addTourWishList(TourWishListInsertRequest tourWishListInsertRequest, Long userId);

    void addTourWishListHistory(TourWishListInsertRequest tourWishListInsertRequest, Long userId, Long wishListId, String active);

    void updateTourWishList(TourWishListInsertRequest tourWishListInsertRequest, Long userId, ExistTourWishListDataDto exist);

    List<Long> getAllActivityWishListByUserId(Long userId);

    List<Long> getAllDestinationWishListByUserId(Long userId);

    List<Long> getAllTourWishListByUserId(Long userId);

    List<Long> getAllPackageWishListByUserId(Long userId);
}
