package com.felicita.service.impl;

import com.felicita.exception.*;
import com.felicita.model.enums.VerifiedStatus;
import com.felicita.model.request.*;
import com.felicita.model.response.*;
import com.felicita.repository.AccountSecurityRepository;
import com.felicita.service.AccountSecurityService;
import com.felicita.service.CommonService;
import com.felicita.util.CommonResponseMessages;
import com.felicita.validation.AccountSecurityValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AccountSecurityServiceImpl implements AccountSecurityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountSecurityServiceImpl.class);

    private final AccountSecurityRepository accountSecurityRepository;
    private final CommonService commonService;
    private final AccountSecurityValidationService accountSecurityValidationService;

    @Autowired
    public AccountSecurityServiceImpl(AccountSecurityRepository accountSecurityRepository,
                                      CommonService commonService,
                                      AccountSecurityValidationService accountSecurityValidationService) {
        this.accountSecurityRepository = accountSecurityRepository;
        this.commonService = commonService;
        this.accountSecurityValidationService = accountSecurityValidationService;
    }

    @Override
    public CommonResponse<AccountSecurityDetailsResponse> getAccountSecurityDetailsById() {
        LOGGER.info("Start fetching user account security details from repository");
        try {
            Long userId = commonService.getUserIdBySecurityContext();
            AccountSecurityDetailsResponse accountSecurityDetailsResponse =
                    accountSecurityRepository.getAccountSecurityDetailsById(userId);
            LOGGER.info("Fetched user account security details successfully");
            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            accountSecurityDetailsResponse,
                            Instant.now()
                    )
            );
        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching user account security details: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching user account security details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch user account security details from database");
        } finally {
            LOGGER.info("End fetching user account security details from repository");
        }
    }

    @Override
    public CommonResponse<UpdateResponse> updateMobileVerification(MobileVerificationRequest mobileVerificationRequest) {
        try {
            accountSecurityValidationService.validateMobileVerificationRequest(mobileVerificationRequest);
            Long userId = commonService.getUserIdBySecurityContext();
            VerificationCompareResponse verificationCompareResponse =
                    accountSecurityRepository.getRealMobileVerificationCode
                            (userId,mobileVerificationRequest.getWhichMobile());
            if(!verificationCompareResponse.getStatus().equalsIgnoreCase(VerifiedStatus.PENDING.toString())){
                throw new VerificationFailedErrorExceptionHandle("cant update because its not correct status : "+ verificationCompareResponse.getStatus());
            }
            if (!verificationCompareResponse.getCode().equals(mobileVerificationRequest.getCode())){
                accountSecurityRepository.updateMobileVerification(mobileVerificationRequest,userId, VerifiedStatus.REJECTED.toString());
                throw new VerificationFailedErrorExceptionHandle("varification failed in the update mobile verification request");
            }else{
                accountSecurityRepository.updateMobileVerification(mobileVerificationRequest,userId,VerifiedStatus.VERIFIED.toString());
            }
            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            new UpdateResponse("Successfully update mobile verification request", null),
                            Instant.now()
                    );
        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the update mobile verification request", vfe.getValidationFailedResponses());
        } catch (InsertFailedErrorExceptionHandler ife) {
            throw new InsertFailedErrorExceptionHandler(ife.getMessage());
        } catch (VerificationFailedErrorExceptionHandle vfe){
            throw new VerificationFailedErrorExceptionHandle(vfe.getMessage());
        }catch (Exception e) {
            LOGGER.error(e.toString());
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public CommonResponse<UpdateResponse> updateEmailVerification(EmailVerificationRequest emailVerificationRequest) {
        try {
            accountSecurityValidationService.validateEmailVerificationRequest(emailVerificationRequest);
            Long userId = commonService.getUserIdBySecurityContext();
            VerificationCompareResponse verificationCompareResponse = accountSecurityRepository.getRealEmailVerificationCode(userId,emailVerificationRequest.getWhichEmail());
            if(!verificationCompareResponse.getStatus().equalsIgnoreCase(VerifiedStatus.PENDING.toString())){
                throw new VerificationFailedErrorExceptionHandle("cant update because its not correct status : "+ verificationCompareResponse.getStatus());
            }
            if (!verificationCompareResponse.getCode().equals(emailVerificationRequest.getCode())){
                accountSecurityRepository.updateEmailVerification(emailVerificationRequest,userId, VerifiedStatus.REJECTED.toString());
                throw new VerificationFailedErrorExceptionHandle("varification failed in the update email verification request");
            }else{
                accountSecurityRepository.updateEmailVerification(emailVerificationRequest,userId,VerifiedStatus.VERIFIED.toString());
            }
            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            new UpdateResponse("Successfully update email verification request", null),
                            Instant.now()
                    );
        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the update email verification request", vfe.getValidationFailedResponses());
        } catch (InsertFailedErrorExceptionHandler ife) {
            throw new InsertFailedErrorExceptionHandler(ife.getMessage());
        } catch (VerificationFailedErrorExceptionHandle vfe){
            throw new VerificationFailedErrorExceptionHandle(vfe.getMessage());
        }catch (Exception e) {
            LOGGER.error(e.toString());
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public CommonResponse<UpdateResponse> sendOtpToMobileVerification(MobileVerificationOtpRequest mobileVerificationOtpRequest) {
        try {
            accountSecurityValidationService.validateMobileVerificationOtpRequest(mobileVerificationOtpRequest);
            Long userId = commonService.getUserIdBySecurityContext();
            String randomOtp = commonService.generateRandomOtp();
            accountSecurityRepository.insertRandomOtpToMobileVerification(userId,mobileVerificationOtpRequest.getWhichMobile(),randomOtp);
            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            new UpdateResponse("Successfully request mobile verification", null),
                            Instant.now()
                    );
        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the update mobile otp verification request", vfe.getValidationFailedResponses());
        } catch (InsertFailedErrorExceptionHandler ife) {
            throw new InsertFailedErrorExceptionHandler(ife.getMessage());
        } catch (VerificationFailedErrorExceptionHandle vfe){
            throw new VerificationFailedErrorExceptionHandle(vfe.getMessage());
        }catch (Exception e) {
            LOGGER.error(e.toString());
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public CommonResponse<UpdateResponse> sendOtpToEmailVerification(EmailVerificationOtpRequest emailVerificationOtpRequest) {
        try {
            accountSecurityValidationService.validateEmailVerificationOtpRequest(emailVerificationOtpRequest);
            Long userId = commonService.getUserIdBySecurityContext();
            String randomOtp = commonService.generateRandomOtp();
            accountSecurityRepository.insertRandomOtpToEmailVerification(userId,emailVerificationOtpRequest.getWhichEmail(),randomOtp);
            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            new UpdateResponse("Successfully request email verification", null),
                            Instant.now()
                    );
        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the update email otp verification request", vfe.getValidationFailedResponses());
        } catch (InsertFailedErrorExceptionHandler ife) {
            throw new InsertFailedErrorExceptionHandler(ife.getMessage());
        } catch (VerificationFailedErrorExceptionHandle vfe){
            throw new VerificationFailedErrorExceptionHandle(vfe.getMessage());
        }catch (Exception e) {
            LOGGER.error(e.toString());
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }
}
