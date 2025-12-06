package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.CouponDetailsResponse;
import com.felicita.model.response.EmployeeGuideResponse;
import com.felicita.model.response.EmployeeWithSocialMediaResponse;
import com.felicita.repository.EmployeeRepository;
import com.felicita.service.EmployeeService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public CommonResponse<List<EmployeeWithSocialMediaResponse>> getEmployeeWithSocailMedia() {
        LOGGER.info("Start fetching employee with social media details from repository");
        try {
            List<EmployeeWithSocialMediaResponse> employeeWithSocialMediaResponses = employeeRepository.getEmployeeWithSocailMedia();
            LOGGER.info("Fetched employee with social media details successfully");
            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            employeeWithSocialMediaResponses,
                            Instant.now()
                    )
            );
        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching employee with social media details : {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching employee with social media details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch employee with social media details from database");
        } finally {
            LOGGER.info("End fetching employee with social media details from repository");
        }
    }

    @Override
    public CommonResponse<List<EmployeeWithSocialMediaResponse>> getALlEmployeeWithSocailMedia() {
        LOGGER.info("Start fetching all employee with social media details from repository");
        try {
            List<EmployeeWithSocialMediaResponse> employeeWithSocialMediaResponses = employeeRepository.getALlEmployeeWithSocailMedia();
            LOGGER.info("Fetched all employee with social media details successfully");
            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            employeeWithSocialMediaResponses,
                            Instant.now()
                    )
            );
        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching all employee with social media details : {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching all employee with social media details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch all employee with social media details from database");
        } finally {
            LOGGER.info("End fetching all employee with social media details from repository");
        }
    }

    @Override
    public CommonResponse<List<EmployeeGuideResponse>> getEmployeeGiudeDetails() {
        LOGGER.info("Start fetching all employee with social media details from repository");
        try {
            List<EmployeeGuideResponse> employeeGuideResponses = employeeRepository.getEmployeeGuideDetails();
            LOGGER.info(employeeGuideResponses.toString());
            LOGGER.info("Fetched all employee with social media details successfully");
            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            employeeGuideResponses,
                            Instant.now()
                    )
            );
        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching all employee with social media details : {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching all employee with social media details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch all employee with social media details from database");
        } finally {
            LOGGER.info("End fetching all employee with social media details from repository");
        }
    }
}
