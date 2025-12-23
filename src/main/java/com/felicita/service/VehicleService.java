package com.felicita.service;

import com.felicita.model.dto.VehicleBasicDetailsDto;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.VehicleDetailResponse;
import com.felicita.model.response.VehicleResponse;

import java.util.List;

public interface VehicleService {
    CommonResponse<List<VehicleResponse>> getActiveVehicles();

    CommonResponse<List<VehicleDetailResponse>> getVehicleDetailsById(String vehicleId);

    VehicleBasicDetailsDto getVehicleBasicDetailsById(Long vehicleId);
}
