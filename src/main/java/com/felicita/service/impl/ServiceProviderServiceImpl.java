package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.ServiceProviderDetailsResponse;
import com.felicita.repository.ServiceProviderRepository;
import com.felicita.service.ServiceProviderService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceProviderServiceImpl implements ServiceProviderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceProviderServiceImpl.class);

    private final ServiceProviderRepository serviceProviderRepository;

    @Autowired
    public ServiceProviderServiceImpl(ServiceProviderRepository serviceProviderRepository) {
        this.serviceProviderRepository = serviceProviderRepository;
    }


    @Override
    public CommonResponse<ServiceProviderDetailsResponse>  getServiceProviderDetailsById(String serviceProviderId) {
        LOGGER.info("Start fetching all partners from repository");
        ServiceProviderDetailsResponse serviceProviderDetailsResponses = new ServiceProviderDetailsResponse();
        try {
            ServiceProviderDetailsResponse.ServiceProviderDetails serviceProviderDetails = serviceProviderRepository.getServiceProviderDetails(serviceProviderId);
            List<ServiceProviderDetailsResponse.NearbyDestination> nearbyDestinations = serviceProviderRepository.getNearByDestiantions(serviceProviderId);
            List<ServiceProviderDetailsResponse.MealDetail> mealDetails = serviceProviderRepository.getMealDetails(serviceProviderId);
            List<ServiceProviderDetailsResponse.RoomDetail> roomDetails  = serviceProviderRepository.getRoomDetails(serviceProviderId);
            List<ServiceProviderDetailsResponse.PackageDetail> packageDetails = serviceProviderRepository.getPackageDetails(serviceProviderId);
            List<ServiceProviderDetailsResponse.AmenityDetail> amenityDetails = serviceProviderRepository.getAmenityDetails(serviceProviderId);
            List<ServiceProviderDetailsResponse.FacilityDetail> facilityDetails = serviceProviderRepository.getFacilityDetails(serviceProviderId);
            List<ServiceProviderDetailsResponse.SeasonalPricing> seasonalPricings = serviceProviderRepository.getSeasonalPricings(serviceProviderId);
            List<ServiceProviderDetailsResponse.ContactPerson> contactPeoples = serviceProviderRepository.getContactPersons(serviceProviderId);
            List<ServiceProviderDetailsResponse.BankDetail> bankDetails = serviceProviderRepository.getBankDetails(serviceProviderId);
            ServiceProviderDetailsResponse.TaxAndCommissionDetails taxAndCommissionDetails = serviceProviderRepository.getTaxAndCommissionDetails(serviceProviderId);
            List<ServiceProviderDetailsResponse.BookingRestriction> bookingRestrictions = serviceProviderRepository.getBookingRestrictions(serviceProviderId);
            ServiceProviderDetailsResponse.Statistics statistics = serviceProviderRepository.getStatistics(serviceProviderId);
            List<ServiceProviderDetailsResponse.SocialMedia> socialMedias = serviceProviderRepository.getSocialMedias(serviceProviderId);
            List<ServiceProviderDetailsResponse.ReviewDetail> reviewDetails = serviceProviderRepository.getReviewDetails(serviceProviderId);

            serviceProviderDetailsResponses.setServiceProviderDetails(serviceProviderDetails);
            serviceProviderDetailsResponses.setNearbyDestinations(nearbyDestinations);
            serviceProviderDetailsResponses.setMealDetails(mealDetails);
            serviceProviderDetailsResponses.setRoomDetails(roomDetails);
            serviceProviderDetailsResponses.setPackageDetails(packageDetails);
            serviceProviderDetailsResponses.setAmenities(amenityDetails);
            serviceProviderDetailsResponses.setFacilities(facilityDetails);
            serviceProviderDetailsResponses.setSeasonalPricing(seasonalPricings);
            serviceProviderDetailsResponses.setContactPersons(contactPeoples);
            serviceProviderDetailsResponses.setBankDetails(bankDetails);
            serviceProviderDetailsResponses.setTaxAndCommissionDetails(taxAndCommissionDetails);
            serviceProviderDetailsResponses.setBookingRestrictions(bookingRestrictions);
            serviceProviderDetailsResponses.setStatistics(statistics);
            serviceProviderDetailsResponses.setSocialMedia(socialMedias);
            serviceProviderDetailsResponses.setReviews(reviewDetails);

            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            serviceProviderDetailsResponses,
                            Instant.now()
                    ));

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching partners: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching partners: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch partners from database");
        } finally {
            LOGGER.info("End fetching all partners from repository");
        }
    }
}
