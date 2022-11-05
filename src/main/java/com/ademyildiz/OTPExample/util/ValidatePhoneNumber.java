package com.ademyildiz.OTPExample.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatePhoneNumber {
    public static void validatePhoneNumber(String phoneNumber) throws Exception {
        Pattern pattern = Pattern.compile("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if(!matcher.matches()){
            throw new Exception("Your phone number is not valid!");
        }
    }
}
