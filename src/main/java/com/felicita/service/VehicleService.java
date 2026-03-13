package com.felicita.service;

import com.felicita.model.dto.VehicleBasicDetailsDto;
import com.felicita.model.request.VehicleSpecificationSearchRequest;
import com.felicita.model.response.*;

import java.util.List;

public interface VehicleService {
    CommonResponse<List<VehicleResponse>> getActiveVehicles();

    CommonResponse<List<VehicleDetailResponse>> getVehicleDetailsById(String vehicleId);

    VehicleBasicDetailsDto getVehicleBasicDetailsById(Long vehicleId);

    CommonResponse<List<VehicleIdAndNameResponse>> getVehiclesNumberAndIds();

    CommonResponse<VehicleSpecificationDetailsResponse> getVehicleSpecificationDetailsById(Long specificationId);

    CommonResponse<VehicleSpecificationSearchResponse> getVehicleSpecificationDetails(VehicleSpecificationSearchRequest vehicleSpecificationSearchRequest);

    CommonResponse<VehicleSpecificationFilterResponse> getVehicleSpecificationFilters();

    CommonResponse<List<VehicleTypeResponse>> getActiveVehiclesTypes();

    CommonResponse<VehicleTypeResponse> getActiveVehiclesTypesDetailsById(Long typeId);
}
