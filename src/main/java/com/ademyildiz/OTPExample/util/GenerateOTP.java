package com.ademyildiz.OTPExample.util;

import java.text.DecimalFormat;
import java.util.Random;

public class GenerateOTP {

    public static String generateOTP(){
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }

}
