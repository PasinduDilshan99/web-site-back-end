package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.enums.PartnerStatus;
import com.felicita.model.response.BlogResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.repository.BlogRepository;
import com.felicita.service.BlogService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogServiceImpl.class);

    private final BlogRepository blogRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public ResponseEntity<CommonResponse<List<BlogResponse>>> getAllBlogs() {
        LOGGER.info("Start fetching all blogs from repository");
        try {
            List<BlogResponse> blogResponses = blogRepository.getAllBlogs();

            if (blogResponses.isEmpty()) {
                LOGGER.warn("No blogs found in database");
                throw new DataNotFoundErrorExceptionHandler("No blogs found");
            }

            LOGGER.info("Fetched {} blogs successfully", blogResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            blogResponses,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching blogs: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching blogs: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch blogs from database");
        } finally {
            LOGGER.info("End fetching all blogs from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<BlogResponse>>> getAllActiveBlogs() {
        LOGGER.info("Start fetching all visible blogs from repository");

        try {
            List<BlogResponse> blogResponses = blogRepository.getAllBlogs();

            if (blogResponses.isEmpty()) {
                LOGGER.warn("No blogs found in database");
                throw new DataNotFoundErrorExceptionHandler("No blogs found");
            }

            List<BlogResponse> blogResponseList = blogResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatus()))
                    .toList();

            if (blogResponseList.isEmpty()) {
                LOGGER.warn("No visible blogs found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible blogs found");
            }

            LOGGER.info("Fetched {} visible blogs successfully", blogResponseList.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            blogResponseList,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching visible blogs: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching visible blogs: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch blogs from database");
        } finally {
            LOGGER.info("End fetching all visible blogs from repository");
        }
    }
}
