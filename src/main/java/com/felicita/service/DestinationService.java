package com.felicita.service;

import com.felicita.model.dto.DestinationResponseDto;
import com.felicita.model.response.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DestinationService {
    ResponseEntity<CommonResponse<List<DestinationResponseDto>>> getAllDestinations();

    ResponseEntity<CommonResponse<List<DestinationResponseDto>>> getAllActiveDestinations();

}
