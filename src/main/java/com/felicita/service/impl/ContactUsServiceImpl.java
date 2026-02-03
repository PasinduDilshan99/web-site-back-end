package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.ContactMethodResponse;
import com.felicita.repository.ContactUsRepository;
import com.felicita.service.ContactUsService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactUsServiceImpl.class);

    private final ContactUsRepository contactUsRepository;

    @Autowired
    public ContactUsServiceImpl(ContactUsRepository contactUsRepository) {
        this.contactUsRepository = contactUsRepository;
    }

    @Override
    public CommonResponse<List<ContactMethodResponse>> getContactMethods() {
        LOGGER.info("Start fetching active contact methods from repository");
        try {
            List<ContactMethodResponse> contactMethodResponses = contactUsRepository.getContactMethods();

            if (contactMethodResponses.isEmpty()) {
                LOGGER.warn("No contact methods found in database");
                throw new DataNotFoundErrorExceptionHandler("No contact methods found");
            }

            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            contactMethodResponses,
                            Instant.now());

        } catch (DataNotFoundErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active contact methods: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch contact methods from database");
        } finally {
            LOGGER.info("End fetching all active contact methods from repository");
        }
    }

}
