package seedu.address.model.charge;

import static java.util.Objects.requireNonNull;

import seedu.address.model.pet.Appointment;

/**
 * Represents the daily amount chargeable of a Pet in the address book.
 * Guarantees: immutable; is always valid.
 */
public class Charge {
    /**
     * Regex to match charge format.
     * eg: "$80.40".
     */
    public static final String VALIDATION_REGEX =
            "^\\$(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d\\d)?$";

    private static String perDayCharge;

    /**
     * Constructs an {@code Charge}.
     *
     * @param charge A valid per day charge.
     */
    public Charge(String charge) {
        requireNonNull(charge);
        this.perDayCharge = charge;
    }

    /**
     * Returns the total amount chargeable of a Pet based on the given number of days
     * @param days The number of days to charge a Pet for.
     * @return total amount to charge the Pet
     */
    public static Double computeCharge(Integer days) {
        return days * perDayCharge();
    }

    /**
     * Sets the value of a valid charge per day
     */
    public static void setPerDayCharge(String charge) {
        perDayCharge = charge;
    }

    /**
     * Returns the value of a valid charge per day
     * @return perDayValue as an integer.
     */
    public static Double perDayCharge() {
        String value = perDayCharge.replace("$", "");
        return Double.parseDouble(value);
    }

    /**
     * Returns true if a given string is a valid charge
     * @param test The input string.
     * @return True if input matches VALIDATION_REGEX, False otherwise.
     */
    public static boolean isValidCharge(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return perDayCharge;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Appointment
                && perDayCharge.equals(((Appointment) other).value));
    }

    @Override
    public int hashCode() {
        return perDayCharge.hashCode();
    }

}
