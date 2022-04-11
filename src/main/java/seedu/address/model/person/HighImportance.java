package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class HighImportance implements Comparable<HighImportance> {
    public static final String MODEL_NAME = "impt";
    public static final HighImportance HIGH_IMPORTANCE = new HighImportance(true);
    public static final HighImportance NOT_HIGH_IMPORTANCE = new HighImportance(false);
    public static final String MESSAGE_CONSTRAINTS =
            "HighImportance should be either true or false, and should not be blank.";
    public static final String TRUE_STRING = String.valueOf(true);
    public static final String FALSE_STRING = String.valueOf(false);
    /*
     * The first character of the HighImportance status must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code HighImportance} status object.
     *
     * @param hasHighImportance The HighImportance status.
     */
    public HighImportance(Boolean hasHighImportance) {
        requireNonNull(hasHighImportance);
        value = hasHighImportance.toString();
    }

    /**
     * Takes the string representation of the boolean and returns the correct {@code HighImportance} object.
     *
     * @param hasHighImportance is the String representation provided.
     * @return the {@code HighImportance} object that corresponds to the String provided.
     */
    public static HighImportance valueOf(String hasHighImportance) throws IllegalArgumentException {
        if (!(TRUE_STRING.equals(hasHighImportance) || FALSE_STRING.equals(hasHighImportance))) {
            throw new IllegalArgumentException("HighImportance can only be true or false.");
        }

        return TRUE_STRING.equals(hasHighImportance)
                ? HIGH_IMPORTANCE
                : NOT_HIGH_IMPORTANCE;
    }

    /**
     * Returns true if a given string has a valid HighImportance status.
     */
    public static boolean isValidHighImportance(String test) {
        return test.matches(VALIDATION_REGEX)
                && (TRUE_STRING.equals(test) || FALSE_STRING.equals(test));
    }

    /**
     * Returns true if a person has high importance
     */
    public boolean hasHighImportance() {
        return this.equals(HIGH_IMPORTANCE);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HighImportance // instanceof handles nulls
                && value.equals(((HighImportance) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(HighImportance other) {
        if (this.hasHighImportance() && !other.hasHighImportance()) {
            return -1;
        } else if (this.hasHighImportance() == other.hasHighImportance()) {
            return 0;
        } else if (!this.hasHighImportance() && other.hasHighImportance()) {
            return 1;
        } else {
            return 0;
        }
    }
}
