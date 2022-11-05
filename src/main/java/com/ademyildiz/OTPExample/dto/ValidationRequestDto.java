package com.ademyildiz.OTPExample.dto;

import lombok.Data;

@Data
public class ValidationRequestDto {
    private String userName;
    private String otp;
}
