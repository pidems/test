package com.example.frauddetection.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.time.Instant;

public class FraudDetectionUtil {

    public static Instant parseDate(String date) {
        return Instant.parse(date);
    }

    public static String md5hash(String input) {
        return DigestUtils.md5Hex(input);
    }
}
