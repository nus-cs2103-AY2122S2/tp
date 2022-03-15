package seedu.address.model.testresult;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a TestResult's test date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTestDate(String)}
 */
public class TestDate {

    public static final String MESSAGE_CONSTRAINTS = "Test dates must be in the form of yyyy-mm-dd";

    public final LocalDate value;

    /**
     * Constructs an {@code TestDate}.
     *
     * @param testDate A valid test date.
     */
    public TestDate(String testDate) {
        requireNonNull(testDate);
        checkArgument(isValidTestDate(testDate), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(testDate);
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidTestDate(String test) {
        try {
            LocalDate.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        } finally {
            return true;
        }
    }

    @Override
    public String toString() {
        return value.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TestDate // instanceof handles nulls
                && value.equals(((TestDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
