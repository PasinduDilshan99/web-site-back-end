package com.felicita.controller;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.GalleryResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.service.GalleryService;
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
@RequestMapping(path = "/api/v0/gallery")
public class GalleryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GalleryController.class);

    private final GalleryService galleryService;

    @Autowired
    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<GalleryResponse>>> getAllImages(){
        LOGGER.info("{} Start execute get all images {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<GalleryResponse>>> response = galleryService.getAllImages();
        LOGGER.info("{} End execute get all images {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/open")
    public ResponseEntity<CommonResponse<List<GalleryResponse>>> getOpenImages(){
        LOGGER.info("{} Start execute get all open images {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<GalleryResponse>>> response = galleryService.getOpenImages();
        LOGGER.info("{} End execute get all open images {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

}
