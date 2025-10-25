package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.LinkBarItemStatus;
import com.felicita.model.enums.NavBarItemStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.LinkBarResponse;
import com.felicita.model.response.NavBarResponse;
import com.felicita.repository.NavBarRepository;
import com.felicita.service.NavBarService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CommonResponse<List<NavBarResponse>>> getAllNavBarItems() {
        LOGGER.info("Start fetching all nav bar items from repository");
        try {
            List<NavBarResponse> navBarResponses = navBarRepository.getAllNavBarItems();

            if (navBarResponses.isEmpty()) {
                LOGGER.warn("No nav bar items found in database");
                throw new DataNotFoundErrorExceptionHandler("No nav bar items found");
            }

            LOGGER.info("Fetched {} nav bar items with submenus successfully", navBarResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            navBarResponses,
                            Instant.now()
                    )
            );

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching nav bar items: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch nav bar items from database");
        } finally {
            LOGGER.info("End fetching all nav bar items from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<NavBarResponse>>> getAllVisibleNavBarItems() {
        LOGGER.info("Start fetching all visible LinkBar items from repository");

        try {
            List<NavBarResponse> navBarResponses = navBarRepository.getAllNavBarItems();

            if (navBarResponses.isEmpty()) {
                LOGGER.warn("No LinkBar items found in database");
                throw new DataNotFoundErrorExceptionHandler("No LinkBar items found");
            }

            List<NavBarResponse> visibleList = navBarResponses.stream()
                    .filter(item -> NavBarItemStatus.VISIBLE.toString().equalsIgnoreCase(item.getStatus()))
                    .toList();

            if (visibleList.isEmpty()) {
                LOGGER.warn("No visible nav bar items found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible nav bar items found");
            }

            LOGGER.info("Fetched {} visible nav bar items successfully", visibleList.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            visibleList,
                            Instant.now()
                    )
            );

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching visible nav bar items: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch nav bar items from database");
        } finally {
            LOGGER.info("End fetching all visible nav bar items from repository");
        }
    }
}
