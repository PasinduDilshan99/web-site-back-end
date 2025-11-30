package com.felicita.controller;

import com.felicita.model.response.AccountSecurityDetailsResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.WishListResponse;
import com.felicita.service.WishListService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
