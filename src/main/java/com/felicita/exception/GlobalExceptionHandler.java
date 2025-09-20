package com.felicita.exception;

import com.felicita.model.response.CommonResponse;
import com.felicita.util.CommonResponseMessages;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DataNotFoundErrorExceptionHandler.class)
    public ResponseEntity<CommonResponse<String>> handleDataNotFoundException(DataNotFoundErrorExceptionHandler e) {
        logger.error("{} Data Not Found Exception: {} {}", Constant.ERROR_DOTS_START, e.getMessage(), Constant.ERROR_DOTS_END);

        CommonResponse<String> response = new CommonResponse<>(
                CommonResponseMessages.DATA_NOT_FOUND_CODE,
                CommonResponseMessages.DATA_NOT_FOUND_STATUS,
                CommonResponseMessages.DATA_NOT_FOUND_MESSAGE,
                e.getMessage(),
                Instant.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(InternalServerErrorExceptionHandler.class)
    public ResponseEntity<CommonResponse<String>> handleInternalServerException(InternalServerErrorExceptionHandler e) {
        logger.error("{} Internal Server Error: {} {}", Constant.ERROR_DOTS_START, e.getMessage(), Constant.ERROR_DOTS_END);

        CommonResponse<String> response = new CommonResponse<>(
                CommonResponseMessages.INTERNAL_SERVER_ERROR_CODE,
                CommonResponseMessages.INTERNAL_SERVER_ERROR_STATUS,
                CommonResponseMessages.INTERNAL_SERVER_ERROR_MESSAGE,
                e.getMessage(),
                Instant.now()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(DataAccessErrorExceptionHandler.class)
    public ResponseEntity<CommonResponse<String>> handleDataAccessException(DataAccessErrorExceptionHandler e) {
        logger.error("{} Database Access Error: {} {}", Constant.ERROR_DOTS_START, e.getMessage(), Constant.ERROR_DOTS_END);

        CommonResponse<String> response = new CommonResponse<>(
                CommonResponseMessages.DATA_ACCESS_ERROR_CODE,
                CommonResponseMessages.DATA_ACCESS_ERROR_STATUS,
                CommonResponseMessages.DATA_ACCESS_ERROR_MESSAGE,
                e.getMessage(),
                Instant.now()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(UpdateFailedErrorExceptionHandler.class)
    public ResponseEntity<CommonResponse<String>> handleUpdateFailedErrorException(UpdateFailedErrorExceptionHandler e) {
        logger.error("{} Update Failed Error Exception: {} {}", Constant.ERROR_DOTS_START, e.getMessage(), Constant.ERROR_DOTS_END);

        CommonResponse<String> response = new CommonResponse<>(
                CommonResponseMessages.BAD_REQUEST_CODE,
                CommonResponseMessages.BAD_REQUEST_STATUS,
                CommonResponseMessages.BAD_REQUEST_MESSAGES,
                e.getMessage(),
                Instant.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
