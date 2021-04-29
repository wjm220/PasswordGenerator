import java.security.SecureRandom;
import java.util.stream.IntStream;

public class PasswordGenerator {
    private static final SecureRandom rng = new SecureRandom();
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "+-*/|%$@&#!?`'\\\"~=^<>:;,._[]{}()";

    private PasswordGenerator() { }

    public static String generatePassword(int length, boolean includeLetters, boolean includeDigits,
                                          boolean includeSpecialCharacters) {
        String passwordCharacters;
        if  (includeLetters & !includeDigits & !includeSpecialCharacters) {
            passwordCharacters = UPPERCASE_LETTERS + LOWERCASE_LETTERS;
        }
        else if (includeLetters & includeDigits & !includeSpecialCharacters) {
            passwordCharacters = UPPERCASE_LETTERS + LOWERCASE_LETTERS + DIGITS;
        }
        else if (includeLetters & !includeDigits & includeSpecialCharacters) {
            passwordCharacters = UPPERCASE_LETTERS + LOWERCASE_LETTERS + SPECIAL_CHARACTERS;
        }
        else if (!includeLetters & includeDigits & !includeSpecialCharacters) {
            passwordCharacters = DIGITS;
        }
        else if (!includeLetters & includeDigits & includeSpecialCharacters) {
            passwordCharacters = DIGITS + SPECIAL_CHARACTERS;
        }
        else if (!includeLetters & !includeDigits & includeSpecialCharacters) {
            passwordCharacters = SPECIAL_CHARACTERS;
        }
        else if (includeLetters & includeDigits & includeSpecialCharacters) {
            passwordCharacters = UPPERCASE_LETTERS + LOWERCASE_LETTERS + DIGITS + SPECIAL_CHARACTERS;
        }
        else {
            throw new IllegalArgumentException("""
                    A password must be contain at least one of the following character types:
                    - Letters
                    - Digits
                    - Special Characters""");
        }

        StringBuilder password = new StringBuilder();

        while (true) {
            for (int i = 0; i < length; i++) {
                int random_index = rng.nextInt(passwordCharacters.length());
                password.append(passwordCharacters.charAt(random_index));
            }
            if (!includeDigits) {
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
