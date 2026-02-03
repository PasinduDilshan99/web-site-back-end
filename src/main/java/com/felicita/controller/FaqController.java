package com.felicita.controller;

import com.felicita.model.request.FaqViewCountUpdateRequest;
import com.felicita.model.request.InsertFaqRequest;
import com.felicita.model.response.*;
import com.felicita.service.FaqService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v0/faq")
public class FaqController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FaqController.class);

    private final FaqService faqService;

    @Autowired
    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<FaqResponse>>> getAllFaqData(){
        LOGGER.info("{} Start execute get all faq data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<FaqResponse>> response = faqService.getAllFaqData();
        LOGGER.info("{} End execute get all faq data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/active")
    public ResponseEntity<CommonResponse<List<FaqResponse>>> getActiveFaqData(){
        LOGGER.info("{} Start execute get active faq data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<FaqResponse>> response = faqService.getActiveFaqData();
        LOGGER.info("{} End execute get active faq data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/view-count")
    public ResponseEntity<CommonResponse<UpdateResponse>> updateFaqViewCount(@RequestBody FaqViewCountUpdateRequest faqViewCountUpdateRequest){
        LOGGER.info("{} Start execute update faq data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<UpdateResponse> response = faqService.updateFaqViewCount(faqViewCountUpdateRequest);
        LOGGER.info("{} End execute update faq data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/options")
    public ResponseEntity<CommonResponse<List<FaqOptionDetailsResponse>>> getFaqOptions(){
        LOGGER.info("{} Start execute get faq options data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<FaqOptionDetailsResponse>> response = faqService.getFaqOptions();
        LOGGER.info("{} End execute get faq options data {}", Constant.DOTS, Constant.DOTS);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/insert-faq-request")
    public ResponseEntity<CommonResponse<InsertResponse>> insertFaqRequest(@RequestBody InsertFaqRequest insertFaqRequest){
        LOGGER.info("{} Start execute insert faq request data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<InsertResponse> response = faqService.insertFaqRequest(insertFaqRequest);
        LOGGER.info("{} End execute insert faq request data {}", Constant.DOTS, Constant.DOTS);
        return ResponseEntity.ok(response);
    }

}
