package ee.spiderolga.myhealth.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for date and time operations.
 */
public class DateTimeUtils {
    
    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    
    /**
     * Parse ISO date time string.
     * 
     * @param dateTimeString ISO date time string
     * @return LocalDateTime object
     */
    public static LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, ISO_FORMATTER);
    }
    
    /**
     * Format LocalDateTime to ISO string.
     * 
     * @param dateTime LocalDateTime object
     * @return ISO formatted string
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(ISO_FORMATTER);
    }
    
    /**
     * Get current date and time.
     * 
     * @return current LocalDateTime
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
    
    /**
     * Check if date is within range.
     * 
     * @param date date to check
     * @param startDate range start
     * @param endDate range end
     * @return true if date is within range
     */
    public static boolean isWithinRange(LocalDateTime date, LocalDateTime startDate, LocalDateTime endDate) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
