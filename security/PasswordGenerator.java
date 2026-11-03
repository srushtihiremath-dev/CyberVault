package security;
import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String LOWER = 
    "abcdefghijklmnopqrstuvwxyz";

    private static final String UPPER = 
    "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String NUMBERS = 
    "0123456789";

    private static final String SYMBOLS = 
    "!@#$%^&*()-_=+";

    private static final String ALL = 
    LOWER + UPPER + NUMBERS + SYMBOLS;

    private final SecureRandom random = new SecureRandom();
    
    public String generate(int length) {

        if (length < 12) {
            throw new IllegalArgumentException(
                "Password length must be at least 12.");
        }

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALL.length());
            password.append(ALL.charAt(index));
        }

        return password.toString();
    }

    public static String generatePassword() {
        return new PasswordGenerator().generate(16);
    }
}
