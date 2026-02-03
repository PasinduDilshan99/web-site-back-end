package com.felicita.repository;

import com.felicita.model.response.*;
import java.util.List;

public interface HeroSectionRepository {

    List<HeroSectionResponse> getAllHomeHeroSectionData();

    List<AboutUsHeroSectionResponse> getAboutUsHeroSectionDetails();

    List<ContactUsHeroSectionResponse> getContactUsHeroSectionDetails();

    List<BlogHeroSectionResponse> getBlogHeroSectionDetails();

    List<FaqHeroSectionResponse> getFAQHeroSectionDetails();

    List<TourHeroSectionResponse> getTourHeroSectionDetails();

    List<ActivityHeroSectionResponse> getActivityHeroSectionDetails();

    List<DestinationHeroSectionResponse> getDestinationHeroSectionDetails();

    List<PackageHeroSectionResponse> getPackageHeroSectionDetails();

    List<PackageScheduleHeroSectionResponse> getPackageScheduleHeroSectionDetails(Long packageScheduleId);

    List<BookedTourHeroSectionResponse> getBookedTourHeroSectionDetails(Long bookingId);

    List<ActivityDetailsHeroSectionResponse> getActivityHeroSectionDetailsByActivityId(Long activityId);

}
