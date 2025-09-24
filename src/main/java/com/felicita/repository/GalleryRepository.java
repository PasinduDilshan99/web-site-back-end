package com.felicita.repository;

import com.felicita.model.response.GalleryResponse;

import java.util.List;

public interface GalleryRepository {
    List<GalleryResponse> getAllImages();
}
