package com.promise.platform.auth.util;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordUtil {

    public static String encrypt(String input) {
        return DigestUtils.sha256Hex(input + DigestUtils.sha256Hex(input));
    }

    public static boolean isPasswordCorrect(String rawPassword, String encryptedPassword) {
        return encryptedPassword.equals(encrypt(rawPassword));
    }

}
