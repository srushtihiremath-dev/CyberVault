package security;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptionService {

    private static final int IV_LENGTH = 12;

    private static final int TAG_LENGTH = 128;

    public static String encrypt(
            String plaintext,
            byte[] key) throws Exception {

        byte[] iv = new byte[IV_LENGTH];

        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");

        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH, iv);

        cipher.init(
                Cipher.ENCRYPT_MODE,
                secretKey,
                spec);

        byte[] encrypted = cipher.doFinal(
                plaintext.getBytes(
                        java.nio.charset.StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(iv)
                + ":"
                + Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(
            String encryptedText,
            byte[] key) throws Exception {

        String[] parts = encryptedText.split(":");

        byte[] iv = Base64.getDecoder().decode(parts[0]);

        byte[] encrypted = Base64.getDecoder().decode(parts[1]);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");

        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH, iv);

        cipher.init(
                Cipher.DECRYPT_MODE,
                secretKey,
                spec);

        byte[] decrypted = cipher.doFinal(encrypted);

        return new String(
                decrypted,
                java.nio.charset.StandardCharsets.UTF_8);

    }
}
