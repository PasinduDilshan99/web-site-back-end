package com.felicita.controller;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.SocialMediaResponse;
import com.felicita.service.SocialMediaService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v0/social-media")
public class SocialMediaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocialMediaController.class);

    private final SocialMediaService socialMediaService;

    @Autowired
    public SocialMediaController(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<SocialMediaResponse>>> getAllSocialMediaData(){
        LOGGER.info("{} Start execute get all social media data {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<SocialMediaResponse>>> response = socialMediaService.getAllSocialMediaData();
        LOGGER.info("{} End execute get all social media data {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active")
    public ResponseEntity<CommonResponse<List<SocialMediaResponse>>> getActiveSocialMediaData(){
        LOGGER.info("{} Start execute get all active social media data {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<SocialMediaResponse>>> response = socialMediaService.getActiveSocialMediaData();
        LOGGER.info("{} End execute get all active social media data {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

}
