package com.marych.insuranceApp.service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class HashPasswordService {


    public HashPasswordService() {
    }


    public  boolean validatePassword(String originalPassword, String storedPassword)
    {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);

        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(),
                salt, iterations, hash.length * 8);
        SecretKeyFactory skf = null;
        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        byte[] testHash = new byte[0];
        try {
            assert skf != null;
            testHash = skf.generateSecret(spec).getEncoded();
        }catch (InvalidKeySpecException e){
            e.printStackTrace();
        }

        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }
    private byte[] fromHex(String hex)
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i < bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    public String generatePasswordHash(String password) {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();
        PBEKeySpec spec = null;
        SecretKeyFactory skf = null;
        byte[] hash = new byte[0];
        try
        {
            spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        try
        {
            assert skf != null;
            hash = skf.generateSecret(spec).getEncoded();

        }catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private byte[] getSalt() {
        SecureRandom sr;
        byte[] salt = new byte[0];
        try {
            sr = SecureRandom.getInstance("SHA1PRNG");
            salt = new byte[16];
            sr.nextBytes(salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return salt;
    }

    private String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }
}
