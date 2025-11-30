package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.*;
import com.felicita.model.response.AccountSecurityDetailsResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.WishListResponse;
import com.felicita.model.response.WishlistItemResponse;
import com.felicita.repository.WishListRepository;
import com.felicita.service.CommonService;
import com.felicita.service.WishListService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class WishListServiceImpl implements WishListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WishListServiceImpl.class);

    private final WishListRepository wishListRepository;
    private final CommonService commonService;

    @Autowired
    public WishListServiceImpl(WishListRepository wishListRepository,
                               CommonService commonService) {
        this.wishListRepository = wishListRepository;
        this.commonService = commonService;
    }

    @Override
    public CommonResponse<WishListResponse> getWishListDetails() {
        LOGGER.info("Start fetching wish list details from repository");
        try {
            Long userId = commonService.getUserIdBySecurityContext();
            List<WishlistItemResponse> wishlistItemResponse = wishListRepository.getWishListItems(userId);
            List<Long> packagesIdList = wishlistItemResponse.stream()
                    .filter(item -> item.getWishlistType().equalsIgnoreCase("package"))
                    .map(WishlistItemResponse::getItemId)
                    .toList();
            LOGGER.info(packagesIdList.toString());
            List<PackageWishResponseDto> packageWishResponseDtos = wishListRepository.getPackageWishList(packagesIdList);
            List<Long> toursIdList = wishlistItemResponse.stream()
                    .filter(item -> item.getWishlistType().equalsIgnoreCase("tour"))
                    .map(WishlistItemResponse::getItemId)
                    .toList();
            List<TourWishResponsesDto> tourWishResponsesDtos = wishListRepository.getTourWishList(toursIdList);
            List<Long> destinationsIdList = wishlistItemResponse.stream()
                    .filter(item -> item.getWishlistType().equalsIgnoreCase("destination"))
                    .map(WishlistItemResponse::getItemId)
                    .toList();
            List<DestinationWishResponseDto> destinationWishResponseDtos = wishListRepository.getDestinationWishList(destinationsIdList);
            List<Long> activitiesIdList = wishlistItemResponse.stream()
                    .filter(item -> item.getWishlistType().equalsIgnoreCase("activity"))
                    .map(WishlistItemResponse::getItemId)
                    .toList();
            List<ActivityWishResponseDto> activityWishResponseDtos = wishListRepository.getActivitiesWishList(activitiesIdList);
            WishListResponse wishListResponse = WishListResponse.builder()
                    .packageWishResponseDtos(packageWishResponseDtos)
                    .tourWishResponsesDtos(tourWishResponsesDtos)
                    .destinationWishResponseDtos(destinationWishResponseDtos)
                    .activityWishResponseDtos(activityWishResponseDtos)
                    .build();
            LOGGER.info("Fetched user account security details successfully");
            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            wishListResponse,
                            Instant.now()
                    )
            );
        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching user account security details: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching user account security details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch user account security details from database");
        } finally {
            LOGGER.info("End fetching user account security details from repository");
        }
    }
}
