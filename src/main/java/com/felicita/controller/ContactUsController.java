package com.felicita.controller;

import com.felicita.service.ContactUsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v0/contact-us")
public class ContactUsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactUsController.class);

    private final ContactUsService contactUsService;

    @Autowired
    public ContactUsController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }
}
