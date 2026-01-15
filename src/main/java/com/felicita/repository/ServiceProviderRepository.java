package com.felicita.repository;

import com.felicita.model.response.ServiceProviderDetailsResponse;
import com.felicita.model.response.ServiceProviderIdsAndNamesReponse;

import java.util.List;

public interface ServiceProviderRepository {
    ServiceProviderDetailsResponse.ServiceProviderDetails getServiceProviderDetails(String serviceProviderId);

    List<ServiceProviderDetailsResponse.NearbyDestination> getNearByDestiantions(String serviceProviderId);

    List<ServiceProviderDetailsResponse.MealDetail> getMealDetails(String serviceProviderId);

    List<ServiceProviderDetailsResponse.RoomDetail> getRoomDetails(String serviceProviderId);

    List<ServiceProviderDetailsResponse.PackageDetail> getPackageDetails(String serviceProviderId);

    List<ServiceProviderDetailsResponse.AmenityDetail> getAmenityDetails(String serviceProviderId);

    List<ServiceProviderDetailsResponse.FacilityDetail> getFacilityDetails(String serviceProviderId);

    List<ServiceProviderDetailsResponse.SeasonalPricing> getSeasonalPricings(String serviceProviderId);

    List<ServiceProviderDetailsResponse.ContactPerson> getContactPersons(String serviceProviderId);

    List<ServiceProviderDetailsResponse.BankDetail> getBankDetails(String serviceProviderId);

    ServiceProviderDetailsResponse.TaxAndCommissionDetails getTaxAndCommissionDetails(String serviceProviderId);

    List<ServiceProviderDetailsResponse.BookingRestriction> getBookingRestrictions(String serviceProviderId);

    ServiceProviderDetailsResponse.Statistics getStatistics(String serviceProviderId);

    List<ServiceProviderDetailsResponse.SocialMedia> getSocialMedias(String serviceProviderId);

    List<ServiceProviderDetailsResponse.ReviewDetail> getReviewDetails(String serviceProviderId);

    List<ServiceProviderIdsAndNamesReponse> getServiceProviderNamesAndIds();
}
