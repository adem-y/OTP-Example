package com.ademyildiz.OTPExample.dto;

import lombok.Data;

@Data
public class VerificationCodeRequestDto {

    private String phoneNumber;
    private String userName;

}
