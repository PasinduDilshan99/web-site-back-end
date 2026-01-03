package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.SocialMediaResponse;
import com.felicita.model.response.SocialMediaWithBestForRespone;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SocialMediaService {
    ResponseEntity<CommonResponse<List<SocialMediaResponse>>> getAllSocialMediaData();

    ResponseEntity<CommonResponse<List<SocialMediaResponse>>> getActiveSocialMediaData();

    CommonResponse<List<SocialMediaWithBestForRespone>> getSocialMediaWithBestForData();
}
