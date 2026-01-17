package com.felicita.service.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.NavBarItemStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.NavBarResponse;
import com.felicita.repository.NavBarRepository;
import com.felicita.service.NavBarService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service
public class NavBarServiceImpl implements NavBarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NavBarServiceImpl.class);

    private final NavBarRepository navBarRepository;

    @Autowired
    public NavBarServiceImpl(NavBarRepository navBarRepository) {
        this.navBarRepository = navBarRepository;
    }

    @Override
    public CommonResponse<List<NavBarResponse>> getAllNavBarData() {
        LOGGER.info("Start fetching all nav bar data from repository");
        try {
            List<NavBarResponse> navBarResponses = navBarRepository.getAllNavBarData();

            if (navBarResponses.isEmpty()) {
                LOGGER.warn("No nav bar data found in database");
                throw new DataNotFoundErrorExceptionHandler("No nav bar data found");
            }

            LOGGER.info("Fetched {} nav bar data with submenus successfully", navBarResponses.size());
            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    navBarResponses,
                    Instant.now());

        } catch (DataNotFoundErrorExceptionHandler e) {
            throw e;
        } catch (DataAccessErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching nav bar data: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch nav bar data from database");
        } finally {
            LOGGER.info("End fetching all nav bar data from repository");
        }
    }

    @Override
    public CommonResponse<List<NavBarResponse>> getActiveNavBarData() {
        LOGGER.info("Start fetching active nav bar data from repository");

        try {
            List<NavBarResponse> navBarResponses = getAllNavBarData().getData();

            List<NavBarResponse> visibleList = navBarResponses.stream()
                    .filter(item -> NavBarItemStatus.VISIBLE.toString().equalsIgnoreCase(item.getStatus()))
                    .toList();

            if (visibleList.isEmpty()) {
                LOGGER.warn("No active nav bar data found in database");
                throw new DataNotFoundErrorExceptionHandler("No active nav bar data found");
            }

            LOGGER.info("Fetched {} active nav bar data successfully", visibleList.size());

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    visibleList,
                    Instant.now());

        } catch (DataNotFoundErrorExceptionHandler e) {
            throw e;
        } catch (DataAccessErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active nav bar data: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch active nav bar data from database");
        } finally {
            LOGGER.info("End fetching active nav bar data from repository");
        }
    }

}
