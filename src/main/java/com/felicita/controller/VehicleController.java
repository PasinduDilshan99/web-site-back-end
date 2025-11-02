package com.felicita.controller;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.ServiceProviderDetailsResponse;
import com.felicita.model.response.VehicleDetailResponse;
import com.felicita.model.response.VehicleResponse;
import com.felicita.service.VehicleService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
