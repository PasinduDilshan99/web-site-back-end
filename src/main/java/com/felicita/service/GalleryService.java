package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.GalleryResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GalleryService {
    ResponseEntity<CommonResponse<List<GalleryResponse>>> getAllImages();

    ResponseEntity<CommonResponse<List<GalleryResponse>>> getOpenImages();
}
