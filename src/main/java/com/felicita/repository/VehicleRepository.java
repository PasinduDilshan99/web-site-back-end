package com.felicita.repository;

import com.felicita.model.dto.VehicleBasicDetailsDto;
import com.felicita.model.response.VehicleDetailResponse;
import com.felicita.model.response.VehicleResponse;

import java.util.List;

public interface VehicleRepository {
    List<VehicleResponse> getActiveVehicles();

    List<VehicleDetailResponse> getVehicleDetailsById(String vehicleId);

    VehicleBasicDetailsDto getVehicleBasicDetailsById(Long vehicleId);
}
