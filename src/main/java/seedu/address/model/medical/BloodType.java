package seedu.address.model.medical;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a patient's blood type in MedBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidBloodType(String)}
 */
public class BloodType {

    public static final String MESSAGE_CONSTRAINTS = "Invalid blood type input. "
            + "We recommend inputting blood type using ABO blood group system (eg. AB), "
            + "but we allow any other blood type input values (eg. other blood group system). "
            + "Blood type parameter can take any values, and it should not be blank.";

    /*
     * Blood type can take any value
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code BloodType}.
     *
     * @param bloodType A valid blood type.
     */
    public BloodType(String bloodType) {
        requireNonNull(bloodType);
        checkArgument(isValidBloodType(bloodType), MESSAGE_CONSTRAINTS);
        value = bloodType;
    }

    /**
     * Returns true if given string is valid.
     */
    public static boolean isValidBloodType(String test) {
        Pattern p = Pattern.compile(VALIDATION_REGEX);
        Matcher m = p.matcher(test);
        return m.lookingAt();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BloodType // instanceof handles nulls
                && value.equals(((BloodType) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
