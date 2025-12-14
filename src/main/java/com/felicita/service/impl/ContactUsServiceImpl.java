package com.felicita.service.impl;

import com.felicita.repository.ContactUsRepository;
import com.felicita.service.ContactUsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactUsServiceImpl.class);

    private final ContactUsRepository contactUsRepository;

    @Autowired
    public ContactUsServiceImpl(ContactUsRepository contactUsRepository) {
        this.contactUsRepository = contactUsRepository;
    }
}
