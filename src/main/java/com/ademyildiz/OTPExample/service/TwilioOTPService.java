package com.ademyildiz.OTPExample.service;

import com.ademyildiz.OTPExample.config.TwilioConfig;
import com.ademyildiz.OTPExample.dto.OtpStatus;
import com.ademyildiz.OTPExample.dto.PasswordResetRequestDto;
import com.ademyildiz.OTPExample.dto.PasswordResetResponseDto;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class TwilioOTPService {

    @Autowired
    private TwilioConfig twilioConfig;

    Map<String, String> otpMap = new HashMap<>();

    public Mono<PasswordResetResponseDto> sendOTPForPasswordReset(PasswordResetRequestDto request){

        PasswordResetResponseDto response = null;

        try {
            PhoneNumber phoneNumberTo = new PhoneNumber(request.getPhoneNumber());
            PhoneNumber phoneNumberFrom = new PhoneNumber(twilioConfig.getTrialNumber());
            String otp = generateOTP();
            String otpMessage = "Your verification code: " + otp;

            Message message = Message
                    .creator(phoneNumberTo, // to
                            phoneNumberFrom, // from
                            otpMessage)
                    .create();

            otpMap.put(request.getUserName(), otp);
            response = new PasswordResetResponseDto(OtpStatus.DELIVERED, otpMessage);

        } catch (Exception e){
            response = new PasswordResetResponseDto(OtpStatus.DELIVERED, e.getMessage());
        }

        return Mono.just(response);

    }

    public Mono<String> validateOTP(String userInputOTP, String userName){
        if (userInputOTP.equals(otpMap.get(userName))){
            return Mono.just("Verification code is correct.");
        } else {
            return Mono.error(new IllegalArgumentException("Verification code is wrong."));
        }
    }


    private String generateOTP(){
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }

}
