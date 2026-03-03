package com.felicita.controller;

import com.felicita.model.request.VehicleSpecificationSearchRequest;
import com.felicita.model.response.*;
import com.felicita.service.VehicleService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v0/vehicles")
public class VehicleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping(path = "/active-vehicles")
    public ResponseEntity<CommonResponse<List<VehicleResponse>>> getActiveVehicles(){
        LOGGER.info("{} Start execute get vehicles details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<VehicleResponse>> response = vehicleService.getActiveVehicles();
        LOGGER.info("{} End execute get vehicles details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{vehicleId}")
    public ResponseEntity<CommonResponse<List<VehicleDetailResponse>>> getVehicleDetailsById(@PathVariable String vehicleId){
        LOGGER.info("{} Start execute get vehicles details by id {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<VehicleDetailResponse>> response = vehicleService.getVehicleDetailsById(vehicleId);
        LOGGER.info("{} End execute get vehicles details by id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/specification/{specificationId}")
    public ResponseEntity<CommonResponse<VehicleSpecificationDetailsResponse>> getVehicleSpecificationDetailsById(@PathVariable Long specificationId){
        LOGGER.info("{} Start execute get vehicle specification details by id {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<VehicleSpecificationDetailsResponse> response = vehicleService.getVehicleSpecificationDetailsById(specificationId);
        LOGGER.info("{} End execute get vehicle specification details by id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/specification-filters")
    public ResponseEntity<CommonResponse<VehicleSpecificationFilterResponse>> getVehicleSpecificationFilters(){
        LOGGER.info("{} Start execute get vehicle specification filters {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<VehicleSpecificationFilterResponse> response = vehicleService.getVehicleSpecificationFilters();
        LOGGER.info("{} End execute get vehicle specification filters {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/specification")
    public ResponseEntity<CommonResponse<VehicleSpecificationSearchResponse>> getVehicleSpecificationDetails(@RequestBody VehicleSpecificationSearchRequest vehicleSpecificationSearchRequest){
        LOGGER.info("{} Start execute get vehicle specification details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<VehicleSpecificationSearchResponse> response = vehicleService.getVehicleSpecificationDetails(vehicleSpecificationSearchRequest);
        LOGGER.info("{} End execute get vehicle specification details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/names-and-ids")
    public ResponseEntity<CommonResponse<List<VehicleIdAndNameResponse>>> getVehiclesNumberAndIds() {
        LOGGER.info("{} Start execute get all active vehicles ids and register numbers {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<VehicleIdAndNameResponse>> response = vehicleService.getVehiclesNumberAndIds();
        LOGGER.info("{} End execute get all active vehicles ids and register numbers {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
