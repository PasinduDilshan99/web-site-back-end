package com.felicita.repository;

import com.felicita.model.dto.ActivityWishResponseDto;
import com.felicita.model.dto.DestinationWishResponseDto;
import com.felicita.model.dto.PackageWishResponseDto;
import com.felicita.model.dto.TourWishResponsesDto;
import com.felicita.model.response.WishlistItemResponse;

import java.util.List;

public interface WishListRepository {
    List<WishlistItemResponse> getWishListItems(Long userId);

    List<PackageWishResponseDto> getPackageWishList(List<Long> packagesIdList);

    List<TourWishResponsesDto> getTourWishList(List<Long> toursIdList);

    List<DestinationWishResponseDto> getDestinationWishList(List<Long> destinationsIdList);

    List<ActivityWishResponseDto> getActivitiesWishList(List<Long> activitiesIdList);
}
