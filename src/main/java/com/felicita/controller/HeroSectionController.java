package com.felicita.controller;

import com.felicita.model.response.*;
import com.felicita.service.HeroSectionService;
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
@RequestMapping(path = "/api/v0/hero-section")
public class HeroSectionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeroSectionController.class);

    private final HeroSectionService heroSectionService;

    @Autowired
    public HeroSectionController(HeroSectionService heroSectionService) {
        this.heroSectionService = heroSectionService;
    }

    @GetMapping(path = "/home-all")
    public ResponseEntity<CommonResponse<List<HeroSectionResponse>>> getAllHomeHeroSectionData(){
        LOGGER.info("{} Start execute get home hero section all data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<HeroSectionResponse>> response = heroSectionService.getAllHomeHeroSectionData();
        LOGGER.info("{} End execute get home hero section all data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/home")
    public ResponseEntity<CommonResponse<List<HeroSectionResponse>>> getHomeHeroSectionDetails(){
        LOGGER.info("{} Start execute get home hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<HeroSectionResponse>> response = heroSectionService.getHomeHeroSectionDetails();
        LOGGER.info("{} End execute get home hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/about-us")
    public ResponseEntity<CommonResponse<List<AboutUsHeroSectionResponse>>> getAboutUsHeroSectionDetails(){
        LOGGER.info("{} Start execute get about us hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<AboutUsHeroSectionResponse>> response = heroSectionService.getAboutUsHeroSectionDetails();
        LOGGER.info("{} End execute get about us hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/contact-us")
    public ResponseEntity<CommonResponse<List<ContactUsHeroSectionResponse>>> getContactUsHeroSectionDetails(){
        LOGGER.info("{} Start execute get contact us hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<ContactUsHeroSectionResponse>> response = heroSectionService.getContactUsHeroSectionDetails();
        LOGGER.info("{} End execute get contact us hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/blog")
    public ResponseEntity<CommonResponse<List<BlogHeroSectionResponse>>> getBlogHeroSectionDetails(){
        LOGGER.info("{} Start execute get blog hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<BlogHeroSectionResponse>> response = heroSectionService.getBlogHeroSectionDetails();
        LOGGER.info("{} End execute get blog hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/faq")
    public ResponseEntity<CommonResponse<List<FaqHeroSectionResponse>>> getFAQHeroSectionDetails(){
        LOGGER.info("{} Start execute get faq hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<FaqHeroSectionResponse>> response = heroSectionService.getFAQHeroSectionDetails();
        LOGGER.info("{} End execute get faq hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/tour")
    public ResponseEntity<CommonResponse<List<TourHeroSectionResponse>>> getTourHeroSectionDetails(){
        LOGGER.info("{} Start execute get tour hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<TourHeroSectionResponse>> response = heroSectionService.getTourHeroSectionDetails();
        LOGGER.info("{} End execute get tour hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/package")
    public ResponseEntity<CommonResponse<List<PackageHeroSectionResponse>>> getPackageHeroSectionDetails(){
        LOGGER.info("{} Start execute get package hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<PackageHeroSectionResponse>> response = heroSectionService.getPackageHeroSectionDetails();
        LOGGER.info("{} End execute get package hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/package-schedule/{packageScheduleId}")
    public ResponseEntity<CommonResponse<List<PackageScheduleHeroSectionResponse>>> getPackageScheduleHeroSectionDetails(@PathVariable Long packageScheduleId){
        LOGGER.info("{} Start execute get package schedule hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<PackageScheduleHeroSectionResponse>> response = heroSectionService.getPackageScheduleHeroSectionDetails(packageScheduleId);
        LOGGER.info("{} End execute get package schedule hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/booked-tour/{bookingId}")
    public ResponseEntity<CommonResponse<List<BookedTourHeroSectionResponse>>> getBookedTourHeroSectionDetails(@PathVariable Long bookingId){
        LOGGER.info("{} Start execute get booked tour hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<BookedTourHeroSectionResponse>> response = heroSectionService.getBookedTourHeroSectionDetails(bookingId);
        LOGGER.info("{} End execute get booked tour hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/destination")
    public ResponseEntity<CommonResponse<List<DestinationHeroSectionResponse>>> getDestinationHeroSectionDetails(){
        LOGGER.info("{} Start execute get destination hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<DestinationHeroSectionResponse>> response = heroSectionService.getDestinationHeroSectionDetails();
        LOGGER.info("{} End execute get destination hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/activity")
    public ResponseEntity<CommonResponse<List<ActivityHeroSectionResponse>>> getActivityHeroSectionDetails(){
        LOGGER.info("{} Start execute get activity hero section data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<ActivityHeroSectionResponse>> response = heroSectionService.getActivityHeroSectionDetails();
        LOGGER.info("{} End execute get activity hero section data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
