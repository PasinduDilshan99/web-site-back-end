package com.felicita.controller;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.LinkBarResponse;
import com.felicita.model.response.NavBarResponse;
import com.felicita.service.NavBarService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v0/api/nav-bar")
public class NavBarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NavBarController.class);

    private final NavBarService navBarService;

    @Autowired
    public NavBarController(NavBarService navBarService) {
        this.navBarService = navBarService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<NavBarResponse>>> getAllNavBarItems(){
        LOGGER.info("{} Start execute get all nav bar data {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<NavBarResponse>>> response = navBarService.getAllNavBarItems();
        LOGGER.info("{} End execute get all nav bar data {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/visible")
    public ResponseEntity<CommonResponse<List<NavBarResponse>>> getAllVisibleNavBarItems(){
        LOGGER.info("{} Start execute get all visible nav bar data {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<NavBarResponse>>> response = navBarService.getAllVisibleNavBarItems();
        LOGGER.info("{} End execute get all visible nav bar data {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

}
