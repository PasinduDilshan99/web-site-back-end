package com.felicita.service.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.LinkBarItemStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.LinkBarResponse;
import com.felicita.repository.LinkBarRepository;
import com.felicita.service.LinkBarService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service
public class LinkBarServiceImpl implements LinkBarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkBarServiceImpl.class);

    private final LinkBarRepository linkBarRepository;

    @Autowired
    public LinkBarServiceImpl(LinkBarRepository linkBarRepository) {
        this.linkBarRepository = linkBarRepository;
    }

    @Override
    public CommonResponse<List<LinkBarResponse>> getAllLinkBarData() {
        LOGGER.info("Start fetching all link bar data from repository");
        try {
            List<LinkBarResponse> linkBarResponses = linkBarRepository.getAllLinkBarData();

            if (linkBarResponses.isEmpty()) {
                LOGGER.warn("No link bar data found in database");
                throw new DataNotFoundErrorExceptionHandler("No link bar data found");
            }

            LOGGER.info("Fetched {} link bar data successfully", linkBarResponses.size());
            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            linkBarResponses,
                            Instant.now());

        }catch (DataNotFoundErrorExceptionHandler dnfee){
            LOGGER.error("No link bar data found: {}", dnfee.getMessage(), dnfee);
            throw dnfee;
        }catch (DataAccessErrorExceptionHandler daee){
            LOGGER.error("Error occurred while fetching link bar data: {}", daee.getMessage(), daee);
            throw daee;
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching link bar data: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch link bar data from database");
        } finally {
            LOGGER.info("End fetching all link bar data from repository");
        }
    }

    @Override
    public CommonResponse<List<LinkBarResponse>> getActiveLinkBarData() {
        LOGGER.info("Start fetching active link bar data from repository");

        try {
            List<LinkBarResponse> linkBarResponses = getAllLinkBarData().getData();

            List<LinkBarResponse> activeList = linkBarResponses.stream()
                    .filter(item -> LinkBarItemStatus.VISIBLE.toString().equalsIgnoreCase(item.getItemStatus()))
                    .toList();

            if (activeList.isEmpty()) {
                LOGGER.warn("No active link bar data found in database");
                throw new DataNotFoundErrorExceptionHandler("No active link bar data found");
            }

            LOGGER.info("Fetched {} active link bar data successfully", activeList.size());

            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            activeList,
                            Instant.now());

        }catch (DataAccessErrorExceptionHandler daee){
            LOGGER.error("Error occurred while fetching link bar data: {}", daee.getMessage(), daee);
            throw daee;
        } catch (DataNotFoundErrorExceptionHandler dnfe) {
            LOGGER.error("Error occurred while fetching active link bar data:",dnfe);
            throw dnfe;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active link bar data: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch active link bar data from database");
        } finally {
            LOGGER.info("End fetching active link bar data from repository");
        }
    }


}
