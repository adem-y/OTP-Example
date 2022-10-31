package com.ademyildiz.OTPExample.dto;

import lombok.Data;

@Data
public class PasswordResetRequestDto {

    private String phoneNumber;
    private String userName;
    private String otp;

}
