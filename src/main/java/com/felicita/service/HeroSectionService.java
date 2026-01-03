package com.felicita.service;

import com.felicita.model.response.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HeroSectionService {
    ResponseEntity<CommonResponse<List<HeroSectionResponse>>> getAllHeroSectionItems();

    ResponseEntity<CommonResponse<List<HeroSectionResponse>>> getAllVisibleHeroSectionItems();

    CommonResponse<List<AboutUsHeroSectionResponse>> getAboutUsHeroSectionDetails();

    CommonResponse<List<ContactUsHeroSectionResponse>> getContactUsHeroSectionDetails();

    CommonResponse<List<BlogHeroSectionResponse>> getBlogHeroSectionDetails();

    CommonResponse<List<FaqHeroSectionResponse>> getFAQHeroSectionDetails();

    CommonResponse<List<TourHeroSectionResponse>> getTourHeroSectionDetails();

    CommonResponse<List<PackageHeroSectionResponse>> getPackageHeroSectionDetails();

    CommonResponse<List<DestinationHeroSectionResponse>> getDestinationHeroSectionDetails();

    CommonResponse<List<ActivityHeroSectionResponse>> getActivityHeroSectionDetails();

    CommonResponse<List<PackageScheduleHeroSectionResponse>> getPackageScheduleHeroSectionDetails(Long packageScheduleId);

    CommonResponse<List<BookedTourHeroSectionResponse>> getBookedTourHeroSectionDetails(Long bookingId);
}
