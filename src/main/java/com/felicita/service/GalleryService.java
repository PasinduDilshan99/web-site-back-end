package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.GalleryResponse;
import java.util.List;

public interface GalleryService {

    CommonResponse<List<GalleryResponse>> getAllImages();

    CommonResponse<List<GalleryResponse>> getOpenImages();

}
