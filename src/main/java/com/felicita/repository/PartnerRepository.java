package com.felicita.repository;

import com.felicita.model.response.PartnerResponse;

import java.util.List;

public interface PartnerRepository {
    List<PartnerResponse> getAllPartners();
}
