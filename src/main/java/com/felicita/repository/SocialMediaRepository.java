package com.felicita.repository;

import com.felicita.model.response.SocialMediaResponse;

import java.util.List;

public interface SocialMediaRepository {

    List<SocialMediaResponse> getAllSocialMediaData();
}
