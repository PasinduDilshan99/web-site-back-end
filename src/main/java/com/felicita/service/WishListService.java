package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.WishListResponse;

public interface WishListService {
    CommonResponse<WishListResponse> getWishListDetails();
}
