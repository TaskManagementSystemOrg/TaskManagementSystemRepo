package Utils;

public class ValidationHelpers {
        public static void ValidateIntRange(int input, int min, int max, String errorMessage) {
                if (input < min || input > max) {
                        throw new IllegalArgumentException(errorMessage);
                }
        }
        public static void ValidateStringLength(String input, int min, int max, String errorMessage) {
                ValidateIntRange(input.length(), min, max, errorMessage);
        }
}
