package com.mcmillan.libapp.Utils;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class HashPassword {

    public static String hashPassword(String password) {

        // Generate a random salt
        byte[] salt = generateSalt();

        // Hash the password
        byte[] hashedPassword = hashPassword(password, salt);

        // Store the hashed password and the salt in the database
        // Make sure to store the salt along with the hashed password
        System.out.println("Hashed Password: " + Base64.getEncoder().encodeToString(hashedPassword));
        System.out.println("Salt: " + Base64.getEncoder().encodeToString(salt));
    }

    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16]; // 16 bytes for a strong salt
        random.nextBytes(salt);
        return salt;
    }

    private static byte[] hashPassword(String password, byte[] salt) {
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

