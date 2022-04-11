package seedu.address.model.order;

import static java.util.Objects.requireNonNull;

public class Complete {
    public static final String MESSAGE_CONSTRAINTS = "Complete can take only boolean values";

    public final Boolean isCompleted;

    /**
     * Constructs a {@code Complete}.
     *
     * @param value A valid boolean.
     */
    public Complete(Boolean value) {
        requireNonNull(value);

        this.isCompleted = value;
    }

    /**
     * Constructs a {@code Complete}.
     *
     * @param value A valid string of a boolean.
     */
    public Complete(String value) {
        requireNonNull(value);
        if (value.equals(Boolean.TRUE.toString())) {
            this.isCompleted = true;
        } else {
            this.isCompleted = false;
        }
    }

    /**
     * Returns true if a given string is a valid complete value.
     */
    public static boolean isValidComplete(String details) {
        return details.equals(Boolean.FALSE.toString())
                || details.equals(Boolean.TRUE.toString());
    }

    public boolean isComplete() {
        return this.isCompleted;
    }

    @Override
    public String toString() {
        return (isCompleted) ? "Complete" : "Incomplete";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Complete // instanceof handles nulls
                && isCompleted.equals(((Complete) other).isCompleted)); // state check
    }

    @Override
    public int hashCode() {
        return isCompleted.hashCode();
    }



}
