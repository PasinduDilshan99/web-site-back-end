package com.felicita.controller;

import com.felicita.model.response.*;
import com.felicita.service.ServiceProviderService;
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
@RequestMapping(path = "/api/v0/service-provider")
public class ServiceProviderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceProviderController.class);

    private final ServiceProviderService serviceProviderService;

    @Autowired
    public ServiceProviderController(ServiceProviderService serviceProviderService) {
        this.serviceProviderService = serviceProviderService;
    }

    @GetMapping(path = "/{serviceProviderId}")
    public ResponseEntity<CommonResponse<ServiceProviderDetailsResponse>> getServiceProviderDetailsById(@PathVariable String serviceProviderId){
        LOGGER.info("{} Start execute get service provider details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<ServiceProviderDetailsResponse> response = serviceProviderService.getServiceProviderDetailsById(serviceProviderId);
        LOGGER.info("{} End execute get service provider details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping(path = "/names-and-ids")
    public ResponseEntity<CommonResponse<List<ServiceProviderIdsAndNamesReponse>>> getServiceProviderNamesAndIds() {
        LOGGER.info("{} Start execute get all active service provider ids and names {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<ServiceProviderIdsAndNamesReponse>> response = serviceProviderService.getServiceProviderNamesAndIds();
        LOGGER.info("{} End execute get all active service provider ids and names {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
