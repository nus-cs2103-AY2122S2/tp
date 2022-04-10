package seedu.ibook.model.item;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Locale;

/**
 * Represents a Product's expiry date in the ibook.
 * Guarantees: immutable;
 */
public class ExpiryDate implements Comparable<ExpiryDate> {

    public static final ExpiryDate WILD_EXPIRY_DATE = new ExpiryDate() {
        @Override
        public boolean equals(Object other) {
            return other instanceof ExpiryDate;
        }
    };

    public static final String MESSAGE_CONSTRAINTS =
            "Expiry dates should be valid and have format such as 03 Apr 2022, 3 Apr 2022 or 2022-04-03";

    public static final String DAYS_CONSTRAINTS =
            "Number of days should be non-negative.";

    private static final DateStringManager[] ACCEPTED_FORMATS = {
        new DateStringManager("d MMM yyyy"),
        new DateStringManager("dd MMM yyyy"),
        new DateStringManager("yyyy-MM-dd")
    };

    private static final DateStringManager DEFAULT_DATE_MANAGER = ACCEPTED_FORMATS[0];

    public final LocalDate expiryDate;
    private final DateStringManager dateStringManager;

    private ExpiryDate() {
        expiryDate = LocalDate.parse("0000-01-01");
        dateStringManager = null;
    }

    /**
     * Constructs a {@code ExpiryDate}.
     *
     * @param date A valid date.
     */
    public ExpiryDate(String date) {
        requireNonNull(date);
        checkArgument(isValidExpiryDate(date), MESSAGE_CONSTRAINTS);

        dateStringManager = findDateStringManager(date);
        expiryDate = dateStringManager.parse(date);
    }

    /**
     * Creates an expiryDate {@code days} days from the current day.
     *
     * @param days The number of days.
     * @return An expiryDate exactly {@code days} day from now.
     */
    public static ExpiryDate getDateFromNow(int days) {
        checkArgument(days >= 0, DAYS_CONSTRAINTS);
        LocalDate dateNow = LocalDate.now();
        return new ExpiryDate(dateNow.plusDays(days).toString());
    }

    /**
     * Checks if a given {@code LocalDate} is valid as per {@code VALIDATION_REGEX}.
     *
     * @param expiryDate Date to test.
     * @return Result of test.
     */
    public static boolean isValidExpiryDate(String expiryDate) {
        return Arrays.stream(ACCEPTED_FORMATS)
                .anyMatch(manager -> manager.matches(expiryDate));
    }

    public boolean isPast() {
        return expiryDate.isBefore(LocalDate.now());
    }

    public long dayDifference(LocalDate other) {
        return DAYS.between(other, expiryDate);
    }

    /**
     * Finds first {@code DateStringManager} object that matches the given date string.
     *
     * @param date Date string
     * @return First {@code DateStringManager} in {@code ACCEPTED_FORMATS} that matches {@code date}.
     */
    private DateStringManager findDateStringManager(String date) {
        return Arrays.stream(ACCEPTED_FORMATS)
                .filter(manager -> manager.matches(date))
                .findFirst()
                .get();
    }

    @Override
    public String toString() {
        return DEFAULT_DATE_MANAGER.parseString(expiryDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpiryDate // instanceof handles nulls
                && expiryDate.equals(((ExpiryDate) other).expiryDate)); // state check
    }

    @Override
    public int hashCode() {
        return expiryDate.hashCode();
    }

    @Override
    public int compareTo(ExpiryDate o) {
        return expiryDate.compareTo(o.expiryDate);
    }

    /**
     * Checks if the current expiryDate is within the currentDate and {@code toCheck}
     * including those two dates
     *
     * @param toCheck The later date.
     * @return A boolean indicating whether the current expiryDate is within the 2 dates.
     */
    public boolean within(ExpiryDate toCheck) {
        return !LocalDate.now().isAfter(this.expiryDate) && !toCheck.expiryDate.isBefore(this.expiryDate);
    }

    /**
     * Class that encapsulates date string pattern matching and parsing
     */
    private static class DateStringManager {

        // Default use of English locality for dates
        private final Locale locale = new Locale("en");

        private final DateTimeFormatter dateFormatter;

        public DateStringManager(String format) {
            dateFormatter = DateTimeFormatter.ofPattern(format, locale);
        }

        public boolean matches(String test) {
            try {
                LocalDate parsedDate = LocalDate.parse(test, dateFormatter);

                // Java truncates the date into appropriate ranges according to the year and month
                return parsedDate.format(dateFormatter).equals(test);
            } catch (DateTimeParseException pe) {
                return false;
            }
        }

        public LocalDate parse(String date) {
            return LocalDate.parse(date, dateFormatter);
        }

        public String parseString(LocalDate date) {
            return date.format(dateFormatter);
        }

    }

}
