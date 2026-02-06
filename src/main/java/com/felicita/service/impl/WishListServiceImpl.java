package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InsertFailedErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.exception.ValidationFailedErrorExceptionHandler;
import com.felicita.model.dto.*;
import com.felicita.model.request.ActivityWishListInsertRequest;
import com.felicita.model.request.DestinationWishListInsertRequest;
import com.felicita.model.request.PackageWishListInsertRequest;
import com.felicita.model.request.TourWishListInsertRequest;
import com.felicita.model.response.*;
import com.felicita.repository.WishListRepository;
import com.felicita.service.CommonService;
import com.felicita.service.WishListService;
import com.felicita.util.CommonResponseMessages;
import com.felicita.validation.WishListValidationService;
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
    private final WishListValidationService wishListValidationService;

    @Autowired
    public WishListServiceImpl(WishListRepository wishListRepository, CommonService commonService, WishListValidationService wishListValidationService) {
        this.wishListRepository = wishListRepository;
        this.commonService = commonService;
        this.wishListValidationService = wishListValidationService;
    }

    @Override
    public CommonResponse<WishListResponse> getWishListDetails() {
        LOGGER.info("Start fetching wish list details from repository");
        try {
            Long userId = commonService.getUserIdBySecurityContext();
            List<WishlistItemResponse> wishlistItemResponse = wishListRepository.getWishListItems(userId);
            List<WishlistItemResponse> list = wishlistItemResponse.stream().filter(item -> item.getStatusId().equals(1)).toList();

            List<Long> packagesIdList = list.stream()
                    .filter(item -> item.getWishlistType().equalsIgnoreCase("package"))
                    .map(WishlistItemResponse::getItemId)
                    .toList();
            List<PackageWishResponseDto> packageWishResponseDtos = wishListRepository.getPackageWishList(packagesIdList);

            List<Long> toursIdList = list.stream()
                    .filter(item -> item.getWishlistType().equalsIgnoreCase("tour"))
                    .map(WishlistItemResponse::getItemId)
                    .toList();
            List<TourWishResponsesDto> tourWishResponsesDtos = wishListRepository.getTourWishList(toursIdList);

            List<Long> destinationsIdList = list.stream()
                    .filter(item -> item.getWishlistType().equalsIgnoreCase("destination"))
                    .map(WishlistItemResponse::getItemId)
                    .toList();
            List<DestinationWishResponseDto> destinationWishResponseDtos = wishListRepository.getDestinationWishList(destinationsIdList);

            List<Long> activitiesIdList = list.stream()
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

    @Override
    public CommonResponse<InsertResponse> addActivityWishList(ActivityWishListInsertRequest activityWishListInsertRequest) {
        try {
            wishListValidationService.validateActivityWishListInsertRequest(activityWishListInsertRequest);
            Long userId = commonService.getUserIdBySecurityContext();
            ExistActivityWishListDataDto existActivityWishListDataDto = wishListRepository.getExistingWishListData(userId, activityWishListInsertRequest);
            if (existActivityWishListDataDto == null) {
                Long wishListId = wishListRepository.addActivityWishList(activityWishListInsertRequest, userId);
                wishListRepository.addActivityWishListHistory(activityWishListInsertRequest, userId, wishListId, "ACTIVE");
                return new CommonResponse<>(
                        CommonResponseMessages.SUCCESSFULLY_INSERT_CODE,
                        CommonResponseMessages.SUCCESSFULLY_INSERT_STATUS,
                        CommonResponseMessages.SUCCESSFULLY_INSERT_MESSAGE,
                        new InsertResponse("Successfully insert wish list request"),
                        Instant.now());
            } else {
                wishListRepository.updateActivityWishList(activityWishListInsertRequest, userId, existActivityWishListDataDto);
                wishListRepository.addActivityWishListHistory(activityWishListInsertRequest, userId, existActivityWishListDataDto.getWishListId(), existActivityWishListDataDto.getStatus());
                return new CommonResponse<>(
                        CommonResponseMessages.SUCCESSFULLY_UPDATE_CODE,
                        CommonResponseMessages.SUCCESSFULLY_UPDATE_STATUS,
                        CommonResponseMessages.SUCCESSFULLY_UPDATE_MESSAGE,
                        new InsertResponse("Successfully update wish list request"),
                        Instant.now());
            }

        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the insert wish list request", vfe.getValidationFailedResponses());
        } catch (InsertFailedErrorExceptionHandler ife) {
            throw new InsertFailedErrorExceptionHandler(ife.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.toString());
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public CommonResponse<InsertResponse> addDestinationWishList(
            DestinationWishListInsertRequest destinationWishListInsertRequest) {

        try {

            Long userId = commonService.getUserIdBySecurityContext();

            ExistDestinationWishListDataDto exist =
                    wishListRepository.getExistingDestinationWishListData(
                            userId,
                            destinationWishListInsertRequest);

            if (exist == null) {

                Long wishListId =
                        wishListRepository.addDestinationWishList(
                                destinationWishListInsertRequest,
                                userId);

                wishListRepository.addDestinationWishListHistory(
                        destinationWishListInsertRequest,
                        userId,
                        wishListId,
                        "ACTIVE");

                return new CommonResponse<>(
                        CommonResponseMessages.SUCCESSFULLY_INSERT_CODE,
                        CommonResponseMessages.SUCCESSFULLY_INSERT_STATUS,
                        CommonResponseMessages.SUCCESSFULLY_INSERT_MESSAGE,
                        new InsertResponse("Successfully insert destination wish list"),
                        Instant.now());

            } else {

                wishListRepository.updateDestinationWishList(
                        destinationWishListInsertRequest,
                        userId,
                        exist);

                wishListRepository.addDestinationWishListHistory(
                        destinationWishListInsertRequest,
                        userId,
                        exist.getWishListId(),
                        exist.getStatus());

                return new CommonResponse<>(
                        CommonResponseMessages.SUCCESSFULLY_UPDATE_CODE,
                        CommonResponseMessages.SUCCESSFULLY_UPDATE_STATUS,
                        CommonResponseMessages.SUCCESSFULLY_UPDATE_MESSAGE,
                        new InsertResponse("Successfully update destination wish list"),
                        Instant.now());
            }

        } catch (Exception e) {

            LOGGER.error(e.toString());

            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public CommonResponse<InsertResponse> addPackageWishList(
            PackageWishListInsertRequest packageWishListInsertRequest) {

        try {

            Long userId = commonService.getUserIdBySecurityContext();

            ExistPackageWishListDataDto exist =
                    wishListRepository.getExistingPackageWishListData(
                            userId,
                            packageWishListInsertRequest);

            if (exist == null) {

                Long wishListId =
                        wishListRepository.addPackageWishList(
                                packageWishListInsertRequest,
                                userId);

                wishListRepository.addPackageWishListHistory(
                        packageWishListInsertRequest,
                        userId,
                        wishListId,
                        "ACTIVE");

                return new CommonResponse<>(
                        CommonResponseMessages.SUCCESSFULLY_INSERT_CODE,
                        CommonResponseMessages.SUCCESSFULLY_INSERT_STATUS,
                        CommonResponseMessages.SUCCESSFULLY_INSERT_MESSAGE,
                        new InsertResponse("Successfully insert package wish list"),
                        Instant.now());

            } else {

                wishListRepository.updatePackageWishList(
                        packageWishListInsertRequest,
                        userId,
                        exist);

                wishListRepository.addPackageWishListHistory(
                        packageWishListInsertRequest,
                        userId,
                        exist.getWishListId(),
                        exist.getStatus());

                return new CommonResponse<>(
                        CommonResponseMessages.SUCCESSFULLY_UPDATE_CODE,
                        CommonResponseMessages.SUCCESSFULLY_UPDATE_STATUS,
                        CommonResponseMessages.SUCCESSFULLY_UPDATE_MESSAGE,
                        new InsertResponse("Successfully update package wish list"),
                        Instant.now());
            }

        } catch (Exception e) {

            LOGGER.error(e.toString());

            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public CommonResponse<InsertResponse> addTourWishList(
            TourWishListInsertRequest tourWishListInsertRequest) {

        try {

            Long userId = commonService.getUserIdBySecurityContext();

            ExistTourWishListDataDto exist =
                    wishListRepository.getExistingTourWishListData(
                            userId,
                            tourWishListInsertRequest);

            if (exist == null) {

                Long wishListId =
                        wishListRepository.addTourWishList(
                                tourWishListInsertRequest,
                                userId);

                wishListRepository.addTourWishListHistory(
                        tourWishListInsertRequest,
                        userId,
                        wishListId,
                        "ACTIVE");

                return new CommonResponse<>(
                        CommonResponseMessages.SUCCESSFULLY_INSERT_CODE,
                        CommonResponseMessages.SUCCESSFULLY_INSERT_STATUS,
                        CommonResponseMessages.SUCCESSFULLY_INSERT_MESSAGE,
                        new InsertResponse("Successfully insert tour wish list"),
                        Instant.now());

            } else {

                wishListRepository.updateTourWishList(
                        tourWishListInsertRequest,
                        userId,
                        exist);

                wishListRepository.addTourWishListHistory(
                        tourWishListInsertRequest,
                        userId,
                        exist.getWishListId(),
                        exist.getStatus());

                return new CommonResponse<>(
                        CommonResponseMessages.SUCCESSFULLY_UPDATE_CODE,
                        CommonResponseMessages.SUCCESSFULLY_UPDATE_STATUS,
                        CommonResponseMessages.SUCCESSFULLY_UPDATE_MESSAGE,
                        new InsertResponse("Successfully update tour wish list"),
                        Instant.now());
            }

        } catch (Exception e) {

            LOGGER.error(e.toString());

            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }


}
