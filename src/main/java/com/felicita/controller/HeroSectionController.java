package com.felicita.controller;

import com.felicita.model.response.*;
import com.felicita.service.HeroSectionService;
import com.felicita.service.NavBarService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v0/api/hero-section")
public class HeroSectionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeroSectionController.class);

    private final HeroSectionService heroSectionService;

    @Autowired
    public HeroSectionController(HeroSectionService heroSectionService) {
        this.heroSectionService = heroSectionService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<HeroSectionResponse>>> getAllHeroSectionItems(){
        LOGGER.info("{} Start execute get all hero section data {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<HeroSectionResponse>>> response = heroSectionService.getAllHeroSectionItems();
        LOGGER.info("{} End execute get all hero section data {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/visible")
    public ResponseEntity<CommonResponse<List<HeroSectionResponse>>> getAllVisibleHeroSectionItems(){
        LOGGER.info("{} Start execute get all visible hero section data {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<HeroSectionResponse>>> response = heroSectionService.getAllVisibleHeroSectionItems();
        LOGGER.info("{} End execute get all visible hero section data {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/about-us")
    public ResponseEntity<CommonResponse<List<AboutUsHeroSectionResponse>>> getAboutUsHeroSectionDetails(){
        LOGGER.info("{} Start execute get all about us hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<AboutUsHeroSectionResponse>> response = heroSectionService.getAboutUsHeroSectionDetails();
        LOGGER.info("{} End execute get all about us hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/contact-us")
    public ResponseEntity<CommonResponse<List<ContactUsHeroSectionResponse>>> getContactUsHeroSectionDetails(){
        LOGGER.info("{} Start execute get all contact us hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<ContactUsHeroSectionResponse>> response = heroSectionService.getContactUsHeroSectionDetails();
        LOGGER.info("{} End execute get all contact us hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/blog")
    public ResponseEntity<CommonResponse<List<BlogHeroSectionResponse>>> getBlogHeroSectionDetails(){
        LOGGER.info("{} Start execute get all blog hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<BlogHeroSectionResponse>> response = heroSectionService.getBlogHeroSectionDetails();
        LOGGER.info("{} End execute get all blog hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/faq")
    public ResponseEntity<CommonResponse<List<FaqHeroSectionResponse>>> getFAQHeroSectionDetails(){
        LOGGER.info("{} Start execute get all faq hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<FaqHeroSectionResponse>> response = heroSectionService.getFAQHeroSectionDetails();
        LOGGER.info("{} End execute get all faq hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/tour")
    public ResponseEntity<CommonResponse<List<TourHeroSectionResponse>>> getTourHeroSectionDetails(){
        LOGGER.info("{} Start execute get all tour hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<TourHeroSectionResponse>> response = heroSectionService.getTourHeroSectionDetails();
        LOGGER.info("{} End execute get all tour hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/package")
    public ResponseEntity<CommonResponse<List<PackageHeroSectionResponse>>> getPackageHeroSectionDetails(){
        LOGGER.info("{} Start execute get all package hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<PackageHeroSectionResponse>> response = heroSectionService.getPackageHeroSectionDetails();
        LOGGER.info("{} End execute get all package hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/package-schedule/{packageScheduleId}")
    public ResponseEntity<CommonResponse<List<PackageScheduleHeroSectionResponse>>> getPackageScheduleHeroSectionDetails(@PathVariable Long packageScheduleId){
        LOGGER.info("{} Start execute get all package schedule hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<PackageScheduleHeroSectionResponse>> response = heroSectionService.getPackageScheduleHeroSectionDetails(packageScheduleId);
        LOGGER.info("{} End execute get all package schedule hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/booked-tour/{bookingId}")
    public ResponseEntity<CommonResponse<List<BookedTourHeroSectionResponse>>> getBookedTourHeroSectionDetails(@PathVariable Long bookingId){
        LOGGER.info("{} Start execute get all booked tour hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<BookedTourHeroSectionResponse>> response = heroSectionService.getBookedTourHeroSectionDetails(bookingId);
        LOGGER.info("{} End execute get all booked tour hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping(path = "/destination")
    public ResponseEntity<CommonResponse<List<DestinationHeroSectionResponse>>> getDestinationHeroSectionDetails(){
        LOGGER.info("{} Start execute get all destination hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<DestinationHeroSectionResponse>> response = heroSectionService.getDestinationHeroSectionDetails();
        LOGGER.info("{} End execute get all destination hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping(path = "/activity")
    public ResponseEntity<CommonResponse<List<ActivityHeroSectionResponse>>> getActivityHeroSectionDetails(){
        LOGGER.info("{} Start execute get all activity hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<ActivityHeroSectionResponse>> response = heroSectionService.getActivityHeroSectionDetails();
        LOGGER.info("{} End execute get all activity hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
