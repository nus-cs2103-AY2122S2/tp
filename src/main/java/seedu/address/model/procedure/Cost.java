package seedu.address.model.procedure;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.math.BigDecimal;


public class Cost implements Comparable<Cost> {

    public static final String MESSAGE_CONSTRAINTS =
            "1. Cost should be rounded to the nearest cent. \n"
                    + "2. Cost should be more than $0 and less than $100000000 (One hundred million dollars) \n"
                    + "3. Cost should follow the format 'dollars.cents'.\n"
                    + "Example: 31.10";

    public static final String VALIDATION_REGEX = "^\\d+(?:\\.\\d{1,2})?$";
    public static final BigDecimal MAX_COST = new BigDecimal(100000000);
    public static final BigDecimal MIN_COST = new BigDecimal(0);

    public final BigDecimal cost;

    /**
     * Constructs a {@code Cost}.
     *
     * @param cost A valid cost.
     */
    public Cost(String cost) {
        requireNonNull(cost);
        checkArgument(isValidCost(cost), MESSAGE_CONSTRAINTS);
        this.cost = new BigDecimal(cost).setScale(2, BigDecimal.ROUND_UP);
    }

    /**
     * Returns true if a given string is a valid cost.
     */
    public static boolean isValidCost(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            BigDecimal cost = new BigDecimal(test);
            if (cost.compareTo(MAX_COST) < 0 && cost.compareTo(MIN_COST) > 0) {
                return true;
            }
        }
        return false;
    }

    public BigDecimal value() {
        return this.cost;
    }

    @Override
    public String toString() {
        return this.cost.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.procedure.Cost // instanceof handles nulls
                && this.cost.equals(((seedu.address.model.procedure.Cost) other).cost)); // state check
    }

    @Override
    public int hashCode() {
        return this.cost.hashCode();
    }

    @Override
    public int compareTo(Cost otherCost) {
        return cost.compareTo(otherCost.cost);
    }
}
