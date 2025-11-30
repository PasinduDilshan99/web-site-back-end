package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InsertFailedErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.request.EmailVerificationRequest;
import com.felicita.model.request.MobileVerificationRequest;
import com.felicita.model.request.VerificationCompareOtpRequest;
import com.felicita.model.response.AccountSecurityDetailsResponse;
import com.felicita.model.response.VerificationCompareOtpResponse;
import com.felicita.model.response.VerificationCompareResponse;
import com.felicita.queries.AccountSecurityQueries;
import com.felicita.repository.AccountSecurityRepository;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AccountSecurityRepositoryImpl implements AccountSecurityRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountSecurityRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountSecurityRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public AccountSecurityDetailsResponse getAccountSecurityDetailsById(Long userId) {
        try {
            LOGGER.info("Fetching user account security details for userId: {}", userId);
            String GET_USER_INFO = "SELECT username, email, mobile_number1, mobile_number2 FROM user WHERE user_id = ?";
            Map<String, Object> userMap = jdbcTemplate.queryForMap(GET_USER_INFO, userId);
            String GET_EMAIL_VERIFICATIONS = """
            SELECT 
                ev.which_email,
                ev.created_at,
                ev.updated_at,
                vs.name AS status_name,
                vs.description AS status_description
            FROM email_verified ev
            LEFT JOIN verified_status vs ON ev.status_id = vs.verified_status_id
            WHERE ev.user_id = ?
        """;

            List<AccountSecurityDetailsResponse.EmailVerificationDetails> emailVerifications =
                    jdbcTemplate.query(GET_EMAIL_VERIFICATIONS, new Object[]{userId},
                            (rs, rowNum) -> AccountSecurityDetailsResponse.EmailVerificationDetails.builder()
                                    .whichEmail(rs.getString("which_email"))
                                    .createdAt(rs.getTimestamp("created_at") != null
                                            ? rs.getTimestamp("created_at").toLocalDateTime() : null)
                                    .updatedAt(rs.getTimestamp("updated_at") != null
                                            ? rs.getTimestamp("updated_at").toLocalDateTime() : null)
                                    .statusName(rs.getString("status_name"))
                                    .statusDescription(rs.getString("status_description"))
                                    .build());

            String GET_MOBILE_VERIFICATIONS = """
            SELECT 
                mv.which_mobile,
                mv.created_at,
                mv.updated_at,
                vs.name AS status_name,
                vs.description AS status_description
            FROM mobile_verified mv
            LEFT JOIN verified_status vs ON mv.status_id = vs.verified_status_id
            WHERE mv.user_id = ?
        """;

            List<AccountSecurityDetailsResponse.MobileVerificationDetails> mobileVerifications =
                    jdbcTemplate.query(GET_MOBILE_VERIFICATIONS, new Object[]{userId},
                            (rs, rowNum) -> AccountSecurityDetailsResponse.MobileVerificationDetails.builder()
                                    .whichMobile(rs.getString("which_mobile"))
                                    .createdAt(rs.getTimestamp("created_at") != null
                                            ? rs.getTimestamp("created_at").toLocalDateTime() : null)
                                    .updatedAt(rs.getTimestamp("updated_at") != null
                                            ? rs.getTimestamp("updated_at").toLocalDateTime() : null)
                                    .statusName(rs.getString("status_name"))
                                    .statusDescription(rs.getString("status_description"))
                                    .build());

            return AccountSecurityDetailsResponse.builder()
                    .username((String) userMap.get("username"))
                    .email((String) userMap.get("email"))
                    .mobileNumber1((String) userMap.get("mobile_number1"))
                    .mobileNumber2((String) userMap.get("mobile_number2"))
                    .emailVerifications(emailVerifications)
                    .mobileVerifications(mobileVerifications)
                    .build();

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching user account security details: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch user account security from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching user account security details: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching user account security");
        }
    }


    @Override
    public VerificationCompareResponse getRealMobileVerificationCode(Long userId,String whichMobile) {
        String GET_REAL_MOBILE_VERIFICATION_CODE = AccountSecurityQueries.GET_REAL_MOBILE_VERIFICATION_CODE;
        try {
            LOGGER.info("Executing query to fetch real mobile verification code for userId: {}", userId);
            return jdbcTemplate.queryForObject(
                    GET_REAL_MOBILE_VERIFICATION_CODE,
                    new Object[]{userId,whichMobile},
                    (rs,rowCount) -> VerificationCompareResponse.builder()
                            .code(rs.getString("send_code"))
                            .status(rs.getString("name"))
                            .build()
            );

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching real mobile verification code: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch mobile verification code from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching real mobile verification code: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching mobile verification code");
        }
    }



    @Override
    public void updateMobileVerification(MobileVerificationRequest mobileVerificationRequest, Long userId, String verifiedStatus) {
        String sql = AccountSecurityQueries.UPDATE_MOBILE_VERIFICATION;

        try {
            int rowsAffected = jdbcTemplate.update(
                    sql,
                    verifiedStatus,
                    userId,
                    mobileVerificationRequest.getWhichMobile()
            );

            if (rowsAffected == 0) {
                throw new InsertFailedErrorExceptionHandler("No rows affected when updating mobile verification for userId: " + userId);
            }

            LOGGER.info("Updated mobile verification status '{}' for userId {} successfully.", verifiedStatus, userId);

        } catch (InsertFailedErrorExceptionHandler e) {
            LOGGER.error("Failed to update mobile verification for userId {}: {}", userId, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            LOGGER.error("Unexpected error while updating mobile verification for userId {}: {}", userId, e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Something went wrong while updating mobile verification");
        }
    }

    @Override
    public VerificationCompareResponse getRealEmailVerificationCode(Long userId,String whichEmail) {
        String GET_REAL_EMAIL_VERIFICATION_CODE = AccountSecurityQueries.GET_REAL_EMAIL_VERIFICATION_CODE;
        try {
            LOGGER.info("Executing query to fetch real email verification code for userId: {}", userId);
            return jdbcTemplate.queryForObject(
                    GET_REAL_EMAIL_VERIFICATION_CODE,
                    new Object[]{userId,whichEmail},
                    (rs,rowCount) -> VerificationCompareResponse.builder()
                            .code(rs.getString("send_code"))
                            .status(rs.getString("name"))
                            .build()
            );

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching real email verification code: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch email verification code from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching real email verification code: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching email verification code");
        }
    }

    @Override
    public void updateEmailVerification(EmailVerificationRequest emailVerificationRequest, Long userId, String verifiedStatus) {
        String sql = AccountSecurityQueries.UPDATE_EMAIL_VERIFICATION;

        try {
            int rowsAffected = jdbcTemplate.update(
                    sql,
                    verifiedStatus,
                    userId,
                    emailVerificationRequest.getWhichEmail()
            );

            if (rowsAffected == 0) {
                throw new InsertFailedErrorExceptionHandler("No rows affected when updating email verification for userId: " + userId);
            }

            LOGGER.info("Updated email verification status '{}' for userId {} successfully.", verifiedStatus, userId);

        } catch (InsertFailedErrorExceptionHandler e) {
            LOGGER.error("Failed to update email verification for userId {}: {}", userId, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            LOGGER.error("Unexpected error while updating email verification for userId {}: {}", userId, e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Something went wrong while updating email verification");
        }
    }

    @Override
    public VerificationCompareOtpResponse getMobileVerificationCompareOtpResponse(VerificationCompareOtpRequest req) {
        try {
            String selectedQuery;

            if (Constant.PRIMARY.equalsIgnoreCase(req.getWhichParameter())) {
                selectedQuery = AccountSecurityQueries.GET_VERIFICATION_COMPARE_OTP_PRIMARY;
            } else if (Constant.SECONDARY.equalsIgnoreCase(req.getWhichParameter())) {
                selectedQuery = AccountSecurityQueries.GET_VERIFICATION_COMPARE_OTP_SECONDARY;
            } else {
                throw new IllegalArgumentException("Invalid mobile selection. Allowed: primary | secondary");
            }

            LOGGER.info("Fetching mobile verification info for userId: {}, mobile: {}",
                    req.getUserId(), req.getWhichParameter());

            return jdbcTemplate.queryForObject(
                    selectedQuery,
                    new Object[]{req.getUserId()},
                    (rs, rowNum) -> VerificationCompareOtpResponse.builder()
                            .parameter(rs.getString("MOBILE_NUMBER"))
                            .currentStatus(rs.getString("CURRENT_STATUS"))
                            .build()
            );

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching mobile compare info: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch mobile compare info from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching mobile compare info: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching mobile compare info");
        }
    }

    @Override
    public void insertRandomOtpToMobileVerification(Long userId, String whichMobile, String randomOtp) {
        String sql = AccountSecurityQueries.UPDATE_MOBILE_NUMBER_RANDOM_OTP;
        try {
            int rowsAffected = jdbcTemplate.update(
                    sql,
                    randomOtp,     // send_code = ?
                    userId,        // user_id = ?
                    whichMobile    // which_mobile = ?
            );

            if (rowsAffected == 0) {
                throw new InsertFailedErrorExceptionHandler(
                        "No rows affected while updating mobile verification for userId: " + userId
                );
            }

            LOGGER.info("Inserted random OTP for userId {} and mobile {} successfully.", userId, whichMobile);

        } catch (InsertFailedErrorExceptionHandler e) {
            LOGGER.error("Failed to insert random OTP for userId {}: {}", userId, e.getMessage(), e);
            throw e;

        } catch (Exception e) {
            LOGGER.error("Unexpected error while inserting random OTP for userId {}: {}", userId, e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Something went wrong while updating mobile verification");
        }
    }

    @Override
    public VerificationCompareOtpResponse getEmailVerificationCompareOtpResponse(VerificationCompareOtpRequest req) {
        try {
            String selectedQuery;
            if (Constant.PRIMARY.equalsIgnoreCase(req.getWhichParameter())) {
                selectedQuery = AccountSecurityQueries.GET_EMAIL_VERIFICATION_COMPARE_OTP_PRIMARY;
            } else if (Constant.SECONDARY.equalsIgnoreCase(req.getWhichParameter())) {
                selectedQuery = AccountSecurityQueries.GET_EMAIL_VERIFICATION_COMPARE_OTP_SECONDARY;
            } else {
                throw new IllegalArgumentException("Invalid mobile selection. Allowed: primary | secondary");
            }

            LOGGER.info("Fetching email verification info for userId: {}, mobile: {}",
                    req.getUserId(), req.getWhichParameter());

            return jdbcTemplate.queryForObject(
                    selectedQuery,
                    new Object[]{req.getUserId()},
                    (rs, rowNum) -> VerificationCompareOtpResponse.builder()
                            .parameter(rs.getString("EMAIL"))
                            .currentStatus(rs.getString("CURRENT_STATUS"))
                            .build()
            );

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching email compare info: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch email compare info from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching email compare info: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching email compare info");
        }
    }

    @Override
    public void insertRandomOtpToEmailVerification(Long userId, String whichEmail, String randomOtp) {
        String sql = AccountSecurityQueries.UPDATE_EMAIL_RANDOM_OTP;
        try {
            int rowsAffected = jdbcTemplate.update(
                    sql,
                    randomOtp,     // send_code = ?
                    userId,        // user_id = ?
                    whichEmail    // which_mobile = ?
            );

            if (rowsAffected == 0) {
                throw new InsertFailedErrorExceptionHandler(
                        "No rows affected while updating email verification for userId: " + userId
                );
            }

            LOGGER.info("Inserted random OTP for userId {} and email {} successfully.", userId, whichEmail);

        } catch (InsertFailedErrorExceptionHandler e) {
            LOGGER.error("Failed to insert random OTP for userId {}: {}", userId, e.getMessage(), e);
            throw e;

        } catch (Exception e) {
            LOGGER.error("Unexpected error while inserting random OTP for userId {}: {}", userId, e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Something went wrong while updating email verification");
        }
    }


}
