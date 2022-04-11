package seedu.address.model.procedure;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Completion {
    public static final String MESSAGE_CONSTRAINTS = "1. hasCompleted should be a Boolean value, "
        + "Boolean.TRUE or Boolean.FALSE";

    public static final String VALIDATION_REGEX = "^([t][r][u][e]|[f][a][l][s][e])$";
    public final Boolean hasCompleted;

    /**
     * Constructs a {@code Completion}.
     *
     * @param hasCompleted A valid Boolean value indicating hasCompleted of Procedure.
     */
    public Completion(String hasCompleted) {
        requireNonNull(hasCompleted);
        checkArgument(isValidHasCompleted(hasCompleted), MESSAGE_CONSTRAINTS);
        this.hasCompleted = Boolean.parseBoolean(hasCompleted);
    }

    /**
     * Returns true if a given string is a valid boolean.
     *
     */
    public static boolean isValidHasCompleted(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public Boolean getHasCompleted() {
        return this.hasCompleted;
    }

    @Override
    public String toString() {
        return this.hasCompleted.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Completion // instanceof handles nulls
                && this.getHasCompleted().equals(((Completion) other).getHasCompleted())); // state check
    }

    @Override
    public int hashCode() {
        return this.hasCompleted.hashCode();
    }
}
