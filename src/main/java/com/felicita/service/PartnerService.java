package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PartnerService {
    ResponseEntity<CommonResponse<List<PartnerResponse>>> getAllPartners();

    ResponseEntity<CommonResponse<List<PartnerResponse>>> getAllVisiblePartners();
}
