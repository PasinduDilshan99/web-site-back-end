package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.LinkBarResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LinkBarService {
    ResponseEntity<CommonResponse<List<LinkBarResponse>>> getAllLinkBarItems();

    ResponseEntity<CommonResponse<List<LinkBarResponse>>> getAllVisibleLinkBarItems();
}
