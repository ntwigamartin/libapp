package com.mcmillan.libapp.Utils;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.SecureRandom;

public class HashPassword {

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16]; // 16 bytes for a strong salt
        random.nextBytes(salt);
        return salt;
    }

    public static byte[] generateHash(String password, byte[] salt) {
        int iterations = 10000;
        int keyLength = 256;
        try {
            // Create PBEKeySpec with password, salt, iterations, and key length
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);

            // Use PBKDF2 with SHA-256
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            // Generate the secret key
            SecretKey secretKey = factory.generateSecret(spec);

            // Get the encoded key bytes
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }
}

