package com.felicita.controller;

import com.felicita.model.request.ActivityWishListInsertRequest;
import com.felicita.model.request.DestinationWishListInsertRequest;
import com.felicita.model.request.PackageWishListInsertRequest;
import com.felicita.model.request.TourWishListInsertRequest;
import com.felicita.model.response.AccountSecurityDetailsResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.InsertResponse;
import com.felicita.model.response.WishListResponse;
import com.felicita.service.WishListService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v0/wish-list")
public class WishListController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WishListController.class);

    private final WishListService wishListService;

    @Autowired
    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping(path = "/details")
    public ResponseEntity<CommonResponse<WishListResponse>> getWishListDetails() {
        LOGGER.info("{} Start execute get user wish list details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<WishListResponse> response = wishListService.getWishListDetails();
        LOGGER.info("{} End execute get user wish list details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/add-activity-wish-list")
    public ResponseEntity<CommonResponse<InsertResponse>> addActivityWishList(@RequestBody ActivityWishListInsertRequest activityWishListInsertRequest) {
        LOGGER.info("{} Start execute add activity wish list data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<InsertResponse> response = wishListService.addActivityWishList(activityWishListInsertRequest);
        LOGGER.info("{} End execute add activity wish list data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(path = "/add-destination-wish-list")
    public ResponseEntity<CommonResponse<InsertResponse>> addDestinationWishList(
            @RequestBody DestinationWishListInsertRequest destinationWishListInsertRequest) {

        LOGGER.info("{} Start execute add destination wish list {}", Constant.DOTS, Constant.DOTS);

        CommonResponse<InsertResponse> response =
                wishListService.addDestinationWishList(destinationWishListInsertRequest);

        LOGGER.info("{} End execute add destination wish list {}", Constant.DOTS, Constant.DOTS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping(path = "/add-package-wish-list")
    public ResponseEntity<CommonResponse<InsertResponse>> addPackageWishList(
            @RequestBody PackageWishListInsertRequest packageWishListInsertRequest) {

        LOGGER.info("{} Start execute add package wish list {}", Constant.DOTS, Constant.DOTS);

        CommonResponse<InsertResponse> response =
                wishListService.addPackageWishList(packageWishListInsertRequest);

        LOGGER.info("{} End execute add package wish list {}", Constant.DOTS, Constant.DOTS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping(path = "/add-tour-wish-list")
    public ResponseEntity<CommonResponse<InsertResponse>> addTourWishList(
            @RequestBody TourWishListInsertRequest tourWishListInsertRequest) {

        LOGGER.info("{} Start execute add tour wish list {}", Constant.DOTS, Constant.DOTS);

        CommonResponse<InsertResponse> response =
                wishListService.addTourWishList(tourWishListInsertRequest);

        LOGGER.info("{} End execute add tour wish list {}", Constant.DOTS, Constant.DOTS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
