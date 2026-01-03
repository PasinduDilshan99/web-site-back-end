package com.felicita.controller;

import com.felicita.model.response.BlogTagResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.ContactMethodResponse;
import com.felicita.service.ContactUsService;
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
@RequestMapping(path = "/api/v0/contact-us")
public class ContactUsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactUsController.class);

    private final ContactUsService contactUsService;

    @Autowired
    public ContactUsController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }

    @GetMapping(path = "/contact-methods")
    public ResponseEntity<CommonResponse<List<ContactMethodResponse>>> getContactMethods() {
        LOGGER.info("{} Start execute get active contact methods {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<ContactMethodResponse>> response = contactUsService.getContactMethods();
        LOGGER.info("{} End execute get active contact methods {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
