package seedu.address.model.charge;

import static java.util.Objects.requireNonNull;

/**
 * Represents the month-year of a Pet to be charged in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ChargeDate {
    public static final String MESSAGE_CONSTRAINTS = "Charge date should be formatted as MM-yyyy!";

    /**
     * Regex to match charge date format.
     * eg: "03-2022".
     */
    public static final String VALIDATION_REGEX = "\\d{2}-\\d{4}\\s*";

    private final int month;
    private final int year;

    /**
     * Constructs an {@code ChargeDate}.
     *
     * @param chargeDate A valid charge date.
     */
    public ChargeDate(String chargeDate) {
        requireNonNull(chargeDate);
        checkArgument(isValidChargeDate(chargeDate), MESSAGE_CONSTRAINTS);
        String[] monthYear = chargeDate.trim().split("-");
        int month = Integer.valueOf(monthYear[0]);
        int year = Integer.valueOf(monthYear[1]);
        this.month = month;
        this.year = year;
    }

    /**
     * Returns the month to charge the pet on
     * @return month as an integer.
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Returns the year to charge the pet on
     * @return year as an integer.
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Returns true if a given string is a valid month
     * @param m The input string.
     * @return True if input matches lies between 0 and 13 exclusive, False otherwise.
     */
    public static boolean isValidMonth(int m) {
        return (m > 0) && (m < 13);
    }

    /**
     * Returns true if a given string is a valid year
     * @param y The input string.
     * @return True if input matches lies between 999 and 10000 exclusive, False otherwise.
     */
    public static boolean isValidYear(int y) {
        return (y > 999) && (y < 10000);
    }

    /**
     * Returns true if a given string is a valid charge date
     * @param date The input string.
     * @return True if input matches the VALIDATION_REGEX, False otherwise.
     */
    public static boolean isValidChargeDate(String date) {
        boolean isValidDate = date.matches(VALIDATION_REGEX);
        // boolean isValidDate = date.matches("\\d{2}-\\d{4}\\s*");
        if (isValidDate) {
            String[] monthYear = date.trim().split("-");
            int m = Integer.valueOf(monthYear[0]);
            int y = Integer.valueOf(monthYear[1]);
            return isValidMonth(m) && isValidYear(y);
        }
        return false;
    }

    /**
     * Checks that {@code condition} is true. Used for validating arguments to methods.
     *
     * @throws IllegalArgumentException with {@code errorMessage} if {@code condition} is false.
     */
    public static void checkArgument(Boolean condition, String errorMessage) {
        if (!condition) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ChargeDate)) {
            return false;
        }

        // state check
        ChargeDate e = (ChargeDate) other;
        return month == e.getMonth()
                && year == e.getYear();
    }
}
