package com.felicita.repository;

import com.felicita.model.dto.VehicleBasicDetailsDto;
import com.felicita.model.request.VehicleSpecificationSearchRequest;
import com.felicita.model.response.*;

import java.util.List;

public interface VehicleRepository {
    List<VehicleResponse> getActiveVehicles();

    List<VehicleDetailResponse> getVehicleDetailsById(String vehicleId);

    VehicleBasicDetailsDto getVehicleBasicDetailsById(Long vehicleId);

    List<VehicleIdAndNameResponse> getVehiclesNumberAndIds();

    VehicleSpecificationDetailsResponse getVehicleSpecificationDetailsById(Long specificationId);

    VehicleSpecificationSearchResponse getVehicleSpecificationDetails(VehicleSpecificationSearchRequest vehicleSpecificationSearchRequest);

    List<String> getDistinctMakes();

    List<String> getDistinctModels();

    List<Integer> getDistinctYears();

    List<String> getDistinctBodyTypes();

    VehicleSpecificationFilterResponse.HorsePowerRange getDistinctHorsePowers();

    List<Integer> getDistinctSeatsCount();

    List<String> getDistinctRoofTypes();

    List<String> getDistinctAcTypes();
}
