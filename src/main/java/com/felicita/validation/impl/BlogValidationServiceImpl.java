package com.felicita.validation.impl;

import com.felicita.exception.ValidationFailedErrorExceptionHandler;
import com.felicita.model.request.CreateBlogRequest;
import com.felicita.model.response.ValidationFailedResponse;
import com.felicita.model.response.ValidationResultResponse;
import com.felicita.validation.BlogValidationService;
import com.felicita.validation.CommonValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogValidationServiceImpl implements BlogValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogValidationServiceImpl.class);

    private final CommonValidationService commonValidationService;

    @Autowired
    public BlogValidationServiceImpl(CommonValidationService commonValidationService) {
        this.commonValidationService = commonValidationService;
    }

    @Override
    public void validateCreateBlogRequest(CreateBlogRequest createBlogRequest) {
        List<ValidationFailedResponse> validationFailedResponses = new ArrayList<>();
        ValidationResultResponse title = commonValidationService.validateNotNullOrEmpty("title", createBlogRequest.getTitle());
        if (!title.isValid()) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field(title.getField()).value(title.getMessage()).build());
        }
        ValidationResultResponse subTitle = commonValidationService.validateNotNullOrEmpty("subTitle", createBlogRequest.getSubTitle());
        if (!subTitle.isValid()) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field(subTitle.getField()).value(subTitle.getMessage()).build());
        }
        ValidationResultResponse description = commonValidationService.validateNotNullOrEmpty("description", createBlogRequest.getDescription());
        if (!description.isValid()) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field(description.getField()).value(description.getMessage()).build());
        }

        int index = 0;
        for (String imageUrl : createBlogRequest.getImageUrls()) {
            ValidationResultResponse url = commonValidationService.validateNotNullOrEmpty("image url", imageUrl);
            if (!url.isValid()) {
                validationFailedResponses.add(
                        ValidationFailedResponse.builder()
                                .field(url.getField() + " - index " + index)
                                .value(url.getMessage())
                                .build()
                );
            }
            index++;
        }


        LOGGER.info(validationFailedResponses.toString());
        if (!validationFailedResponses.isEmpty()) {
            throw new ValidationFailedErrorExceptionHandler("Validation failed : createBlogRequest", validationFailedResponses);
        }
    }
}
