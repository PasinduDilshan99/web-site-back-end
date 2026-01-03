package com.felicita.service.impl;

import com.felicita.exception.InsertFailedErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.exception.ValidationFailedErrorExceptionHandler;
import com.felicita.model.request.CreateInquiryRequest;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.InsertResponse;
import com.felicita.repository.InquiryRepository;
import com.felicita.service.CommonService;
import com.felicita.service.InquiryService;
import com.felicita.util.CommonResponseMessages;
import com.felicita.validation.InquiryValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class InquiryServiceImpl implements InquiryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InquiryServiceImpl.class);

    private final InquiryRepository inquiryRepository;
    private final InquiryValidationService inquiryValidationService;
    private final CommonService commonService;

    @Autowired
    public InquiryServiceImpl(InquiryRepository inquiryRepository,
                              InquiryValidationService inquiryValidationService,
                              CommonService commonService) {
        this.inquiryRepository = inquiryRepository;
        this.inquiryValidationService = inquiryValidationService;
        this.commonService = commonService;
    }

    @Override
    public CommonResponse<InsertResponse> createInquiry(CreateInquiryRequest createInquiryRequest) {
        try {
            inquiryValidationService.validateCreateInquiryRequest(createInquiryRequest);
            Long userId = commonService.getUserIdBySecurityContextWithOutException();
            inquiryRepository.createInquiry(createInquiryRequest, userId);
            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            new InsertResponse("Successfully create a inquiry"),
                            Instant.now()
                    );
        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the create inquiry request", vfe.getValidationFailedResponses());
        } catch (InsertFailedErrorExceptionHandler ife) {
            throw new InsertFailedErrorExceptionHandler(ife.getMessage());

        } catch (Exception e) {
            LOGGER.error(e.toString());
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }
}
