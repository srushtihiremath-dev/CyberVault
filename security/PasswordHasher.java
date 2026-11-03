package security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHasher {

    private static final int SALT_LENGTH = 16;

    private static final int ITERATIONS = 210_000;

    private static final int KEY_LENGTH = 256;

    public static String createHash(char[] password)
    throws Exception {

        SecureRandom random = new SecureRandom();

        byte[] salt = new byte[SALT_LENGTH];

        random.nextBytes(salt);

        byte[] hash = derive(password, salt);

        return Base64.getEncoder().encodeToString(salt)
        + ":" 
        + Base64.getEncoder().encodeToString(hash);
        
    }

    private static byte[] derive(
            char[] password,
            byte[] salt) throws Exception {

        PBEKeySpec spec = new PBEKeySpec(
                password,
                salt,
                ITERATIONS,
                KEY_LENGTH);

        SecretKeyFactory factory = SecretKeyFactory.getInstance(
                "PBKDF2WithHmacSHA256");

        byte[] result = factory.generateSecret(spec)
                .getEncoded();

        spec.clearPassword();

        return result;
    }

}

