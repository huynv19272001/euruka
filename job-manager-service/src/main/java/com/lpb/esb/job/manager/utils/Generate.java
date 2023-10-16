package com.lpb.esb.job.manager.utils;

import java.util.Random;

public class Generate {
    public static String generateMsg() {
        Random randomOtpLength = new Random();
        String msgid = "1234567890";
        char[] otp = new char[8];
        for (int i = 0; i < 8; i++) {
            otp[i] = msgid.charAt(randomOtpLength.nextInt(msgid.length()));
        }
        return new String(otp);
    }
}
