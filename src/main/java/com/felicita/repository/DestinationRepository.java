package com.felicita.repository;

import com.felicita.model.dto.DestinationResponseDto;
import com.felicita.model.response.*;

import java.util.List;

public interface DestinationRepository {
    List<DestinationResponseDto> getAllDestinations();

}
