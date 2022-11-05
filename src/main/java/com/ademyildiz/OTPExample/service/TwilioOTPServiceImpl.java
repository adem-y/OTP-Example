package com.ademyildiz.OTPExample.service;

import com.ademyildiz.OTPExample.config.TwilioConfig;
import com.ademyildiz.OTPExample.constants.OtpStatus;
import com.ademyildiz.OTPExample.dto.VerificationCodeRequestDto;
import com.ademyildiz.OTPExample.dto.VerificationCodeResponseDto;
import com.ademyildiz.OTPExample.util.GenerateOTP;
import com.ademyildiz.OTPExample.util.ValidatePhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TwilioOTPServiceImpl implements TwilioOTPService {

    @Autowired
    private TwilioConfig twilioConfig;

    Map<String, String> otpMap = new HashMap<>();

    public VerificationCodeResponseDto sendOTPForPasswordReset(VerificationCodeRequestDto request) throws Exception {
        log.info("Preparing to generate an otp...");
        VerificationCodeResponseDto response = null;

        try {
            PhoneNumber phoneNumberTo = new PhoneNumber(request.getPhoneNumber());
            ValidatePhoneNumber.validatePhoneNumber(String.valueOf(phoneNumberTo));
            PhoneNumber phoneNumberFrom = new PhoneNumber(twilioConfig.getTrialNumber());

            String otp = GenerateOTP.generateOTP();
            String otpMessage = "Your verification code: " + otp;

            Message message = Message
                    .creator(phoneNumberTo, // to
                            phoneNumberFrom, // from
                            otpMessage)
                    .create();

            otpMap.put(request.getUserName(), otp);
            response = new VerificationCodeResponseDto(OtpStatus.DELIVERED, otpMessage);
            log.info("OTP code is generated successfully!");
        } catch (Exception e){
            log.error("OTP code generation is failed!");
            response = new VerificationCodeResponseDto(OtpStatus.FAILED, e.getMessage());
        }

        return response;

    }

    public String validateOTP(String userInputOTP, String userName){
        if (userInputOTP.equals(otpMap.get(userName))){
            log.info("OTP validation is successful!");
            return "Verification code is correct.";
        } else {
            log.error("OTP validation failed!");
            throw new IllegalArgumentException("Verification code is wrong.");
        }
    }


}
