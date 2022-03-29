package seedu.address.model.charge;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ChargeDate {
    public static final String MESSAGE_CONSTRAINTS = "Charge date should be formatted as MM-yyyy!";
    private int month;
    private int year;

    public ChargeDate(String chargeDate) {
        requireNonNull(chargeDate);
        checkArgument(isValidChargeDate(chargeDate), MESSAGE_CONSTRAINTS);
        String[] monthYear = chargeDate.split("-");
        int month = Integer.valueOf(monthYear[0]);
        int year = Integer.valueOf(monthYear[1]);
        this.month = month;
        this.year = year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    public static boolean isValidMonth(int m) {
        return (m > 0) && (m < 13);
    }

    public static boolean isValidYear(int y) {
        return (y > 999) && (y < 10000);
    }

    public static boolean isValidChargeDate(String date) {
        boolean isValidDate = date.matches("\\d{2}-\\d{4}");
        if (isValidDate) {
            String[] monthYear = date.split("-");
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
}
