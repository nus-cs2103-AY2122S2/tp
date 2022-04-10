package woofareyou.model.charge;

import static java.util.Objects.requireNonNull;

/**
 * Represents the daily amount chargeable of a Pet in the pet book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Charge {
    public static final String MESSAGE_INVALID_CHARGE_FORMAT = "Charge should be formatted as [number] or "
             + "number1[.number2] where number2 can be up to 2 decimal places! \ne.g. 200 or 200.55";
    /**
     * Regex to match charge format.
     * eg: "80.40".
     */
    public static final String VALIDATION_REGEX =
            "\\d+[.]?\\d{0,2}";

    private final Double perDayCharge;

    /**
     * Constructs an {@code Charge}.
     *
     * @param charge A valid per day charge.
     */
    public Charge(String charge) {
        requireNonNull(charge);

        this.perDayCharge = Double.parseDouble(charge);
    }

    /**
     * Returns the value of a valid charge per day
     * @return perDayValue as Double.
     */
    public Double getCharge() {
        return this.perDayCharge;
    }

    /**
     * Returns true if a given string is a valid charge
     * @param charge The input string.
     * @return True if input matches VALIDATION_REGEX, False otherwise.
     */
    public static boolean isValidCharge(String charge) {
        return charge.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.valueOf(perDayCharge);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Charge
                && perDayCharge.equals(((Charge) other).getCharge()));
    }

    @Override
    public int hashCode() {
        return perDayCharge.hashCode();
    }

}
