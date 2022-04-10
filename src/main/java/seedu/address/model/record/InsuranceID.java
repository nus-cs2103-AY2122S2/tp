package seedu.address.model.record;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Record's insurance id.
 * Guarantees: immutable; is valid as declared in {@link #isValidInsuranceID(String)}
 */
public class InsuranceID {
    public static final String MESSAGE_CONSTRAINTS =
            "Insurance Index should only contain numbers, and it should not be blank";
    public static final String MESSAGE_INDEX_CONSTRAINT = "Insurance Index cannot be 0!";
    public static final String VALIDATION_REGEX = "\\d{1,}";
    public final String id;

    /**
     * Constructs a {@code Price}.
     *
     * @param insuranceID A valid price number.
     */
    public InsuranceID(String insuranceID) {
        requireNonNull(insuranceID);
        checkArgument(isValidInsuranceID(insuranceID), MESSAGE_CONSTRAINTS);
        checkArgument(isNotZeroInsuranceID(insuranceID), MESSAGE_INDEX_CONSTRAINT);
        id = insuranceID;
    }

    /**
     * Constructs a {@code Name}.
     *
     * @param insuranceName A valid name.
     */
    public InsuranceID(String insuranceName, Boolean yes) {
        requireNonNull(insuranceName);
        id = insuranceName;
    }

    /**
     * Returns true if a given string is a valid price number.
     */
    public static boolean isValidInsuranceID(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given insurance id is more than zero.
     */
    public static boolean isNotZeroInsuranceID(String test) {

        int clientID = Integer.parseInt(test);
        if (clientID > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InsuranceID // instanceof handles nulls
                && id.equals(((InsuranceID) other).id)); // state check
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
