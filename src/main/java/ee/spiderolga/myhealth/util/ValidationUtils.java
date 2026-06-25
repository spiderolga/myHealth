package ee.spiderolga.myhealth.util;

/**
 * Utility class for input validation.
 */
public class ValidationUtils {
    
    /**
     * Validate that a string is not null or empty.
     * 
     * @param value string to validate
     * @param fieldName field name for error message
     * @throws IllegalArgumentException if validation fails
     */
    public static void validateNotEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
    }
    
    /**
     * Validate that a number is positive.
     * 
     * @param value number to validate
     * @param fieldName field name for error message
     * @throws IllegalArgumentException if validation fails
     */
    public static void validatePositive(Double value, String fieldName) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive");
        }
    }
    
    /**
     * Validate that a number is within range.
     * 
     * @param value number to validate
     * @param min minimum value (inclusive)
     * @param max maximum value (inclusive)
     * @param fieldName field name for error message
     * @throws IllegalArgumentException if validation fails
     */
    public static void validateRange(Double value, Double min, Double max, String fieldName) {
        if (value == null || value < min || value > max) {
            throw new IllegalArgumentException(fieldName + " must be between " + min + " and " + max);
        }
    }
}
