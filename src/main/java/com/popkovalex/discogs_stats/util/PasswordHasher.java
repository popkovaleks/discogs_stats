package com.popkovalex.discogs_stats.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHasher {

    public static String hashPassword (String password, String salt) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            String combined = password + salt;
            byte[] hashedBytes = digest.digest(combined.getBytes(StandardCharsets.UTF_8));

            return toHex(hashedBytes);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public static String generateSalt(){
        byte[] arr = new byte[16];
        new SecureRandom().nextBytes(arr);
        return Base64.getEncoder().encodeToString(arr);
    }

    public static String toHex(byte[] bytes){
        StringBuilder result = new StringBuilder();
        for (byte b : bytes){
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}