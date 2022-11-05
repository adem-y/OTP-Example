package com.ademyildiz.OTPExample.controller;

import com.ademyildiz.OTPExample.dto.ValidationRequestDto;
import com.ademyildiz.OTPExample.dto.VerificationCodeRequestDto;
import com.ademyildiz.OTPExample.dto.VerificationCodeResponseDto;
import com.ademyildiz.OTPExample.service.TwilioOTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("otp/")
public class OTPController {

    @Autowired
    TwilioOTPService twilioOTPService;

    @PostMapping("generate")
    public ResponseEntity<VerificationCodeResponseDto> sendOTP(
            @RequestBody VerificationCodeRequestDto verificationCodeRequestDto) throws Exception {
        return new ResponseEntity<VerificationCodeResponseDto>
                (twilioOTPService.sendOTPForPasswordReset(verificationCodeRequestDto), HttpStatus.OK);
    }

    @PostMapping("validate")
    public ResponseEntity<String> validateOTP(
            @RequestBody ValidationRequestDto validationRequestDto){
        return new ResponseEntity<String>
                (twilioOTPService.validateOTP(validationRequestDto.getOtp(), validationRequestDto.getUserName()),
                        HttpStatus.OK);
    }
}
