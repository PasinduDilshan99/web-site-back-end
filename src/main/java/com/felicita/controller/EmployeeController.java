package com.felicita.controller;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.CouponDetailsResponse;
import com.felicita.model.response.EmployeeWithSocialMediaResponse;
import com.felicita.service.EmployeeService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v0/employee")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(path = "/with-social-media-links")
    public ResponseEntity<CommonResponse<List<EmployeeWithSocialMediaResponse>>> getEmployeeWithSocailMedia(){
        LOGGER.info("{} Start execute get employee with social media details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<EmployeeWithSocialMediaResponse>> response = employeeService.getEmployeeWithSocailMedia();
        LOGGER.info("{} End execute get employee with social media details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
