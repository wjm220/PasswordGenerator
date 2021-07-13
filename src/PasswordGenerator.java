import java.security.SecureRandom;

public class PasswordGenerator {
    enum Characters {
        LETTERS,
        DIGITS,
        SPECIALS
    }
    private static final SecureRandom rng = new SecureRandom();
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "+-*/|%$@&#!?`'\\\"~=^<>:;,._[]{}()";

    private PasswordGenerator() { }

    public static String generatePassword(int length) {
        // Check the password length - must be less than 128 and greater than 10
        if (length > 128 || length < 10) {
            throw new IllegalArgumentException("Password length must be in range (10 <= length <= 128)");
        }

        String passwordCharacters = LOWERCASE_LETTERS + UPPERCASE_LETTERS + DIGITS + SPECIAL_CHARACTERS;
        StringBuilder password = new StringBuilder();

        while (true) {
            for (int i = 0; i < length; i++) {

                // Generates a random integer in range 0 to passwordCharacters.length()
                int random_index = rng.nextInt(passwordCharacters.length());

                // This number is then used as an index to obtain a character from the passwordCharacters String
                password.append(passwordCharacters.charAt(random_index));
            }

            // Check that the password has been generated with at least one number - if not, retry
            if (password.toString().matches(".*\\d.*")) {
                break;
            }
            password.setLength(0);
        }

        return password.toString();
    }

    public static String generatePassword(int length, Characters c) {
        if (length > 128 || length < 10) {
            throw new IllegalArgumentException("Password length must be in range (10 <= length <= 128)");
        }

        String passwordCharacters;

        if (c == Characters.LETTERS) {
            passwordCharacters = LOWERCASE_LETTERS + UPPERCASE_LETTERS;
        }
        else if (c == Characters.DIGITS) {
            passwordCharacters = DIGITS;
        }
        else if (c == Characters.SPECIALS) {
            passwordCharacters = SPECIAL_CHARACTERS;
        }
        else {
            throw new IllegalArgumentException("""
                    Parameter c must be one of the following:
                    - Characters.LETTERS,
                    - Characters.DIGITS, or
                    - Characters.SPECIALS""");
        }

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = rng.nextInt(passwordCharacters.length());
            password.append(passwordCharacters.charAt(randomIndex));
        }

        return password.toString();
    }

    public static String generatePassword(int length, Characters c1, Characters c2) {
        if (length > 128 || length < 10) {
            throw new IllegalArgumentException("Password length must be in range (10 <= length <= 128)");
        }

        String passwordCharacters;

        if ((c1 == Characters.LETTERS && c2 == Characters.DIGITS)
                || (c1 == Characters.DIGITS && c2 == Characters.LETTERS)) {
            passwordCharacters = LOWERCASE_LETTERS + UPPERCASE_LETTERS + DIGITS;
        }
        else if ((c1 == Characters.LETTERS && c2 == Characters.SPECIALS)
                || (c1 == Characters.SPECIALS && c2 == Characters.LETTERS)) {
            passwordCharacters = LOWERCASE_LETTERS + UPPERCASE_LETTERS + SPECIAL_CHARACTERS;
        }
        else if ((c1 == Characters.DIGITS && c2 == Characters.SPECIALS)
                || (c1 == Characters.SPECIALS && c2 == Characters.DIGITS)) {
            passwordCharacters = DIGITS + SPECIAL_CHARACTERS;
        }
        else {
            throw new IllegalArgumentException("Parameters c1 and c2 must be different values");
        }

        StringBuilder password = new StringBuilder();

        while (true) {
            for (int i  = 0; i < length; i++) {
                int randomIndex = rng.nextInt(passwordCharacters.length());
                password.append(passwordCharacters.charAt(randomIndex));
            }

            if (c1 != Characters.DIGITS && c2 != Characters.DIGITS) {
                break;
            }
            if (password.toString().matches(".*\\d.*")) {
                break;
            }
            password.setLength(0);
        }

        return password.toString();
    }
}
