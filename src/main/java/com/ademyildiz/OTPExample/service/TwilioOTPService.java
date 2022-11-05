package com.ademyildiz.OTPExample.service;

import com.ademyildiz.OTPExample.dto.VerificationCodeRequestDto;
import com.ademyildiz.OTPExample.dto.VerificationCodeResponseDto;

public interface TwilioOTPService {
    public VerificationCodeResponseDto sendOTPForPasswordReset(VerificationCodeRequestDto request) throws Exception;
    public String validateOTP(String userInputOTP, String userName);
}
