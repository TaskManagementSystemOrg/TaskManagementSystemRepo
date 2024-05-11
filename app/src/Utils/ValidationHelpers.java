package Utils;

public class ValidationHelpers {
        public static void validateIntRange(int input, int min, int max, String errorMessage) {
                if (input < min || input > max) {
                        throw new IllegalArgumentException(errorMessage);
                }
        }
        public static void validateStringLength(String input, int min, int max, String errorMessage) {
                validateIntRange(input.length(), min, max, errorMessage);
        }
}
