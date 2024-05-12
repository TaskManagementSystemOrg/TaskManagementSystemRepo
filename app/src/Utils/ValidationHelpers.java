package Utils;

import java.util.List;

public class ValidationHelpers {
        public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. Expected: %d, Received: %d";

        public static void validateIntRange(int input, int min, int max, String errorMessage) {
                if (input < min || input > max) {
                        throw new IllegalArgumentException(errorMessage);
                }
        }
        public static void validateStringLength(String input, int min, int max, String errorMessage) {
                validateIntRange(input.length(), min, max, errorMessage);
        }
        public static void validateArgumentsCount(List<String> list, int expectedArgumentsCount) {
                if (list.size() < expectedArgumentsCount || list.size() > expectedArgumentsCount) {
                        throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_ARGUMENTS, expectedArgumentsCount, list.size()));
                }
        }
}
