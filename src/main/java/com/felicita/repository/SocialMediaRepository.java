package com.felicita.repository;

import com.felicita.model.response.SocialMediaResponse;
import com.felicita.model.response.SocialMediaWithBestForRespone;

import java.util.List;

public interface SocialMediaRepository {

    List<SocialMediaResponse> getAllSocialMediaData();

    List<SocialMediaWithBestForRespone> getSocialMediaWithBestForData();
}
