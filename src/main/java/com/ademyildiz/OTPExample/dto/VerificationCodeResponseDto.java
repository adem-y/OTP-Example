package com.ademyildiz.OTPExample.dto;

import com.ademyildiz.OTPExample.constants.OtpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationCodeResponseDto {

    private OtpStatus otpStatus;
    private String message;

}
