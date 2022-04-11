package seedu.trackbeau.model.customer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 */
public abstract class Date {
    protected static boolean isSatisfyDateRequirements(String test, DateTimeFormatter formatter) {
        return isNotInFuture(test, formatter);
    }

    static boolean isNotInFuture(String test, DateTimeFormatter formatter) {
        try {
            LocalDate userInputDate = LocalDate.parse(test, formatter);
            LocalDate now = LocalDate.now();
            if (userInputDate.isAfter(now)) { //date cannot be in the future
                return false;
            }
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
