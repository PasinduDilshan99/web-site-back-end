package com.felicita.controller;

import com.felicita.model.request.FaqViewCountUpdateRequest;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.FaqResponse;
import com.felicita.model.response.UpdateResponse;
import com.felicita.service.FaqService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v0/api/faq")
public class FaqController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FaqController.class);

    private final FaqService faqService;

    @Autowired
    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<FaqResponse>>> getAllFaqItems(){
        LOGGER.info("{} Start execute get all faq data {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<FaqResponse>>> response = faqService.getAllFaqItems();
        LOGGER.info("{} End execute get all faq data {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/visible")
    public ResponseEntity<CommonResponse<List<FaqResponse>>> getAllVisibleFaqItems(){
        LOGGER.info("{} Start execute get all visible faq data {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<FaqResponse>>> response = faqService.getAllVisibleFaqItems();
        LOGGER.info("{} End execute get all visible faq data {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @PutMapping(path = "/view-count")
    public ResponseEntity<CommonResponse<UpdateResponse>> updateFaqViewCount(@RequestBody FaqViewCountUpdateRequest faqViewCountUpdateRequest){
        LOGGER.info("{} Start execute update faq data {}", Constant.DOTS, Constant.DOTS);
        LOGGER.info(faqViewCountUpdateRequest.toString());
        ResponseEntity<CommonResponse<UpdateResponse>> response = faqService.updateFaqViewCount(faqViewCountUpdateRequest);
        LOGGER.info("{} End execute update faq data {}", Constant.DOTS, Constant.DOTS);
        return response;
    }
}
